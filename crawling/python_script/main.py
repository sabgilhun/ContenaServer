from _datetime import datetime
from scraping.scraper_handler import *
from model_logic.new_arrival import *
from notification import *
import database

if __name__ == '__main__':
    time_stamp = datetime.strftime(datetime.now(), '%y-%m-%d %H:%M')
    print("Cron job has started at %s" % time_stamp)

    shop_list = list()
    for scraping_data in new_arrival_data_generator_with_scraping():
        shop_name = scraping_data['shop_name']
        shop_logo_url = scraping_data['shop_logo_url']

        new_scrapped_items = scraping_data['scrapped_item']
        old_scrapped_items = database.select_item_with_shop_name(shop_name)

        new_arrival_items = extract_new_arrival_item(old_scrapped_items, new_scrapped_items)

        if new_arrival_items:
            database.insert_shop_post_item_entity(new_arrival_items, shop_name, shop_logo_url)
            shop_list.append(shop_name)

    if shop_list:
        trigger_notification_server(shop_list)
