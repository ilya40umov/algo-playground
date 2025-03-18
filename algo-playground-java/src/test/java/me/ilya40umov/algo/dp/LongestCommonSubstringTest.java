package me.ilya40umov.algo.dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestCommonSubstringTest {

    @ParameterizedTest
    @MethodSource("longestCommonSubstringProblems")
    void find_shouldReturnLongestCommonSubstring(
            String substr,
            String str1,
            String str2
    ) {
        assertEquals(substr, LongestCommonSubstring.find(str1, str2));
    }

    public static Stream<Arguments> longestCommonSubstringProblems() {
        return Stream.of(
                Arguments.of("", "ABC", "DEF"),
                Arguments.of("A", "ABC", "ADE"),
                Arguments.of("BC", "ABC", "ADBC"),
                Arguments.of("CDEFGH", "ABCDEFGHF", "YIDEGABCDOCDEFGH")
        );
    }

}