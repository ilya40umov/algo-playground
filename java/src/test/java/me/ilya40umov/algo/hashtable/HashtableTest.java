package me.ilya40umov.algo.hashtable;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"ConstantValue", "RedundantOperationOnEmptyContainer"})
class HashtableTest {

    @Test
    void clear_shouldRemoveAllRecords() {
        var hashtable = new Hashtable<String, Integer>();
        hashtable.put("A", 1);
        hashtable.put("B", 2);

        hashtable.clear();

        assertTrue(hashtable.isEmpty());
        assertNull(hashtable.get("A"));
        assertNull(hashtable.get("B"));
    }

    @Test
    void size_shouldReturnZero_whenHashTableIsEmpty() {
        assertEquals(0, new Hashtable<String, Integer>().size());
    }

    @Test
    void size_shouldReturnNumberOfRecords_whenHashTableIsNotEmpty() {
        var hashtable = new Hashtable<String, Integer>();
        hashtable.put("A", 1);
        hashtable.put("B", 2);

        assertEquals(2, hashtable.size());
    }

    @Test
    void isEmpty_shouldReturnTrue_whenHashTableIsEmpty() {
        assertTrue(new Hashtable<String, Integer>().isEmpty());
    }

    @Test
    void isEmpty_shouldReturnFalse_whenHashTableIsNotEmpty() {
        var hashtable = new Hashtable<String, Integer>();
        hashtable.put("A", 1);
        hashtable.put("B", 2);

        assertFalse(hashtable.isEmpty());
    }

    @Test
    void containsKey_shouldReturnFalse_whenKeyIsNotPresent() {
        var hashtable = new Hashtable<String, Integer>();
        hashtable.put("A", 1);
        hashtable.put("B", 1);

        assertFalse(hashtable.containsKey("C"));
    }

    @Test
    void containsKey_shouldReturnTrue_whenKeyIsPresent() {
        var hashtable = new Hashtable<String, Integer>();
        hashtable.put("A", 1);
        hashtable.put("B", 2);

        assertTrue(hashtable.containsKey("A"));
    }

    @Test
    void containsValue_shouldReturnFalse_whenValueIsNotPresent() {
        var hashtable = new Hashtable<String, Integer>();
        hashtable.put("A", 1);
        hashtable.put("B", 2);

        assertFalse(hashtable.containsValue(33));
    }

    @Test
    void containsValue_shouldReturnTrue_whenValueIsPresent() {
        var hashtable = new Hashtable<String, Integer>();
        hashtable.put("A", 1);
        hashtable.put("B", 2);

        assertTrue(hashtable.containsValue(2));
    }

    @Test
    void get_shouldReturnNull_whenKeyIsNotPresent() {
        var hashtable = new Hashtable<String, Integer>();
        hashtable.put("A", 1);
        hashtable.put("B", 2);

        assertNull(hashtable.get("C"));
    }

    @Test
    void get_shouldReturnStoredValue_whenKeyIsPresent() {
        var hashtable = new Hashtable<String, Integer>();
        hashtable.put("A", 1);
        hashtable.put("B", 2);

        assertEquals(2, hashtable.get("B"));
    }

    @Test
    void put_shouldReturnNull_whenNewKeyIsAdded() {
        var hashtable = new Hashtable<String, Integer>();
        hashtable.put("A", 1);
        hashtable.put("B", 2);

        assertNull(hashtable.put("C", 3));

        assertEquals(3, hashtable.get("C"));
    }

    @Test
    void put_shouldReturnStoredValue_whenExistingKeyIsOverwritten() {
        var hashtable = new Hashtable<String, Integer>();
        hashtable.put("A", 1);
        hashtable.put("B", 2);

        assertEquals(2, hashtable.put("B", 22));

        assertEquals(22, hashtable.get("B"));
    }

    @Test
    void put_shouldGrowNumberOfBuckets_whenLoadFactorIsReached() {
        var hashtable = new Hashtable<String, Integer>();
        IntStream.range(0, 30).forEach(i -> hashtable.put(String.valueOf(i), i));

        assertEquals(30, hashtable.size());
        assertEquals(80, hashtable.getBucketsSize());
    }

    @Test
    void remove_shouldReturnNull_whenKeyDoesNotExist() {
        var hashtable = new Hashtable<String, Integer>();
        hashtable.put("A", 1);
        hashtable.put("B", 2);

        assertNull(hashtable.remove("C"));

        assertNull(hashtable.get("C"));
    }

    @Test
    void remove_shouldReturnStoredValue_whenKeyExists() {
        var hashtable = new Hashtable<String, Integer>();
        hashtable.put("A", 1);
        hashtable.put("B", 2);

        assertEquals(2, hashtable.remove("B"));

        assertNull(hashtable.get("B"));
    }

    @Test
    void putAll_shouldAddAllProvidedKeyValuePairs() {
        var hashtable = new Hashtable<String, Integer>();
        hashtable.putAll(
                IntStream.range(0, 5)
                        .boxed()
                        .collect(Collectors.toMap(String::valueOf, Function.identity()))
        );

        assertEquals(0, hashtable.get("0"));
        assertEquals(4, hashtable.get("4"));
    }

    @Test
    void keySet_shouldReturnSetWithAllKeys() {
        var hashtable = new Hashtable<String, Integer>();
        hashtable.put("A", 1);
        hashtable.put("B", 2);

        assertEquals(new HashSet<>(Arrays.asList("A", "B")), hashtable.keySet());
    }

    @Test
    void values_shouldReturnCollectionWithAllValues() {
        var hashtable = new Hashtable<String, Integer>();
        hashtable.put("A", 1);
        hashtable.put("B", 2);

        assertEquals(Arrays.asList(1, 2), hashtable.values());
    }

    @Test
    void entrySet_shouldReturnSetWithAllEntries() {
        var hashtable = new Hashtable<String, Integer>();
        hashtable.put("A", 1);
        hashtable.put("B", 2);

        assertEquals(
                new HashSet<>(Arrays.asList(Map.entry("A", 1), Map.entry("B", 2))),
                hashtable.entrySet()
        );
    }
}