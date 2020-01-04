from . import bluesman_scraper


def new_arrival_data_generator_with_scraping():
    yield {'shop_name': 'bluesman',
           'shop_logo': bluesman_scraper.scrap_shop_logo(),
           'scrapped_item': bluesman_scraper.scrap_new_arrival_item()
           }
