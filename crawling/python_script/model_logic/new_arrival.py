def extract_new_arrival_item(old_items, new_items):
    if old_items is None:
        old_items = list()

    if new_items is None:
        new_items = list()

    return list(filter(lambda x: x not in old_items, new_items))
