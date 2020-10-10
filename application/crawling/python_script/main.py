from _datetime import datetime
from scraping.scraper_handler import *
from notification import *
import database

if __name__ == '__main__':
    time_stamp = datetime.strftime(datetime.now(), '%y-%m-%d %H:%M')
    print("Cron job has started at %s" % time_stamp)

    shop_list = list()
    for scraping_data in new_arrival_data_generator_with_scraping():
        shop = scraping_data['shop']
        scrapped_items = scraping_data['scrapped_items']

        if len(scrapped_items) > 0:
            database.insert_shop_post_item_entity(shop, scrapped_items)
            shop_list.append(shop['shop_name'])

    if shop_list:
        trigger_notification_server(shop_list)
