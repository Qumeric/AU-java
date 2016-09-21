package info.qumeric.task1;

/**
 * @author Valery Cherepanov <qumeric@gmail.com>
 * @version 1.0
 */

public class HTTester {
    public static void main(String[] args) {
        HashTable ht = new HashTable();
        System.out.println(ht.size());
        String key = "key";
        String value = "value";
        System.out.format("Ht contains key: %b\n", ht.contains(key));
        ht.put(key, value);
        System.out.format("Ht contains key after putting: %b\n", ht.contains(key));
        System.out.format("Value of key after putting: %s\n", ht.get(key));
        ht.remove(key);
        System.out.format("Ht contains key after removing: %b\n", ht.contains(key));
        System.out.format("Value of key after removing: %s\n", ht.get(key));
    }
}


