import firebase_admin
from firebase_admin import credentials
from firebase_admin import db


def db_init():
    cred = credentials.Certificate("../contena-5c99b-firebase-adminsdk-2lmrx-2bf717ce3a.json")
    firebase_admin.initialize_app(cred, {'databaseURL': 'https://contena-5c99b.firebaseio.com'})
    print('complete db initialize')


def db_get_data(path):
    ref = db.reference(path)
    print(ref.get())
    return ref.get()


def db_set_data(path, data):
    ref = db.reference(path)
    ref.set(data)
