import firebase_admin
import pymysql
from firebase_admin import credentials
from firebase_admin import db

scrapped_database_path = 'scrapped_data/'
post_database_path = 'post_data/'


class DbHelper:
    def __init__(self):
        self.cursor = pymysql.connect(host='db', user='root', password='1234', db='contena',
                                      charset='utf8')

    def insert_item(self, items, post_id):
        sql = """
        INSERT INTO item(shop_name,product_name,brand,image_url,page_url,price,post_id)
                 VALUES (%s, %s, %s, %s, %s, %s, %s)
                 """
        for item in items:
            self.cursor.execute(sql, (item['shop_name'], item['product_name'], item['brand'],
                                      item['image_url'], item['page_url'], item['price'], post_id
                                      ))
        self.cursor.commit()
