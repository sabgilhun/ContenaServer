def extract_new_arrival_item(old_items, new_items):
    if old_items is None:
        old_items = list()

    if new_items is None:
        new_items = list()

    return list(filter(lambda x: x not in old_items, new_items))


def create_new_arrival_post_model(shop_name, new_items):
    if not new_items:
        return new_items
    else:
        post_dict = dict()
        post_dict['shop_name'] = shop_name
        post_dict['logo'] = 'dummy'  # TODO 사이트 로고 스크래핑 할 방식 고민해서 적용해야함
        post_dict['new_item'] = new_items
        return post_dict
