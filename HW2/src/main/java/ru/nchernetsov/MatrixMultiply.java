package ru.nchernetsov;

public interface MatrixMultiply {

    /**
     * Вычислить произведение двух матриц A и B
     *
     * @param A матрица A
     * @param B матрица B
     * @return произведение матрица A x B
     */
    long[][] multiply(long[][] A, long[][] B);

    /**
     * Проверка, что матрицы квадратные и одинакового размера
     * @param A матрица A
     * @param B матрица B
     */
    default void checkSizeAndSquareness(long[][] A, long[][] B) {
        int rowsA = A.length;
        int rowsB = B.length;
        if (rowsA == 0 || rowsB == 0) {
            throw new IllegalArgumentException("matrix size = 0");
        }
        int colsA = A[0].length;
        int colsB = B[0].length;
        if (rowsA != colsA) {
            throw new IllegalArgumentException("matrix A not square");
        }
        if (rowsB != colsB) {
            throw new IllegalArgumentException("matrix B not square");
        }
        if (rowsA != rowsB) {
            throw new IllegalArgumentException("matrices have different sizes");
        }
    }
}
