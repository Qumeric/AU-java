package info.qumeric.hw.task2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


/**
 * @author Valery Cherepanov <qumidium@gmail.com>
 * @version 1.0
 */
public class MatrixTest {
    private Matrix matrix3, matrix5;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        // Use streams for I/O testing
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        int[][] matrix3 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrix5 = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };

        this.matrix3 = new Matrix(matrix3);
        this.matrix5 = new Matrix(matrix5);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void sortByColumns() throws Exception {
        matrix3 = new Matrix(this.matrix3);
        matrix3.sortByColumns();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(matrix3.get(i, 3 - j - 1), i * 3 + j + 1);
            }
        }
    }

    @Test
    public void printSpiral() throws Exception {
        matrix3.printSpiral();
        assertEquals("5 2 3 6 9 8 7 4 1\n", outContent.toString());

        outContent.reset();

        matrix5.printSpiral();
        assertEquals("13 8 9 14 19 18 17 12 7 2 3 4 5 10 15 20 25 24 23 22 21 16 11 6 1\n", outContent.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void notSquaredMatrix() throws Exception {
        int[][] matrix23 = {{1, 2, 3}, {4, 5, 6}};
        new Matrix(matrix23);
    }

    @Test(expected = IllegalArgumentException.class)
    public void oddMatrix() throws Exception {
        int[][] matrix4 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        new Matrix(matrix4);
    }
}