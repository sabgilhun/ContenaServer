import pymysql

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
        SELECT product_name, brand, image_url, page_url, price, shop_name FROM item WHERE shop_name=%s ORDER BY id DESC
        """

post_select_sql = """
        SELECT id FROM post WHERE shop_name=%s ORDER BY id DESC
        """


def connect_contena_db():
    # return pymysql.connect(host='db', user='root', password='1234', db='contena', charset='utf8',
    #                        cursorclass=pymysql.cursors.DictCursor)

    return pymysql.connect(host='49.50.162.239', port=3306, user='root', password='1234', db='contena', charset='utf8',
                           cursorclass=pymysql.cursors.DictCursor)


def select_item_with_shop_name(shop_name, size):
    connection = connect_contena_db()

    try:
        with connection.cursor() as cursor:
            cursor.execute(item_select_sql, (shop_name,))
            items = cursor.fetchmany(size)
    finally:
        connection.close()

    return items


def insert_shop_post_item_entity(new_items, shop_name, shop_logo_url):
    post = dict()
    post['shop_name'] = shop_name

    shop = dict()
    shop['shop_name'] = shop_name
    shop['shop_logo_url'] = shop_logo_url

    connection = connect_contena_db()

    try:
        with connection.cursor() as cursor:
            # insert shop
            cursor.execute(shop_insert_sql, (shop['shop_name'], shop['shop_logo_url'], shop['shop_logo_url']))

            # insert post
            cursor.execute(post_insert_sql, (post['shop_name']))

            # get id of inserted post
            cursor.execute(post_select_sql, (shop_name,))
            post = cursor.fetchone()
            post_id = post['id']

            # insert items
            for item in new_items:
                cursor.execute(item_insert_sql, (item['product_name'], item['brand'], item['image_url'],
                                                 item['page_url'], item['price'], item['shop_name'], post_id))
            connection.commit()
    finally:
        connection.close()
