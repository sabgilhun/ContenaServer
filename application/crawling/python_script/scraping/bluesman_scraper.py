import requests
from bs4 import BeautifulSoup
import database
from scraping.scrap_helper import *


class BluesmansScraper:
    base_url = 'http://bluesman.co.kr'

    shop_name = 'bluesman'
    shop_logo = ''
    shop_desc = '남성 아메리칸캐주얼, 그라미치, 오디너리핏츠, 파라부트, 레졸루, 오어슬로우 취급'

    page_base_url = base_url + '/product/list.html?cate_no=24&page='
    scrapped_items = list()

    def scrap(self):
        # get old post items
        keys_of_old = \
            list(map(
                lambda i: i['page_url'],
                database.select_item_with_shop_name(self.shop_name, LIMIT_NUMBER_OF_OLD_ITEM)
            ))

        # logo scrap
        soup = BeautifulSoup(requests.get(self.base_url, headers=HEADERS).text, 'html.parser')
        self.shop_logo = self.base_url + soup.find_all('a', {'href': '/'})[0].find('img')['src']

        # first page scrap
        page_no = 1
        while True:
            items = BluesmansScraper.scrap_items(self, page_no)
            self.scrapped_items.extend(items)
            page_no += 1
            keys_of_new = list(map(lambda i: i['page_url'], items))
            index = search_first_index(keys_of_old, keys_of_new)
            if index or len(self.scrapped_items) > LIMIT_NUMBER_OF_ITEM_IN_POST:
                break

        shop = {'shop_name': self.shop_name, 'shop_logo_url': self.shop_logo, 'shop_desc': self.shop_desc}
        return {'shop': shop, 'scrapped_items': self.scrapped_items[0:index]}

    def scrap_items(self, page_no):
        url = self.page_base_url + str(page_no)
        request = requests.get(url, headers=HEADERS)
        soup = BeautifulSoup(request.text, 'html.parser')

        data = list()
        for item in soup.find_all('div', {'class': 'col xans-record-'}):
            data.append(BluesmansScraper.generate_dict_item(self, item))

        print('complete scraping: ' + url)

        return data

    def generate_dict_item(self, item):
        item_dict = dict()
        item_dict['shop_name'] = self.shop_name
        item_dict['product_name'] = item.find('div', {'class': 'product_title'}).find('span').text
        item_dict['brand'] = item.find('span', {'class': 'product_brand'}).text
        item_dict['image_url'] = item.find('img', {'class': 'thumb_image'})['src'].replace("//", "http://")
        item_dict['page_url'] = self.base_url + item.find('div', {'class': 'product_title'}).find('a')['href']
        item_dict['price'] = item.find('strong', {'class': 'price'}).text.replace("KRW ", "")

        return item_dict
