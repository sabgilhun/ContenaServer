import datetime
import bluesman_scraper

if __name__ == '__main__':
    print("Cron job has started at %s" % datetime.datetime.now())
    bluesman_scraper.scrap()
