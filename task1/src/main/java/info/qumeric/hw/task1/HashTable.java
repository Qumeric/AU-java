package info.qumeric.hw.task1;


public class HashTable {
  /** Number of keys */
  private static final int DEFAULT_CAPACITY = 256;
  private int size;
  private int capacity;
  private String[] keyStorage;
  private String[] valueStorage;
  private boolean[] isDeleted;

  public HashTable(int capacity) {
    this.size = 0;
    this.capacity = capacity;
    keyStorage = new String[capacity];
    valueStorage = new String[capacity];
    isDeleted = new boolean[capacity];
  }

  public HashTable() {
    this(DEFAULT_CAPACITY);
  }

  private int hash(String s) {
    return Math.abs(s.hashCode()) % this.capacity;
  }

  /**
   * Return the number of keys
   */
  public int size() {
    return this.size;
  }

  /**
   * Checks if the given key is contained in the hash table
   *
   * @param key The key
   * @return Whether the key is contained in the hash table or not
   */
  public boolean contains(String key) {
    int hash = this.hash(key);
    int i = hash;
    do {
      if (key.equals(keyStorage[i]))
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
    Integer firstDeleted = null;
    do {
      if (isDeleted[i] && firstDeleted == null)
        firstDeleted = i;
      if (key.equals(keyStorage[i])) {
        if (firstDeleted != null) {
          keyStorage[firstDeleted] = keyStorage[i];
          valueStorage[firstDeleted] = valueStorage[i];
          keyStorage[i] = null;
          valueStorage[i] = null;
          return valueStorage[firstDeleted];
        } else {
          return valueStorage[i];
        }
      }
      i = (i + 1) % this.capacity;
    } while (i != hash);
    return null;
  }

  /**
   * Insert the key with the given value into the hash table
   *
   * @param key   The key
   * @param value The corresponding value
   * @return Former value of the key or null if there was no such key before
   */
  public String put(String key, String value) {
    int hash = this.hash(key);
    int i = hash;
    do {
      if (isDeleted[i]) {
        i = (i + 1) % this.capacity;
        continue;
      }
      if (keyStorage[i] == null || keyStorage[i].isEmpty()) {
        this.size++;
        keyStorage[i] = key;
        valueStorage[i] = value;
        return null;
      }
      if (key.equals(keyStorage[i])) {
        String oldValue = valueStorage[i];
        valueStorage[i] = value;
        return oldValue;
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
      if (isDeleted[i]) {
        i = (i + 1) % this.capacity;
        continue;
      }
      if (key.equals(keyStorage[i])) {
        isDeleted[i] = true;
        String oldValue = valueStorage[i];
        keyStorage[i] = valueStorage[i] = null;
        this.size--;
        return oldValue;
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
      isDeleted[i] = false;
      keyStorage[i] = null;
      valueStorage[i] = null;
    }
  }
}
