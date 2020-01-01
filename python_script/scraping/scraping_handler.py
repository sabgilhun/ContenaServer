from . import bluesman_scraper


def data_generator_with_scraping():
    yield {'path': 'bluesman', 'data': bluesman_scraper.scrap()}
