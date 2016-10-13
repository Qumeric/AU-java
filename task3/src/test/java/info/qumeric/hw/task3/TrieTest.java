package info.qumeric.hw.task3;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class TrieTest {

    private final Trie trie = new Trie();

    @Before
    public void setUp() throws Exception {
        trie.add("aba");
        trie.add("abacaba");
        trie.add("abacabababa");
        trie.add("zzzz");
        trie.add("www");
        trie.add("1W8SH32S)!");
    }

    @Test
    public void serialization() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        trie.serialize(outputStream);
        Trie trie2 = new Trie();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        trie2.deserialize(inputStream);
        assertEquals(6, trie2.size());
        assertTrue(trie.contains("www"));
        assertFalse(trie.contains("abac"));
    }

    @Test
    public void add() throws Exception {
        trie.add("wow");
        assertTrue(trie.contains("wow"));
        trie.add("OwowwQ");
        assertTrue(trie.contains("OwowwQ"));
    }

    @Test
    public void contains() throws Exception {
        assertTrue(trie.contains("www"));
        assertFalse(trie.contains("wwww"));
    }

    @Test
    public void remove() throws Exception {
        trie.remove("aba");
        assertFalse(trie.contains("aba"));
        trie.remove("www");
        assertFalse(trie.contains("www"));
        assertTrue(trie.contains("abacaba"));
    }

    @Test
    public void size() throws Exception {
        int treeSize = trie.size();
        trie.add("randomstring");
        assertEquals(treeSize+1, trie.size());
        Trie emptyTrie = new Trie();
        assertEquals(0, emptyTrie.size());
    }

    @Test
    public void howManyStartsWithPrefix() throws Exception {
        assertEquals(3, trie.howManyStartsWithPrefix("aba"));
        assertEquals(2, trie.howManyStartsWithPrefix("abac"));
        assertEquals(1, trie.howManyStartsWithPrefix("abacabab"));
        assertEquals(0, trie.howManyStartsWithPrefix("abacabababab"));
        assertEquals(trie.size(), trie.howManyStartsWithPrefix(""));

    }

}