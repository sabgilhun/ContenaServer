import pymysql
from _datetime import datetime

item_insert_sql = """
        INSERT INTO item(shop_name,product_name,brand,image_url,page_url,price,post_id)
                 VALUES (%s, %s, %s, %s, %s, %s, %s)
                 """

post_insert_sql = """
        INSERT INTO post(shop_name,shop_logo_url,upload_date)
                 VALUES (%s, %s, %s)
                 """

post_select_sql = """
        SELECT post_id FROM post WHERE shop_name=%s ORDER BY post_id DESC
        """


def connect_contena_db():
    return pymysql.connect(host='db', user='root', password='1234', db='contena', charset='utf8',
                           cursorclass=pymysql.cursors.DictCursor)


def insert_item(cursor, items, post_id):
    for item in items:
        cursor.execute(item_insert_sql, (item['shop_name'], item['product_name'], item['brand'],
                                         item['image_url'], item['page_url'], item['price'], post_id))


def insert_post(cursor, post):
    cursor.execute(post_insert_sql, (post['shop_name'], post['shop_logo_url'], post['upload_date']))


def begin_item_and_post_insert(new_items, shop_logo_url):
    shop_name = new_items[0]['shop_name']

    post = dict()
    post['shop_name'] = shop_name
    post['shop_logo_url'] = shop_logo_url
    post['upload_date'] = datetime.strftime(datetime.now(), '%y-%m-%d %H:%M')

    connection = connect_contena_db()

    try:
        with connection.cursor() as cursor:
            insert_post(cursor, post)
            cursor.execute(post_select_sql, shop_name)
            post_id = cursor.fetchone()
            insert_item(cursor, new_items, post_id)
            connection.commit()

    finally:
        connection.close()
