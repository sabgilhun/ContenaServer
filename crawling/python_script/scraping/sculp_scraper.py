import requests
from bs4 import BeautifulSoup

base_url = 'https://sculpstore.com'
shop_name = 'sculp'


def scrap_new_arrival_item():
    # mode-man 신상품 화면 url
    target_url = base_url + '/product-category/men/new-arrival-men/'

    # chrome headers
    headers = {'User-Agent': 'Mozilla/5.0'}

    request = requests.get(target_url, headers=headers)

    soup = BeautifulSoup(request.text, 'html.parser')

    print('complete scraping: ' + target_url)

    first_item = soup.find_all('div', {'class': 'product-loop products-grid product-count-5'})[0].find('div')
    item_list = first_item.find_next_siblings()

    item_list.insert(0, first_item)
    item_list.pop()

    data = list(map(__generate_dict_item, item_list))

    return data


def scrap_shop_logo():
    # chrome headers
    headers = {'User-Agent': 'Mozilla/5.0'}

    request = requests.get(base_url, headers=headers)

    soup = BeautifulSoup(request.text, 'html.parser')

    return soup.find_all('div', {'class': 'logo'})[0].find('a').find('img')['src']


def __generate_dict_item(item):
    item_dict = dict()
    item_dict['shop_name'] = shop_name
    item_dict['product_name'] = item.find('h3', {'class': 'product-name'}).find('a').text
    item_dict['brand'] = item.find('div', {'class': 'product-brands'}).find('a').text
    item_dict['image_url'] = item.find('img', {'class': 'hide-image'})['src']
    item_dict['page_url'] = base_url + item.find('a', {'class': 'woocommerce-LoopProduct-link'})['href']
    item_dict['price'] = item.find('span', {'class': 'woocommerce-Price-amount amount'}).text.replace('₩','')

    return item_dict
