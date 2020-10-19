from scraping.bluesman_scraper import BluesmansScraper
from scraping.modeman_scraper import ModeManScraper
from scraping.brendabrenden_scraper import BrendaBrendenScraper


def new_arrival_data_generator_with_scraping():
    yield BluesmansScraper().scrap()
    yield ModeManScraper().scrap()
    yield BrendaBrendenScraper().scrap()
