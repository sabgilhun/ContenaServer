import pymysql
from global_constants import *

shop_insert_sql = """
        INSERT INTO shop(shop_name, shop_logo_url, shop_desc) 
                VALUES (%s, %s, %s)
                ON DUPLICATE KEY UPDATE shop_logo_url = %s, shop_desc = %s
        """

item_insert_sql = """
        INSERT INTO item(product_name, brand, image_url, page_url, price, origin_price, shop_name, post_id)
                 VALUES (%s, %s, %s, %s, %s, %s, %s, %s)
        """

post_insert_sql = """
        INSERT INTO post(shop_name, upload_date)
                 VALUES (%s, NOW())
        """

item_select_sql = """
        SELECT product_name, brand, image_url, page_url, price, shop_name FROM item WHERE post_id=%s
        """

post_select_sql = """
        SELECT id FROM post WHERE shop_name=%s ORDER BY id DESC
        """


def connect_contena_db():
    return pymysql.connect(host='db', user='root', password='1234', db='contena', charset='utf8',
                           cursorclass=pymysql.cursors.DictCursor)

    # return pymysql.connect(host='49.50.162.239', port=3306, user='root', password='1234', db='contena', charset='utf8',
    #                        cursorclass=pymysql.cursors.DictCursor)


def select_item_with_shop_name(shop_name):
    connection = connect_contena_db()

    try:
        with connection.cursor() as cursor:
            cursor.execute(post_select_sql, (shop_name,))
            post = cursor.fetchone()
            if post:
                post_id = post['id']
                cursor.execute(item_select_sql, (post_id,))
                items = cursor.fetchall()
            else:
                items = list()
    finally:
        connection.close()

    return items


def insert_shop_post_item_entity(shop, scrapped_item):
    post = dict()
    post['shop_name'] = shop['shop_name']

    connection = connect_contena_db()

    try:
        with connection.cursor() as cursor:
            # insert shop
            cursor.execute(shop_insert_sql,
                           (shop['shop_name'],
                            shop['shop_logo_url'], shop['shop_desc'],
                            shop['shop_logo_url'], shop['shop_desc']))

            last = len(scrapped_item)
            size = LIMIT_NUMBER_OF_ITEM_IN_POST

            while last - size >= 0:
                # pop last items
                popped = scrapped_item[(last - size):last]

                # insert popped items
                insert_post_and_items(cursor, shop, post, popped)

                # delete inserted items
                del scrapped_item[(last - size): last]

                # move last index
                last -= size

            # insert remaining items
            insert_post_and_items(cursor, shop, post, scrapped_item)

            connection.commit()
    finally:
        connection.close()


def insert_post_and_items(cursor, shop, post, items):
    # insert post
    cursor.execute(post_insert_sql, (post['shop_name']))

    # get id of inserted post
    cursor.execute(post_select_sql, (shop['shop_name'],))
    post = cursor.fetchone()
    post_id = post['id']

    # items with post_id
    for item in items:
        cursor.execute(item_insert_sql, (item['product_name'], item['brand'],
                                         item['image_url'], item['page_url'],
                                         item['price'], item['origin_price'],
                                         item['shop_name'], post_id))
