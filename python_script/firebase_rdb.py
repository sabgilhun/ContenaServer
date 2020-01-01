import firebase_admin
from firebase_admin import credentials
from firebase_admin import db


def firebase_init():
    cred = credentials.Certificate("../contena-5c99b-firebase-adminsdk-2lmrx-2bf717ce3a.json")
    firebase_admin.initialize_app(cred, {'databaseURL': 'https://contena-5c99b.firebaseio.com'})