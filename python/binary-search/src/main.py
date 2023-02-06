#!/usr/bin/env python3

import binary_search


def main():
    data = [1, 2, 4, 7, 9, 13, 33, 41, 50, 77, 81, 99]
    print(f"Data: {data}")
    print(f"Position of 81: {binary_search.find(data, 81)}")
    print(f"Position of 65: {binary_search.find(data, 120)}")
    print(f"Position of 7: {binary_search.find(data, 7)}")
    print(f"Position of 3: {binary_search.find(data, 3)}")


if __name__ == "__main__":
    main()
