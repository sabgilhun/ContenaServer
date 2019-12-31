import datetime
import bluesman_scraper
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db

if __name__ == '__main__':
    print("Cron job has started at %s" % datetime.datetime.now())
    bluesman_scraper.scrap()
    cred = credentials.Certificate("../contena-5c99b-firebase-adminsdk-2lmrx-2bf717ce3a.json")
    print(firebase_admin.initialize_app(cred, {'databaseURL': 'https://contena-5c99b.firebaseio.com'}))

