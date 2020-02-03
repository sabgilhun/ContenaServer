from . import bluesman_scraper
from . import modeman_scraper


def new_arrival_data_generator_with_scraping():
    yield {'shop_name': bluesman_scraper.shop_name,
           'shop_logo_url': bluesman_scraper.scrap_shop_logo(),
           'scrapped_item': bluesman_scraper.scrap_new_arrival_item()
           }
    yield {'shop_name': modeman_scraper.shop_name,
           'shop_logo_url': modeman_scraper.scrap_shop_logo(),
           'scrapped_item': modeman_scraper.scrap_new_arrival_item()
           }
    # yield {'shop_name': '',
    #       'shop_logo': logo,
    #       'scrapped_item': item
    #       }
