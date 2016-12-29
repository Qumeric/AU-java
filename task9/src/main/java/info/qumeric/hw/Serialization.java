package info.qumeric.hw;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Field;

/**
 * A simple class providing serialization.
 * Assuming that all [de]/serializable objects have only public constructor without parameters and
 * each field either has primitive type or String type
 */
public class Serialization {
  private static final int NULL_LENGTH = -1;

  /**
   * Serialize the object to the given
   * @param object object of some constrained class.
   * @param stream a stream to output.
   * @throws IOException thrown if something is wrong with streams.
   * @throws IllegalAccessException shouldn't be thrown in normal environment.
   */
  public static void serialize(@NotNull Object object, @NotNull OutputStream stream)
          throws IOException, IllegalAccessException {
    Class<?> cls = object.getClass();
    Field[] fields = cls.getDeclaredFields();

    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream))) {
      for (Field field : fields) {
        if (field.getType().getName().indexOf('$') != -1) { // isSynthetic doesn't works for some reason
          System.err.println("Skipping synthetic field");
          continue;
        }
        System.out.println("Serializing field of type " + field.getType());
        field.setAccessible(true);
        Object value = field.get(object);
        if (String.class.isAssignableFrom(field.getType())) {
          if (value == null) {
            writer.write(Integer.toString(NULL_LENGTH));
            continue;
          }
          byte[] bytes = ((String) value).getBytes();
          writer.write(Integer.toString(bytes.length));
          for (byte b : bytes) {
            writer.newLine();
            writer.write(Byte.toString(b));
          }
        } else {
          writer.write(value.toString());
        }
        writer.newLine();
      }
    }
  }

  /**
   * Deserializes object into given class.
   * @param stream a stream to read object from.
   * @param cls a class in which we deserialize.
   * @param <T> type of <code>cls</code>.
   * @return Object of type <code>T</code>
   * @throws InstantiationException shouldn't be thrown in normal environment.
   * @throws IllegalAccessException shouldn't be thrown in normal environment.
   * @throws IOException thrown if something is wrong with streams.
   */
  public static <T> T deserialize(@NotNull InputStream stream, @NotNull Class<T> cls)
          throws InstantiationException, IllegalAccessException, IOException {
    T object;
    Field[] fields = cls.getDeclaredFields();

    object = cls.newInstance();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
      for (Field field : fields) {
        if (field.getType().getName().indexOf('$') != -1) {
          System.out.println("Skipping synthetic field");
          continue;
        }
        System.out.println("Deserializing field of type " + field.getType());
        field.setAccessible(true);
        Class type = field.getType();
        String line = reader.readLine();
        System.out.println("Got line " + line);

        if (type.equals(Boolean.TYPE)) {
          field.setBoolean(object, Boolean.parseBoolean(line));
        } else if (type.equals(Character.TYPE)) {
          field.setChar(object, line.charAt(0));
        } else if (type.equals(Byte.TYPE)) {
          field.setByte(object, Byte.parseByte(line));
        } else if (type.equals(Short.TYPE)) {
          field.setShort(object, Short.parseShort(line));
        } else if (type.equals(Integer.TYPE)) {
          field.setInt(object, Integer.parseInt(line));
        } else if (type.equals(Long.TYPE)) {
          field.set(object, Long.parseLong(line));
        } else if (type.equals(Float.TYPE)) {
          field.setFloat(object, Float.parseFloat(line));
        } else if (type.equals(Double.TYPE)) {
          field.setDouble(object, Double.parseDouble(line));
        } else if (type.equals(String.class)) {
          int length = Integer.parseInt(line);
          if (length == NULL_LENGTH) {
            field.set(object, null);
            continue;
          }
          byte[] bytes = new byte[Integer.parseInt(line)];
          for (int i = 0; i < bytes.length; i++)
            bytes[i] = Byte.parseByte(reader.readLine());
          field.set(object, new String(bytes));
        } else {
          throw new IllegalArgumentException("Unknown field type");
        }
      }
    }
    return object;
  }
}