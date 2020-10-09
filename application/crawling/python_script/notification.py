import requests
import json

# target_url = "http://127.0.0.1:8080/notification/new_item"


target_url = "http://106.10.49.154/notification/new_item"


def trigger_notification_server(shop_list):
    headers = {"Content-Type": "application/json"}
    requests.post(target_url, data=json.dumps({"shop_list": shop_list}), headers=headers)
