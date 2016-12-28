package info.qumeric.hw.task2;


/**
 * @author Valery Cherepanov <qumidium@gmail.com>
 * @version 1.0
 */
public class Matrix {
    /**
     * Size of the matrix
     */
    private int n;
    /**
     * The matrix itself
     */
    private int[][] matrix;


    /**
     * Create a matrix with given size
     *
     * @param n size
     */
    public Matrix(int n) throws IllegalArgumentException {
        if (n % 2 == 0 || n <= 0) {
            throw new IllegalArgumentException("n should be an odd positive integer.");
        }
        matrix = new int[n][n];
    }

    /**
     * Create a matrix from list of lists
     *
     * @param matrix two dimensional array
     */
    public Matrix(int[][] matrix) throws IllegalArgumentException {
        int rowsCount = matrix.length;

        if (rowsCount % 2 == 0 || rowsCount <= 0) {
            throw new IllegalArgumentException("n should be an odd positive integer.");
        }

        // Sanity check
        if (matrix[0].length != rowsCount) {
            throw new IllegalArgumentException("Given list should has n rows and n column.");
        }

        this.n = rowsCount;
        this.matrix = matrix;
    }

    /**
     * Copy constructor
     *
     * @param matrix another instance of Matrix
     */
    public Matrix(Matrix matrix) {
        this.n = matrix.n;
        this.matrix = matrix.matrix;
    }

    private void swapColumns(int a, int b) {
        for (int i = 0; i < n; i++) {
            int tmp = matrix[i][a];
            matrix[i][a] = matrix[i][b];
            matrix[i][b] = tmp;
        }
    }

    /**
     * Sort matrix columns in <b>decreasing</b> order according to the first element in each column
     */
    public void sortByColumns() {
        // Why bother if selection sort is good enough?
        for (int i = 0; i < n; i++) {
            int maxPos = i;
            for (int j = i + 1; j < n; j++) {
                if (matrix[0][j] > matrix[0][maxPos]) {
                    maxPos = j;
                }
            }
            swapColumns(i, maxPos);
        }
    }

    /**
     * Return element by given row and column.
     *
     * @param row Row number
     * @param col Column number
     */

    public int get(int row, int col) {
        return matrix[row][col];
    }

    /**
     * Print all elements of the matrix in spiral order starting from the center
     */
    public void printSpiral() {
        int row = n / 2;
        int col = n / 2;

        // Length of next straight segment of the spiral
        int stepSize = 1;

        // After every other straight segment we should increase length of the next one
        Boolean updateSize = false;

        // First element is direction by x-axis (columns), second is direction by y-axis (rows)
        int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

        // Position in directions array
        int currentDirection = 0;

        // How many value we've printed
        int stepsMade = 0;

        /*
         * 1. The algorithm is pretty straightforward:
         * 2. Start from the center
         * 3. Make 1 step up
         * 4. Turn right
         * 5. Make 1 step right
         * 6. Turn down
         * 7. We've made <b><two</b> steps so we should increase length of the next segment
         * 8. Make 2 steps down
         * 9. Turn left
         * 10. ...
         */
        System.out.print(matrix[row][col]);
        outer:
        while (stepsMade < n * n) {
            for (int j = 0; j < stepSize; j++) {
                if (stepsMade > 0) {
                    System.out.print(" " + matrix[row][col]);
                }
                stepsMade++;
                if (stepsMade >= n * n) {
                    break outer;
                }
                row += directions[currentDirection][1];
                col += directions[currentDirection][0];
            }
            currentDirection = (currentDirection + 1) % directions.length;
            if (updateSize) {
                stepSize++;
                updateSize = false;
            } else {
                updateSize = true;
            }
        }
        System.out.println();
    }
}
