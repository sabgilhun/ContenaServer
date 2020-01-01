from . import bluesman_scraper


def data_generator_with_scraping():
    yield {'shop_name': 'bluesman', 'scrapped_item': bluesman_scraper.scrap()}
