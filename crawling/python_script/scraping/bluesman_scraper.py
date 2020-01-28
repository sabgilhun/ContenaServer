import requests
from bs4 import BeautifulSoup

base_url = 'http://bluesman.co.kr'


def scrap_new_arrival_item():
    # bluesman 신상품 화면 url
    target_url = 'http://bluesman.co.kr/product/list.html?cate_no=24/'

    # chrome headers
    headers = {'User-Agent': 'Mozilla/5.0'}

    request = requests.get(target_url, headers=headers)

    soup = BeautifulSoup(request.text, 'html.parser')

    data = list(map(__to_json_parsable, soup.find_all('div', {'class': 'col xans-record-'})))
    print('complete scraping: ' + target_url)

    return data


def scrap_shop_logo():
    # chrome headers
    headers = {'User-Agent': 'Mozilla/5.0'}

    request = requests.get(base_url, headers=headers)

    soup = BeautifulSoup(request.text, 'html.parser')

    return base_url + soup.find_all('a', {'href': '/'})[0].find('img')['src']


def __to_json_parsable(item):
    json_parsable = dict()
    json_parsable['shop_name'] = 'bluesman'
    json_parsable['product_name'] = item.find('div', {'class': 'product_title'}).find('span').text
    json_parsable['brand'] = item.find('span', {'class': 'product_brand'}).text
    json_parsable['image_url'] = item.find('img', {'class': 'thumb_image'})['src'].replace("//", "http://")
    json_parsable['page_url'] = base_url + item.find('div', {'class': 'product_title'}).find('a')['href']
    json_parsable['price'] = item.find('strong', {'class': 'price'}).text.replace("KRW ", "")

    return json_parsable
