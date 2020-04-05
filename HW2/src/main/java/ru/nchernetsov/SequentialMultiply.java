package ru.nchernetsov;

/**
 * Последовательное умножение матриц
 */
public class SequentialMultiply implements MatrixMultiply {

    @Override
    public long[][] multiply(long[][] A, long[][] B) {
        checkSizeAndSquareness(A, B);

        int N = A.length;
        long[][] C = new long[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                long sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += A[i][k] * B[k][j];
                }
                C[i][j] = sum;
            }
        }

        return C;
    }
}
