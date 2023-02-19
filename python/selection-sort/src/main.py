import selection_sort


def main():
    l = [44, 8, 13, 5, 99, 7, 1]
    print("Unsorted list: ", l)
    selection_sort.sort(l)
    print("Sorted list", l)


if __name__ == "__main__":
    main()
