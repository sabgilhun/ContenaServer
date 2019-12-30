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
    for item in soup.find_all('div', {'class': 'col xans-record-'}):
        print(item.find('img', {'class': 'thumb_image'})['src'].replace("//", ""))
        print(item.find('span', {'class': 'product_brand'}).text)
        print(item.find('strong', {'class': 'price'}).text.replace("KRW ", ""))
        print(item.find('div', {'class': 'product_title'}).find('span').text)
        print('\n')
