import quick_sort


def main():
    l = [44, 4, 8, 55, 13, 5, 77, 99, 7, 1, 44, 17, 33, 9, 4]
    print("Unsorted list: ", l)
    quick_sort.sort(l)
    print("Sorted list", l)


if __name__ == "__main__":
    main()
