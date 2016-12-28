package info.qumeric.hw.task3;

import java.io.*;
import java.util.HashMap;

/**
 * Implemetation of a simple trie with support of serialization.
 * It doesn't store number of occurrences of particular world.
 */
public class Trie implements Serializable {
  private static class Node {
    boolean isFinal = false;
    Integer howMany = 0;
    HashMap<Character, Node> leafs;

    Node() {
      leafs = new HashMap<Character, Node>();
    }
  }

  private int size = 0;
  private Node root;

  public Trie() {
    root = new Node();
  }

  /**
   * Serialize the trie and write its representation into the OutputStream.
   *
   * @param out OutputStream where function writes.
   * @throws IOException An exception which may be thrown by <b>out</b>.
   */
  public void serialize(OutputStream out) throws IOException {
    ObjectOutputStream oos = new ObjectOutputStream(out);
    oos.writeInt(size);
    serializeRec(root, oos);
    oos.close();
  }

  private void serializeRec(Node node, ObjectOutputStream oos) throws IOException {
    oos.writeInt(node.howMany);
    oos.writeBoolean(node.isFinal);
    oos.writeInt(node.leafs.size());

    for (HashMap.Entry<Character, Node> entry : node.leafs.entrySet()) {
      oos.writeChar(entry.getKey());
      serializeRec(entry.getValue(), oos);
    }
  }

  /**
   * Deserialize the trie from the InputStream and load it into memory.
   *
   * @param in InputStream from which function reads.
   * @throws IOException An exception which may be thrown by <b>in</b>.
   */
  public void deserialize(InputStream in) throws IOException {
    ObjectInputStream ois = new ObjectInputStream(in);
    root = new Node();
    this.size = ois.readInt();
    root = deserializeRec(root, ois);
  }

  private Node deserializeRec(Node node, ObjectInputStream ois) throws IOException {
    node.howMany = ois.readInt();
    node.isFinal = ois.readBoolean();
    int numberOfLeaves = ois.readInt();
    for (int i = 0; i < numberOfLeaves; i++) {
      Character letter = ois.readChar();
      node.leafs.put(letter, deserializeRec(new Node(), ois));
    }
    return node;
  }

  /**
   * Add a string to the trie.
   * Complexity: O(|element|)
   *
   * @param element A String to add.
   * @return <b>true</b> if there was no such element in the trie.
   */
  boolean add(String element) {
    if (contains(element)) {
      return false;
    }

    Node node = root;
    node.howMany++;
    for (int i = 0; i < element.length(); i++) {
      final Character letter = element.charAt(i);
      if (node.leafs.containsKey(letter)) {
        node = node.leafs.get(letter);
      } else {
        Node next = new Node();
        node.leafs.put(element.charAt(i), next);
        node = next;
      }
      node.howMany += 1;
    }

    size++;
    node.isFinal = true;
    return true;
  }


  /**
   * Checks whether the trie contains the element.
   * Complexity: O(|element|)
   *
   * @param element A String to check for.
   * @return <b>true</b> if there is such element in the trie.
   */
  boolean contains(String element) {
    Node node = root;
    for (int i = 0; i < element.length(); i++) {
      final Character letter = element.charAt(i);
      if (node.leafs.containsKey(letter)) {
        node = node.leafs.get(letter);
      } else {
        return false;
      }
    }
    return node.isFinal;
  }

  /**
   * Remove an elemenet from the trie.
   * Complexity: O(|element|)
   *
   * @param element A string to remove.
   * @return <b>true</b> if something was deleted.
   */
  boolean remove(String element) {
    if (!contains(element))
      return false;

    Node node = root;
    node.howMany--;
    for (int i = 0; i < element.length(); i++) {
      final Character letter = element.charAt(i);
      if (node.leafs.containsKey(letter)) {
        node = node.leafs.get(letter);
      } else {
        return false;
      }
      node.howMany--;
    }

    size--;
    node.isFinal = false;
    return true;
  }

  /**
   * Get the size of the trie.
   * Complexity: O(1)
   *
   * @return size.
   */
  int size() {
    return size;
  }

  /**
   * Get amount of strings which start which start with given prefix.
   * Complexity: O(|prefix|).
   *
   * @param prefix a prefix to check
   * @return number of strings in this trie which starts with prefix
   */
  int howManyStartsWithPrefix(String prefix) {
    Node node = root;
    for (int i = 0; i < prefix.length(); i++) {
      final Character letter = prefix.charAt(i);
      if (node.leafs.containsKey(letter)) {
        node = node.leafs.get(letter);
      } else {
        return 0;
      }
    }
    return node.howMany;
  }
}
