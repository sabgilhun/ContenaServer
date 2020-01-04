from _datetime import datetime
from scraping.scraping_handler import *
from model_logic.new_arrival import *
import database

if __name__ == '__main__':
    time_stamp = datetime.strftime(datetime.now(), '%y-%m-%d %H:%M')
    print("Cron job has started at %s" % time_stamp)

    database.init()

    for scraping_data in new_arrival_data_generator_with_scraping():
        shop_name = scraping_data['shop_name']
        shop_logo = scraping_data['shop_logo']
        new_scrapped_items = scraping_data['scrapped_item']
        old_scrapped_items = database.get_scrapped_items(shop_name)

        database.set_scrapped_items(shop_name, new_scrapped_items)

        new_arrival_items = extract_new_arrival_item(old_scrapped_items, new_scrapped_items)

        post = create_new_arrival_post(time_stamp, shop_name, shop_logo, new_arrival_items)

        if post:
            database.push_post(post)
