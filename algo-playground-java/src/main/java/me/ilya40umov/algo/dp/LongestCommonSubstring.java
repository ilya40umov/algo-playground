package me.ilya40umov.algo.dp;

public class LongestCommonSubstring {
    public static String find(String str1, String str2) {
        /* ABC, ADBC => BC
          | A | B | C |
        A | 1 | 0 | 0 |
        D | 0 | 0 | 0 |
        B | 0 | 1 | 0 |
        C | 0 | 0 | 2 |
         */
        var grid = new int[str2.length()][str1.length()];
        var maxStrLen = 0;
        var maxStrLeftIdx = 0;
        var maxStrRightIdx = 0;
        for (int i = 0; i < str2.length(); i++) {
            for (int j = 0; j < str1.length(); j++) {
                if (str1.charAt(j) == str2.charAt(i)) {
                    var strLen = 1 + ((i > 0 && j > 0) ? grid[i - 1][j - 1] : 0);
                    grid[i][j] = strLen;
                    if (strLen > maxStrLen) {
                        maxStrLen = strLen;
                        maxStrRightIdx = j;
                        maxStrLeftIdx = j - (strLen - 1);
                    }
                }
            }
        }
        return maxStrLen > 0 ? str1.substring(maxStrLeftIdx, maxStrRightIdx + 1) : "";
    }
}
