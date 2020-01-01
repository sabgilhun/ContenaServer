from _datetime import datetime
from database.db_handler import *
from scraping.scraping_handler import *

if __name__ == '__main__':
    print("Cron job has started at %s" % datetime.now())

    db_init()
    for scraping_data in data_generator_with_scraping():
        db_set_data(scraping_data['path'], scraping_data['data'])
