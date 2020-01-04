def extract_new_arrival_item(old_items, new_items):
    if old_items is None:
        old_items = list()

    if new_items is None:
        new_items = list()

    return list(filter(lambda x: x not in old_items, new_items))


def create_new_arrival_post(time_stamp, shop_name, shop_logo, new_items):
    if not new_items:
        return new_items
    else:
        post_dict = dict()
        post_dict['time_stamp'] = time_stamp
        post_dict['shop_name'] = shop_name
        post_dict['logo'] = shop_logo
        post_dict['new_item'] = new_items
        return post_dict
