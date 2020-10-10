# constants
LIMIT_NUMBER_OF_OLD_ITEM = 10
LIMIT_NUMBER_OF_ITEM_IN_POST = 100
HEADERS = {'User-Agent': 'Mozilla/5.0'}


# functions
def search_first_index(keys_of_old, keys_of_new):
    for new in keys_of_new:
        for old in keys_of_old:
            if new == old:
                return keys_of_new.index(new)

    return None
