from scraping.bluesman_scraper import BluesmansScraper


def new_arrival_data_generator_with_scraping():
    yield BluesmansScraper().scrap()
