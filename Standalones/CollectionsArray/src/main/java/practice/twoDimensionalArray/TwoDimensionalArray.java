package practice.twoDimensionalArray;

public class TwoDimensionalArray {

    public static final char SYMBOL = 'X';

    public static void main(String[] args) {
        int size = 7;
        char[][] matrix = getTwoDimensionalArray(size);
        for (int i = 0; i < size; i++) {
            int j;
            System.out.print("[");
            for (j = 0; j < size - 1; j++) {
                System.out.print(matrix[i][j] + ", ");
            }
            System.out.println(matrix[i][j] + "]");
        }
    }

    public static char[][] getTwoDimensionalArray(int size) {

        //TODO: Написать метод, который создаст двумерный массив char заданного размера.
        // массив должен содержать символ SYMBOL по диагоналям, пример для size = 3
        // [X,  , X]
        // [ , X,  ]
        // [X,  , X]

        char[][] matrix = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = ' ';
            }
        }
        int j = 0;
        int k = size - 1;
        for (int i = 0; i < size; i++, j++, k--) {
            matrix[i][j] = 'X';
            matrix[i][k] = 'X';
        }
        return matrix;
    }
}
