# constants
HEADERS = {'User-Agent': 'Mozilla/5.0'}


# functions
def search_first_index(keys_of_old, keys_of_new):
    for new in keys_of_new:
        for old in keys_of_old:
            if new == old:
                return keys_of_new.index(new)

    return None


def remove_krw(price):
    return price.replace("KRW", "").strip()


def remove_won_symbol(price):
    return price.replace("w", "").strip()
