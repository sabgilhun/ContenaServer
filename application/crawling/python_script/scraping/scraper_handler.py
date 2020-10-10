from scraping.bluesman_scraper import BluesmansScraper
from scraping.modeman_scraper import ModeManScrapper


def new_arrival_data_generator_with_scraping():
    yield BluesmansScraper().scrap()
    yield ModeManScrapper().scrap()
