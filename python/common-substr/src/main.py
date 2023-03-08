from substr import longest_common_substring


def main():
    str1 = "abcdefvgh1ebtxdevgh"
    str2 = "xcdeydvgh1eabcde1bt"
    substr = longest_common_substring(str1, str2)
    color_coded_substr = f"\033[94m{substr}\033[0m"
    print("String 1:", str1.replace(substr, color_coded_substr))
    print("String 2:", str2.replace(substr, color_coded_substr))
    print("Longest common substring:", color_coded_substr)


if __name__ == "__main__":
    main()
