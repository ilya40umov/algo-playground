package me.ilya40umov.algo.hashtable;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class Hashtable<K, V> implements Map<K, V> {

    private class Record {
        final K key;
        V value;

        public Record(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final float loadFactor;
    private LinkedList<Record>[] buckets;
    private int size = 0;

    public Hashtable() {
        this(0.7f, 10);
    }

    public Hashtable(float loadFactor, int capacity) {
        this.loadFactor = loadFactor;
        this.buckets = new LinkedList[capacity];
    }

    @Override
    public void clear() {
        for (var bucket : buckets) {
            if (bucket != null) {
                bucket.clear();
            }
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return getRecord(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return Arrays.stream(buckets)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .anyMatch(record -> record.value.equals(value));
    }

    @Override
    public V get(Object key) {
        var record = getRecord(key);
        if (record == null) {
            return null;
        }
        return record.value;
    }

    @Override
    public V put(K key, V value) {
        resizeIfNeeded();
        var record = getRecord(key);
        if (record != null) {
            var oldValue = record.value;
            record.value = value;
            return oldValue;
        }
        // noinspection DataFlowIssue
        getBucketForKey(key, true).add(new Record(key, value));
        size++;
        return null;
    }

    @Override
    public V remove(Object key) {
        var bucket = getBucketForKey(key, false);
        if (bucket == null) {
            return null;
        }
        var iterator = bucket.iterator();
        while (iterator.hasNext()) {
            var record = iterator.next();
            if (record.key.equals(key)) {
                iterator.remove();
                size--;
                return record.value;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    @Override
    public Set<K> keySet() {
        return Arrays.stream(buckets)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .map(record -> record.key)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<V> values() {
        return Arrays.stream(buckets)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .map(record -> record.value)
                .collect(Collectors.toList());
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return Arrays.stream(buckets)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .map(record -> Map.entry(record.key, record.value))
                .collect(Collectors.toSet());
    }

    private Record getRecord(Object key) {
        var bucket = getBucketForKey(key, false);
        if (bucket == null) {
            return null;
        }
        return bucket.stream()
                .filter(record -> record.key.equals(key))
                .findFirst()
                .orElse(null);
    }

    private LinkedList<Record> getBucketForKey(Object key,
                                               boolean createIfMissing) {
        var bucketIndex = key.hashCode() % buckets.length;
        var existingBucket = buckets[bucketIndex];
        if (existingBucket != null) {
            return existingBucket;
        }
        if (createIfMissing) {
            var newBucket = new LinkedList<Record>();
            buckets[bucketIndex] = newBucket;
            return newBucket;
        }
        return null;
    }

    private void resizeIfNeeded() {
        if (1.0f * size / buckets.length < loadFactor) {
            return;
        }
        var oldBuckets = buckets;
        buckets = new LinkedList[oldBuckets.length * 2];

        for (var oldBucket : oldBuckets) {
            if (oldBucket == null) {
                continue;
            }
            for (var record : oldBucket) {
                // noinspection DataFlowIssue
                getBucketForKey(record.key, true).add(record);
            }
        }
    }

    // visible for testing
    int getBucketsSize() {
        return buckets.length;
    }
}
