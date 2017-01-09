package info.qumeric.hw.task4;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TreeTest {
  Tree<Integer> tree = new Tree<>();

  @Test
  public void treeTest() throws Exception {
    assertEquals(0, tree.size());
    assertTrue(tree.add(1));
    assertEquals(1, tree.size());
    assertFalse(tree.add(1));
    assertTrue(tree.add(2));
    assertTrue(tree.contains(1));
    assertFalse(tree.contains(3));
    assertTrue(tree.add(3));
    assertTrue(tree.contains(3));
    assertEquals(3, tree.size());
  }
}