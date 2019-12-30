import requests
from bs4 import BeautifulSoup


def scrap():
    # bluesman 신상품 화면 url
    target_url = 'http://bluesman.co.kr/product/list.html?cate_no=24/'

    # chrome headers
    headers = {'User-Agent': 'Mozilla/5.0'}

    request = requests.get(target_url, headers=headers)

    soup = BeautifulSoup(request.text, 'html.parser')

    print(soup.encode('utf8'))
    print(list(map(__to_json_parsable, soup.find_all('div', {'class': 'col xans-record-'}))))


def __to_json_parsable(item):
    json_parsable = dict()
    json_parsable['image'] = item.find('img', {'class': 'thumb_image'})['src'].replace("//", "")
    json_parsable['brand'] = item.find('span', {'class': 'product_brand'}).text
    json_parsable['price'] = item.find('strong', {'class': 'price'}).text.replace("KRW ", "")
    json_parsable['product'] = item.find('div', {'class': 'product_title'}).find('span').text
    return json_parsable
