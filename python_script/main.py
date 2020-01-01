from _datetime import datetime
from scraping.scraping_handler import *
from model_logic.new_arrival import *
import database

if __name__ == '__main__':
    print("Cron job has started at %s" % datetime.now())

    database.init()

    for scraping_data in data_generator_with_scraping():
        shop_name = scraping_data['shop_name']
        new_scrapped_items = scraping_data['scrapped_item']
        old_scrapped_items = database.get_scrapped_items(shop_name)

        database.set_scrapped_items(shop_name, new_scrapped_items)

        new_arrival_items = extract_new_arrival_item(old_scrapped_items, new_scrapped_items)
        post_model = create_new_arrival_post_model(shop_name, new_arrival_items)

        if post_model:
            print('new')
        else:
            print('none')
