package info.qumeric.hw;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SerializationTest {
  @Test
  public void serializeAndDeserializeTest() throws Exception {
    // General test
    TestClass1 obj = new TestClass1(false, 225, 1. / 117, "test string\nwith two lines\n");
    ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
    Serialization.serialize(obj, outputStream1);
    ByteArrayInputStream inputStream1 = new ByteArrayInputStream(outputStream1.toByteArray());
    TestClass1 deserializedObj = Serialization.deserialize(inputStream1, TestClass1.class);

    assertNotNull(deserializedObj);
    assertEquals(obj.aBoolean, deserializedObj.aBoolean);
    assertEquals(obj.anInt, deserializedObj.anInt);
    assertEquals(obj.aDouble, deserializedObj.aDouble, 1e-9);
    assertEquals(obj.string, deserializedObj.string);

    // Null string test
    obj.string = null;
    ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
    Serialization.serialize(obj, outputStream2);
    ByteArrayInputStream inputStream2 = new ByteArrayInputStream(outputStream2.toByteArray());
    deserializedObj = Serialization.deserialize(inputStream2, TestClass1.class);

    assertEquals(null, deserializedObj.string);
  }

  private static class TestClass1 {
    boolean aBoolean;
    int anInt;
    double aDouble;
    String string;
    private MyInner inner; // to create synthetic field

    public TestClass1() {
    }

    public TestClass1(boolean aBoolean, int anInt, double aDouble, String string) {
      this.aBoolean = aBoolean;
      this.anInt = anInt;
      this.aDouble = aDouble;
      this.string = string;
    }

    private class MyInner {
    }
  }
}
