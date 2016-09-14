package info.qumeric.task1;
import javafx.util.Pair;

/**
 * @author Valery Cherepanov <qumeric@gmail.com>
 * @version 1.0
 */



public class HashTable {
    /**
     * Number of keys
     */
    public static final int default_capacity = 256;
    private int size, capacity;
    private String[]  key_storage;
    private String[]  value_storage;
    private boolean[] is_deleted;

    public HashTable(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        key_storage = new String[capacity];
        value_storage = new String[capacity];
        is_deleted = new boolean[capacity];
    }
    public HashTable() {
        this(default_capacity);
    }

    private int hash(String s) {
        return s.hashCode() % this.capacity;
    }

    /**
     * Return the number of keys
     */
    public int size() {
        return this.size;
    }

    /**
     * Checks if the given key is containted in the hash table
     *
     * @param key The key
     * @return Whether the key contains in the hash table or not
     */
    public boolean contains(String key) {
        int hash = this.hash(key);
        int i = hash;
        do {
            if (key.equals(key_storage[i]))
                return true;
            i = (i + 1) % this.capacity;
        } while (i != hash);
        return false;
    }

    /**
     * Return a value by the key or null if there are no such value
     *
     * @param key The key
     * @return The value of the key or null
     */
    public String get(String key) {
        int hash = this.hash(key);
        int i = hash;
        Integer first_deleted = null;
        do {
            if (is_deleted[i] && first_deleted == null)
                first_deleted = i;
            if (key.equals(key_storage[i])) {
                if (first_deleted != null) {
                    key_storage[first_deleted] = key_storage[i];
                    value_storage[first_deleted] = value_storage[i];
                    key_storage[i] = null;
                    value_storage[i] = null;
                    return value_storage[first_deleted];
                } else {
                    return value_storage[i];
                }
            }
            i = (i + 1) % this.capacity;
        } while (i != hash);
        return null;
    }

    /**
     * Insert the key with the given value into the hash table
     *
     * @param key The key
     * @param value The corresponding value
     * @return Former value of the key or null if there was no such key before
     */
    public String put(String key, String value) {
        int hash = this.hash(key);
        int i = hash;
        do {
            if (is_deleted[i]) {
                i = (i + 1) % this.capacity;
                continue;
            }
            if (key_storage[i] == null || key_storage[i].isEmpty()) {
                this.size++;
                key_storage[i] = key;
                value_storage[i] = value;
                return null;
            }
            if (key.equals(key_storage[i])) {
                String old_value = value_storage[i];
                value_storage[i] = value;
                return old_value;
            }
            i = (i + 1) % this.capacity;
        } while (i != hash);
        return null;
    }

    /**
     * Removes the key from the hash table
     *
     * @param key The key
     * @return Former value of the key or null if there was no such key
     */
    public String remove(String key) {
        int hash = this.hash(key);
        int i = hash;
        do {
            if (is_deleted[i]) {
                i = (i + 1) % this.capacity;
                continue;
            }
            if (key.equals(key_storage[i])) {
                is_deleted[i] = true;
                String old_value = value_storage[i];
                key_storage[i] = value_storage[i] = null;
                return old_value;
            }
            i = (i + 1) % this.capacity;
        } while (i != hash);
        return null;
    }

    /**
     * Clear the hash table
     */
    void clear() {
        this.size = 0;
        for (int i = 0; i < this.capacity; i++) {
            is_deleted[i] = false;
            key_storage[i] = value_storage[i] = null;
        }
    }
}
