import java.util.Random;

public class MatrixHelixMorph {

    /**
     * @param inMatrix
     * @return a matrix that is morphed into a helix version of inMatrix
     */
//    public static int[][] helix( int[][] inMatrix) {
//        //MODIFY THIS METHOD
//        int rows = inMatrix.length;
//        int cols = inMatrix[0].length;
//        if(rows <= 1 || cols <= 1 || inMatrix == null){
//            // Do nothing
//        }
//        else{
//            int[] temp = convertTo1D(inMatrix, rows, cols);
//            spiralOrder(rows, cols, temp, inMatrix);
//        }
//        return inMatrix;
//    }

    public static int[][] helix( int[][] inMatrix) {
        //MODIFY THIS METHOD
        int rows, cols;
        int[] temp;
        try{
            rows = inMatrix.length;
            cols = inMatrix[0].length;
            if(rows <= 1 || cols <= 1 || inMatrix == null){
                // Do nothing
            }
            else{
                temp = convertTo1D(inMatrix, rows, cols);
                spiralOrder(rows, cols, temp, inMatrix);
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
            //System.out.println(e.getCause());
            inMatrix = new int[][]{{}};
        }
        catch(NullPointerException e){
            //System.out.println(e.getCause());
            inMatrix = new int[][]{{}};
        }
        return inMatrix;
    }

    private static int[] convertTo1D(int[][] inMatrix, int rows, int cols){
        int index = 0;
        int[] temp = new int[rows * cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                temp[index] = inMatrix[i][j];
                index++;
            }
        }
        return temp;
    }

//    private static void spiralOrder(int rows, int cols, int[] tempArray, int[][] inMatrix){
//        int index = 0;
//        int top = 0;
//        int bottom = rows - 1;
//        int left = 0;
//        int right = cols - 1;
//        int dir = 1; // direction
//        while(top <= bottom && left <= right){
//            // Moving left to right
//            if(dir == 1){
//                for(int i = left; i <= right; ++i){
//                    inMatrix[top][i] = tempArray[index];
//                    index++;
//                }
//                ++top;
//                dir = 2;
//            }
//            // Moving top to bottom
//            if(dir == 2){
//                for(int i = top; i <= bottom; ++i){
//                    inMatrix[i][right] = tempArray[index];
//                    index++;
//                }
//                --right;
//                dir = 3;
//            }
//            // Moving right to left
//            if(dir == 3){
//                for(int i = right; i >= left; --i){
//                    inMatrix[bottom][i] = tempArray[index];
//                    index++;
//                }
//                --bottom;
//                dir = 4;
//            }
//            // Moving bottom to top
//            if(dir == 4){
//                for(int i = bottom; i >= top; --i){
//                    inMatrix[i][left] = tempArray[index];
//                    index++;
//                }
//                ++left;
//                dir = 1;
//            }
//        }
//    }

    private static void spiralOrder(int rows, int cols, int[] temp, int[][] inMatrix){
        int index = 0;
        int bottom = rows;
        int right = cols;
        int top = 0;
        int left = 0;
        while(top < bottom && left < right){
            for(int i = left; i < right; ++i){
                inMatrix[top][i] = temp[index];
                index++;
            }
            top++;
            for(int i = top; i < bottom; ++i){
                inMatrix[i][right - 1] = temp[index];
                index++;
            }
            right--;
            if(top < right){
                for(int i = right - 1; i >= left; --i){
                    inMatrix[bottom - 1][i] = temp[index];
                    index++;
                }
                bottom--;
            }
            if(left < right){
                for(int i = bottom - 1; i >= top; --i){
                    inMatrix[i][left] = temp[index];
                    index++;
                }
                left++;
            }
        }
    }

//    public static void main(String[] args) {
//        //int[][] inMatrix = {{1,2,3},{4,5,6},{7,8,9}};
//        //int[][] inMatrix = {{7,5,1,4,6},{3,2,9,1,8},{8,3,2,4,1},{10,11,15,19,14}};
//        //int[][] inMatrix = createMatrix();
//        //int[][] inMatrix = null;
//        //int[][] inMatrix = {{1,2,3},{1},{1,2}};
//        //int[][] inMatrix = {{1,2},{3,4},{5,6},{7,8},{9,10},{11,12},{13,14},{15,16},{17,18},{19,20}};
//        //int[][] inMatrix = {{1},{2},{3}};
//        //int[][] inMatrix = {{1,2,3}};
//        int[][] inMatrix = {{1,2},{3,4},{5,6}};
//        System.out.println("Matrix");
//        display(inMatrix);
//        System.out.println("\nHelix");
//        int[][] outMatrix = helix(inMatrix);
//        display(outMatrix);
//    }
//
//    private static void display(int[][] matrix){
//        for(int x = 0; x < matrix.length; x++){
//            for(int y = 0; y < matrix[0].length; y++){
//                System.out.print(matrix[x][y] + " ");
//            }
//            System.out.println();
//        }
//    }
//
//    private static int[][] createMatrix(){
//        int[][] matrix = null;
//        int max = 100;
//        int min = 1;
//        Random random = new Random();
//        int row = random.nextInt(max - min) + min;
//        System.out.println("Rows: " + row);
//        int col = random.nextInt(max - min) + min;
//        System.out.println("Columns: " + col);
//        matrix = new int[row][col];
//        for(int i = 0; i < matrix.length; i++){
//            for(int j = 0; j < matrix[0].length; j++){
//                matrix[i][j] = random.nextInt();
//            }
//        }
//        return matrix;
//    }
}