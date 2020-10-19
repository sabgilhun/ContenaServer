import requests
from bs4 import BeautifulSoup
import database
from scraping.scrap_helper import *


class BrendaBrendenScraper:
    base_url = 'http://brendabrenden.com'

    shop_name = 'Brenda Brenden'
    shop_logo = ''
    shop_desc = '평범한 사람들을 위한 특별한 옷을 만드는 곳, 브렌다브렌든 - '

    page_base_url = base_url + '/product/list.html?cate_no=72&page='
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
        self.shop_logo = self.base_url + soup.find_all('a', {'href': '/'})[0].find('img')['src']

        # first page scrap
        page_no = 1
        while True:
            items = self.scrap_items(page_no)
            self.scrapped_items.extend(items)
            page_no += 1
            keys_of_new = list(map(lambda i: i['page_url'], items))
            index = search_first_index(keys_of_old, keys_of_new)

            if index >= 0:
                # index search success
                break

            if check_scrap_limit(page_no, self.scrapped_items):
                # over limit
                index = len(self.scrapped_items)
                break

        shop = {'shop_name': self.shop_name, 'shop_logo_url': self.shop_logo, 'shop_desc': self.shop_desc}
        return {'shop': shop, 'scrapped_items': self.scrapped_items[0:index]}

    def scrap_items(self, page_no):
        url = self.page_base_url + str(page_no)
        request = requests.get(url, headers=HEADERS)
        soup = BeautifulSoup(request.text, 'html.parser')

        data = list()
        for item in soup.find_all('li', {'class': 'item xans-record-'}):
            data.append(self.generate_dict_item(item))

        print('complete scraping: ' + url)

        return data

    def generate_dict_item(self, item):
        item_dict = dict()

        item_dict['shop_name'] = self.shop_name

        item_dict['product_name'] = item.find('p', {'class': 'name'}).find('span').text

        item_dict['brand'] = ""

        item_dict['image_url'] = item.find('div', {'class': 'prodImg'}).find('img')['src'].replace('//', 'http://')

        item_dict['page_url'] = self.base_url + item.find('a')['href']

        data_list = item.findAll('li', {'class': 'xans-record-'})
        item_dict['origin_price'] = None
        if len(data_list) == 3:
            item_dict['origin_price'] = data_list[0].findAll('span')[1].text
            item_dict['price'] = data_list[2].findAll('span')[1].text
        elif len(data_list) == 2:
            item_dict['origin_price'] = data_list[0].findAll('span')[1].text
            item_dict['price'] = data_list[1].findAll('span')[1].text
        else:
            item_dict['price'] = data_list[0].findAll('span')[1].text
        return item_dict
