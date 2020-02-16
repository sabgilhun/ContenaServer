import pymysql
from _datetime import datetime

shop_insert_sql = """
        INSERT INTO shop(shop_name, shop_logo_url) 
                VALUES (%s, %s)
                ON DUPLICATE KEY UPDATE shop_logo_url = %s
        """

item_insert_sql = """
        INSERT INTO item(product_name, brand, image_url, page_url, price, shop_name, post_id)
                 VALUES (%s, %s, %s, %s, %s, %s, %s)
        """

post_insert_sql = """
        INSERT INTO post(shop_name, upload_date)
                 VALUES (%s, NOW())
        """

item_select_sql = """
        SELECT product_name, brand, image_url, page_url, price, shop_name FROM item WHERE shop_name=%s
        """

post_select_sql = """
        SELECT id FROM post WHERE shop_name=%s ORDER BY id DESC
        """


def connect_contena_db():
    # return pymysql.connect(host='db', user='root', password='1234', db='contena', charset='utf8',
    #                        cursorclass=pymysql.cursors.DictCursor)

    return pymysql.connect(host='127.0.0.1', port=3306, user='root', password='1234', db='contena', charset='utf8',
                           cursorclass=pymysql.cursors.DictCursor)


def insert_shop(cursor, shop):
    cursor.execute(shop_insert_sql, (shop['shop_name'], shop['shop_logo_url'], shop['shop_logo_url']))


def insert_item(cursor, items, post_id):
    for item in items:
        cursor.execute(item_insert_sql, (item['product_name'], item['brand'], item['image_url'],
                                         item['page_url'], item['price'], item['shop_name'], post_id))


def insert_post(cursor, post):
    cursor.execute(post_insert_sql, (post['shop_name']))


def select_one_post_id_with_shop_name(shop_name, cursor=None):
    if not cursor:
        cursor = connect_contena_db().cursor()

    cursor.execute(post_select_sql, (shop_name,))
    return cursor.fetchone()


def select_item_with_shop_name(shop_name, cursor=None):
    if not cursor:
        cursor = connect_contena_db().cursor()

    cursor.execute(item_select_sql, (shop_name,))
    return cursor.fetchall()


def insert_shop_post_item_entity(new_items, shop_name, shop_logo_url):
    post = dict()
    post['shop_name'] = shop_name

    shop = dict()
    shop['shop_name'] = shop_name
    shop['shop_logo_url'] = shop_logo_url

    connection = connect_contena_db()

    try:
        with connection.cursor() as cursor:
            insert_shop(cursor, shop)
            insert_post(cursor, post)
            post_id = select_one_post_id_with_shop_name(shop_name, cursor)['id']
            insert_item(cursor, new_items, post_id)
            connection.commit()
    finally:
        connection.close()
