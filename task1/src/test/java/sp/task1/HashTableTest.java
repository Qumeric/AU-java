package info.qumeric.hw.task1;

import static org.junit.Assert.*;

/**
 * @author Valery Cherepanov <qumidium@gmail.com>
 * @version 1.0
 */
public class HashTableTest {
    private HashTable ht;
    @org.junit.Before
    public void setUp() throws Exception {
        this.ht = new HashTable();
        ht.put("key", "value");
    }

    @org.junit.Test
    public void size() throws Exception {
        assertEquals(1, ht.size());
        ht.remove("key");
        assertEquals(0, ht.size());
    }

    @org.junit.Test
    public void contains() throws Exception {
        assertTrue(ht.contains("key"));
        assertFalse(ht.contains("value"));
    }

    @org.junit.Test
    public void get() throws Exception {
        assertTrue(ht.get("key").equals("value"));
    }

    @org.junit.Test
    public void put() throws Exception {
        assertTrue(ht.contains("key"));
        ht.put("key2", "value2");
        assertTrue(ht.contains("key2"));
    }

    @org.junit.Test
    public void remove() throws Exception {
        ht.remove("key");
        assertFalse(ht.contains("key"));
    }

    @org.junit.Test
    public void clear() throws Exception {
        ht.clear();
        assertFalse(ht.contains("key"));
    }
}