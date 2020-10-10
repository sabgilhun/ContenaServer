import requests
from bs4 import BeautifulSoup
import database
from scraping.scrap_helper import *
from global_constants import *


class ModeManScrapper:
    base_url = 'http://mode-man.com'

    shop_name = 'mode-man'
    shop_logo = ''
    shop_desc = 'Denim Specialty Editing Shop Mode-Man 해외브랜드 데님 전문 편집 매장 홍대 모드맨'

    page_base_url = base_url + '/product/list.html?cate_no=354&page='
    scrapped_items = list()

    def scrap(self):
        # get old post items
        keys_of_old = \
            list(map(
                lambda i: i['page_url'],
                database.select_item_with_shop_name(self.shop_name)
            ))

        # logo scrap
        soup = BeautifulSoup(requests.get(self.base_url, headers=HEADERS).text, 'html.parser')
        self.shop_logo = self.base_url + soup.find_all('a', {'href': '/index.html'})[0].find('img')['src']

        # first page scrap
        page_no = 1
        while True:
            items = ModeManScrapper.scrap_items(self, page_no)
            self.scrapped_items.extend(items)
            page_no += 1
            keys_of_new = list(map(lambda i: i['page_url'], items))
            index = search_first_index(keys_of_old, keys_of_new)
            if (index and index >= 0) or len(self.scrapped_items) > LIMIT_NUMBER_OF_SCRAPPED_ITEM:
                break

        shop = {'shop_name': self.shop_name, 'shop_logo_url': self.shop_logo, 'shop_desc': self.shop_desc}
        return {'shop': shop, 'scrapped_items': self.scrapped_items[0:index]}

    def scrap_items(self, page_no):
        url = self.page_base_url + str(page_no)
        request = requests.get(url, headers=HEADERS)
        soup = BeautifulSoup(request.text, 'html.parser')

        data = list()
        for item in soup.find_all('li', {'class': 'item infinite-item xans-record-'}):
            data.append(ModeManScrapper.generate_dict_item(self, item))

        print('complete scraping: ' + url)

        return data

    def generate_dict_item(self, item):
        item_dict = dict()

        item_dict['shop_name'] = self.shop_name

        item_dict['product_name'] = item.find('p', {'class': 'name'}).find('span').text

        data_list = item.findAll('li', {'class': 'xans-record-'})
        item_dict['brand'] = data_list[0].find('a').text

        item_dict['image_url'] = \
            item.find('img', {'class': 'lazy thumb-front'})['data-original'].replace("//", "http://")

        item_dict['page_url'] = self.base_url + item.find('div', {'class': 'box'}).find('a')['href']

        item_dict['origin_price'] = None
        if len(data_list) == 3:
            item_dict['origin_price'] = remove_won_symbol(data_list[1].findAll('span')[1].text)
            item_dict['price'] = remove_won_symbol(data_list[2].findAll('span')[1].text)
        else:
            item_dict['price'] = remove_won_symbol(data_list[1].findAll('span')[1].text)

        return item_dict
