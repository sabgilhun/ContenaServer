import requests
from bs4 import BeautifulSoup

base_url = 'http://mode-man.com'
shop_name = 'mode-man'


def scrap_new_arrival_item():
    # mode-man 신상품 화면 url
    target_url = base_url + '/product/list.html?cate_no=354/'

    # chrome headers
    headers = {'User-Agent': 'Mozilla/5.0'}

    request = requests.get(target_url, headers=headers)

    soup = BeautifulSoup(request.text, 'html.parser')

    data = list(map(__generate_dict_item, soup.find_all('li', {'class': 'item infinite-item xans-record-'})))
    print('complete scraping: ' + target_url)

    return data


def scrap_shop_logo():
    # chrome headers
    headers = {'User-Agent': 'Mozilla/5.0'}

    request = requests.get(base_url, headers=headers)

    soup = BeautifulSoup(request.text, 'html.parser')

    return base_url + soup.find_all('a', {'href': '/index.html'})[0].find('img')['src']


def __generate_dict_item(item):
    item_dict = dict()
    item_dict['shop_name'] = shop_name
    item_dict['product_name'] = item.find('p', {'class': 'name'}).find('span').text
    data_list = item.findAll('li', {'class': 'xans-record-'})
    item_dict['brand'] = data_list[0].find('a').text
    item_dict['image_url'] = item.find('img', {'class': 'lazy thumb-front'})['data-original'].replace("//", "http://")
    item_dict['page_url'] = base_url + item.find('div', {'class': 'box'}).find('a')['href']
    item_dict['price'] = data_list[1].findAll('span')[1].text.replace("w", "")

    return item_dict
