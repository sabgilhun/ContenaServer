from scraping.bluesman_scraper import BluesmansScraper
from scraping.modeman_scraper import ModeManScrapper
from scraping.brendabrenden_scraper import BrendaBrendenScrapper


def new_arrival_data_generator_with_scraping():
    yield BluesmansScraper().scrap()
    yield ModeManScrapper().scrap()
    yield BrendaBrendenScrapper().scrap()
