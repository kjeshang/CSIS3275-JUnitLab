import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

import static org.junit.Assert.*;

public class MatrixHelixMorphTest {

    static int testCount;

    @BeforeAll
    public static void start(){
        System.out.println("Test started");
        testCount = 1;
    }

    @BeforeEach
    public void setup(){
        System.out.println("Test " + testCount + " started");
    }

    /** Check if two integer matrices have the same contents
     *
     * @param matrix1
     * @param matrix2
     * @return true if the contents of matrix1 and matrix2 are identical and false otherwise
     */
    public static boolean testEqual(int[][] matrix1, int[][] matrix2) {
        /*
            Basic sanity checking:
                Verify that lengths are equal
         */
        if (matrix1 == null || matrix2 == null) {
            return true;
        }
        if (matrix1.length != matrix2.length) {
            return false;
        }
        if (matrix1[0].length != matrix2[0].length) {
            return false;
        }

        /*
            Test the contents of the matrices
         */
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                if (matrix1[i][j] != matrix2[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    @Test
    public void test2x2() {
        int[][] inMatrix  = { {1, 2}, {3, 4} };
        int[][] expOutput = { {1, 2}, {4, 3} };
        assertTrue(testEqual(expOutput, MatrixHelixMorph.helix(inMatrix)));
    }

    @Test
    public void test1x1() {
        int[][] inMatrix  = { {1} };
        int[][] expOutput = { {1} };
        assertTrue(testEqual(expOutput, MatrixHelixMorph.helix(inMatrix)));
    }

    // ADD TESTS TO CHECK PARTITIONS

    private static void display(int[][] matrix){
        for(int x = 0; x < matrix.length; x++){
            for(int y = 0; y < matrix[0].length; y++){
                System.out.print(matrix[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // First given example in ReadMe = 3x3:
    @Test
    public void example1(){
        int[][] inMatrix1 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] outMatrix1 = {{1,2,3},{8,9,4},{7,6,5}};
        System.out.println("Helix");
        display(outMatrix1);
        assertTrue(testEqual(outMatrix1, MatrixHelixMorph.helix(inMatrix1)));
    }

    // Second Given example in ReadMe = 4x5:
    @Test
    public void example2(){
        int[][] inMatrix2 = {{7,5,1,4,6},{3,2,9,1,8},{8,3,2,4,1},{10,11,15,19,14}};
        int[][] outMatrix2 = {{7,5,1,4,6},{4,1,10,11,3},{2,14,19,15,2},{3,8,8,1,9}};
        System.out.println("Helix");
        display(outMatrix2);
        assertTrue(testEqual(outMatrix2, MatrixHelixMorph.helix(inMatrix2)));
    }

    // Check upper left corner of helix matrix:
    @Test
    public void upperLeft(){
        int[][] inMatrix1 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] outMatrix1 = MatrixHelixMorph.helix(inMatrix1);
        assertEquals(outMatrix1[0][0],1);
        int[][] inMatrix2 = {{7,5,1,4,6},{3,2,9,1,8},{8,3,2,4,1},{10,11,15,19,14}};
        int[][] outMatrix2 = MatrixHelixMorph.helix(inMatrix2);
        assertEquals(outMatrix2[0][0],7);
    }

    // Check upper right corner of helix matrix:
    @Test
    public void upperRight(){
        int[][] inMatrix1 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] outMatrix1 = MatrixHelixMorph.helix(inMatrix1);
        assertEquals(outMatrix1[0][outMatrix1[0].length-1],3);
        int[][] inMatrix2 = {{7,5,1,4,6},{3,2,9,1,8},{8,3,2,4,1},{10,11,15,19,14}};
        int[][] outMatrix2 = MatrixHelixMorph.helix(inMatrix2);
        assertEquals(outMatrix2[0][outMatrix2[0].length-1],6);
    }

    // Check lower right corner of helix matrix:
    @Test
    public void lowerRight(){
        int[][] inMatrix1 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] outMatrix1 = MatrixHelixMorph.helix(inMatrix1);
        assertEquals(outMatrix1[outMatrix1.length - 1][outMatrix1[0].length - 1],5);
        int[][] inMatrix2 = {{7,5,1,4,6},{3,2,9,1,8},{8,3,2,4,1},{10,11,15,19,14}};
        int[][] outMatrix2 = MatrixHelixMorph.helix(inMatrix2);
        assertEquals(outMatrix2[outMatrix2.length-1][outMatrix2[0].length-1],9);
    }

    // Check lower left corner of helix matrix:
    @Test
    public void lowerLeft(){
        int[][] inMatrix1 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] outMatrix1 = MatrixHelixMorph.helix(inMatrix1);
        assertEquals(outMatrix1[outMatrix1.length - 1][0],7);
        int[][] inMatrix2 = {{7,5,1,4,6},{3,2,9,1,8},{8,3,2,4,1},{10,11,15,19,14}};
        int[][] outMatrix2 = MatrixHelixMorph.helix(inMatrix2);
        assertEquals(outMatrix2[outMatrix2.length-1][0],3);
    }

    // Check middle value of 3x3 spiral matrix:
    private static double findMiddle(int[][] outMatrix){
        int[] list = new int[outMatrix.length*outMatrix[0].length];
        int listPos = 0;
        for(int i = 0 ; i < outMatrix.length; i++) {
            for(int j = 0; j < outMatrix[i].length; j++) {
                list[listPos++] = outMatrix[i][j];
            }
        }
        int middle = list.length/2;
        if ((list.length%2) == 1) {
            return list[middle];
        }
        return (list[middle-1] + list[middle]) / 2.0;
    }

    @Test
    public void middle(){
        int[][] inMatrix1 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] outMatrix1 = MatrixHelixMorph.helix(inMatrix1);
        assertEquals((int)findMiddle(outMatrix1),9);
    }

    // Test empty 0x0 spiral matrix (as per ReadMe):
    @Test
    public void emptyMatrix(){
        int[][] inMatrix1 = {{}};
        int[][] outMatrix1 = MatrixHelixMorph.helix(inMatrix1);
        System.out.println("Helix");
        display(outMatrix1);
        assertTrue(testEqual(outMatrix1, MatrixHelixMorph.helix(inMatrix1)));
        int[][] inMatrix2 = {{},{},{}};
        int[][] outMatrix2 = MatrixHelixMorph.helix(inMatrix2);
        System.out.println("Helix");
        display(outMatrix2);
        assertTrue(testEqual(outMatrix2, MatrixHelixMorph.helix(inMatrix2)));
    }

    // Check 2 x 7 matrix:
    @Test
    public void test2x7(){
        int[][] inMatrix = {{1,2,3,4,5,6,7},{1,2,3,4,5,6,7}};
        int[][] outMatrix2 = {{1,2,3,4,5,6,7},{7,6,5,4,3,2,1}};
        System.out.println("Helix");
        display(outMatrix2);
        assertTrue(testEqual(outMatrix2, MatrixHelixMorph.helix(inMatrix)));
    }

    // Check if 3x3 and 4x5 helix-morph forms are different from their initial matrix as strings:
    @Test
    public void testAsStrings(){
        int[][] inMatrix1 = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println("Matrix: " + inMatrix1.toString());
        int[][] outMatrix1 = MatrixHelixMorph.helix(inMatrix1);
        System.out.println("Helix: " + outMatrix1);
        assertFalse(inMatrix1.toString().equals(outMatrix1));
        int[][] inMatrix2 = {{7,5,1,4,6},{3,2,9,1,8},{8,3,2,4,1},{10,11,15,19,14}};
        System.out.println("\nMatrix: " + inMatrix2.toString());
        int[][] outMatrix2 = {{7,5,1,4,6},{4,1,10,11,3},{2,14,19,15,2},{3,8,8,1,9}};
        System.out.println("Helix: " + outMatrix2);
        assertFalse(inMatrix2.toString().equals(outMatrix2));
    }
    // Check if randomly generated matrix (as string) equals helix (as string):
    private static int MAX = 10; // <- value can be manually changed but a very large number will throw an error

    private static int[][] createMatrix(){
        int[][] matrix = null;
        Random random = new Random();
        int row = random.nextInt(MAX - 1) + 1;
        System.out.println("Rows: " + row);
        int col = random.nextInt(MAX - 1) + 1;
        System.out.println("Columns: " + col);
        matrix = new int[row][col];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                matrix[i][j] = random.nextInt();
            }
        }
        System.out.println();
        return matrix;
    }

    @Test
    public void testRandomMatrix(){
        int[][] inMatrix = createMatrix();
        System.out.println("Matrix");
        display(inMatrix);
        int[][] outMatrix = MatrixHelixMorph.helix(inMatrix);
        System.out.println("Helix");
        display(outMatrix);
        assertFalse(inMatrix.toString().equals(outMatrix));
    }

    // Test null matrix (as per ReadMe); null matrix becomes empty helix to avoid throwing error:
    @Test
    public void testNullMatrix(){
        int[][] inMatrix = null;
        int[][] outMatrix = MatrixHelixMorph.helix(inMatrix);
        display(outMatrix);
        assertNotSame(inMatrix,outMatrix);
        assertNotEquals(inMatrix,outMatrix);
        assertNotNull(outMatrix);
    }

    // Test helix if initial matrix that did not have equal number of elements per row/column; invalid matrix becomes empty helix to avoid throwing error:
    @Test
    public void invalidMatrix(){
        int[][] inMatrix = {{1,2,3},{1},{1,2}};
        int[][] outMatrix = MatrixHelixMorph.helix(inMatrix);
        display(outMatrix);
        assertNotEquals(inMatrix,outMatrix);
        assertFalse(testEqual(inMatrix,outMatrix));
    }

    // Test 2 x 10 matrix:
    @Test
    public void test2x10(){
        int[][] inMatrix = {{1,2},{3,4},{5,6},{7,8},{9,10},{11,12},{13,14},{15,16},{17,18},{19,20}};
        int[][] expOutput = {{1,2},{20,3},{19,4},{18,5},{17,6},{16,7},{15,8},{14,9},{13,10},{12,11}};
        assertTrue(testEqual(expOutput,MatrixHelixMorph.helix(inMatrix)));
    }

    // Test 1 x 3 matrix:
    @Test
    public void test1x3(){
        int[][] inMatrix = {{1},{2},{3}};
        int[][] expOutput = {{1},{2},{3}};
        assertTrue(testEqual(expOutput,MatrixHelixMorph.helix(inMatrix)));
    }

    // Test 3 x 1 matrix:
    @Test
    public void test3x1(){
        int[][] inMatrix = {{1,2,3}};
        int[][] expOutput = {{1,2,3}};
        assertTrue(testEqual(expOutput,MatrixHelixMorph.helix(inMatrix)));
    }

    @Test
    public void test2x3(){
        int[][] inMatrix = {{1,2},{3,4},{5,6}};
        int[][] expOutput = {{1,2},{6,3},{5,4}};
        assertTrue(testEqual(expOutput,MatrixHelixMorph.helix(inMatrix)));
    }

    @AfterEach
    public void tearDown(){
        System.out.println("Test " + testCount + " completed");
        testCount++;
    }

    @AfterAll
    public static void tearDownAll(){
        System.out.println("All tests completed");
    }
}
