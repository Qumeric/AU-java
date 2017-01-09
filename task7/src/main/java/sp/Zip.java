package sp;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Find all zip archives by a given path (first line in stdin) and extract all files from these archives which names
 * matching a given regular expression (second line in stdin).
 */
public class Zip {
  private final static int BUFFER_SIZE = 4096;

  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(System.in);
    Path path = Paths.get(s.nextLine());
    Pattern regexp = Pattern.compile(s.nextLine());

    try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
      for (Path file: stream) {
        try (ZipFile zipFile = new ZipFile(file.toFile())) {
          Enumeration<? extends ZipEntry> entries = zipFile.entries();

          while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if (regexp.matcher(entry.getName()).matches()) {
              InputStream is = zipFile.getInputStream(entry);

              String filePath = path.toString() + File.separator + entry.getName();
              System.out.println("Extract " + entry.getName() + " from " + zipFile.getName() + " to " + filePath);

              BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));

              byte[] bytesIn = new byte[BUFFER_SIZE];
              int read;
              while ((read = is.read(bytesIn)) != -1) {
                  bos.write(bytesIn, 0, read);
              }
              bos.close();
            }
          }
        } catch (IOException e) {
          // Do nothing if file isn't a zip archive
        }
      }
    }
  }
}
