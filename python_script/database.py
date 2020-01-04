import firebase_admin
from firebase_admin import credentials
from firebase_admin import db

scrapped_database_path = 'scrapped_data/'
post_database_path = 'post_data/'


def init():
    cred = credentials.Certificate("../contena-5c99b-firebase-adminsdk-2lmrx-2bf717ce3a.json")
    firebase_admin.initialize_app(cred, {'databaseURL': 'https://contena-5c99b.firebaseio.com'})
    print('complete db initialize')


def get_scrapped_items(shop_name):
    ref = db.reference(scrapped_database_path + shop_name)
    return ref.get()


def set_scrapped_items(shop_name, data):
    ref = db.reference(scrapped_database_path + shop_name)
    ref.set(data)


def push_post(post):
    ref = db.reference(post_database_path)
    ref.push(post)
