import requests
from bs4 import BeautifulSoup

base_url = 'http://bluesman.co.kr'
shop_name = 'bluesman'

def scrap_new_arrival_item():
    # bluesman 신상품 화면 url
    target_url = base_url + '/product/list.html?cate_no=24/'

    # chrome headers
    headers = {'User-Agent': 'Mozilla/5.0'}

    request = requests.get(target_url, headers=headers)

    soup = BeautifulSoup(request.text, 'html.parser')

    data = list(map(__generate_dict_item, soup.find_all('div', {'class': 'col xans-record-'})))
    print('complete scraping: ' + target_url)

    return data


def scrap_shop_logo():
    # chrome headers
    headers = {'User-Agent': 'Mozilla/5.0'}

    request = requests.get(base_url, headers=headers)

    soup = BeautifulSoup(request.text, 'html.parser')

    return base_url + soup.find_all('a', {'href': '/'})[0].find('img')['src']


def __generate_dict_item(item):
    item_dict = dict()
    item_dict['shop_name'] = shop_name
    item_dict['product_name'] = item.find('div', {'class': 'product_title'}).find('span').text
    item_dict['brand'] = item.find('span', {'class': 'product_brand'}).text
    item_dict['image_url'] = item.find('img', {'class': 'thumb_image'})['src'].replace("//", "http://")
    item_dict['page_url'] = base_url + item.find('div', {'class': 'product_title'}).find('a')['href']
    item_dict['price'] = item.find('strong', {'class': 'price'}).text.replace("KRW ", "")

    return item_dict
