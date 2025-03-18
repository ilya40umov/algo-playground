import hashtable


def main():
    h = hashtable.Hashtable()
    h["a"] = "apple"
    h["b"] = "banana"
    h["c"] = "cherry"
    h["s"] = "strawberry"
    h["t"] = "tomato"
    h["o"] = "orange"
    h["p"] = "peach"
    h["r"] = "raspberry"
    del h["b"]
    del h["o"]
    print("get(a)", h["a"])
    print("get(b)", h["b"])
    print("get(c)", h["c"])
    print("get(s)", h["s"])
    print("get(t)", h["t"])
    print("get(o)", h["o"])
    print("get(p)", h["p"])
    print("get(r)", h["r"])
    print("get(z)", h["z"])


if __name__ == "__main__":
    main()
