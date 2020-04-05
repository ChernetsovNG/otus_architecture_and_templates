package ru.nchernetsov;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

public class ParallelMultiplyTest {

    @Test
    void parallelMultiplyTest1() {
        long[][] A = new long[][]{{1, 2}, {3, 4}};
        long[][] B = new long[][]{{1, 2}, {3, 4}};

        long[][] C = new ParallelMultiply(4).multiply(A, B);

        assertThat(C[0]).isEqualTo(new long[]{7, 10});
        assertThat(C[1]).isEqualTo(new long[]{15, 22});
    }

    @Test
    void parallelMultiplyTest2() {
        long[][] A = new long[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        long[][] B = new long[][]{{2, 2, 2}, {2, 2, 2}, {2, 2, 2}};

        long[][] C = new ParallelMultiply(4).multiply(A, B);

        assertThat(C[0]).isEqualTo(new long[]{6, 6, 6});
        assertThat(C[1]).isEqualTo(new long[]{6, 6, 6});
        assertThat(C[2]).isEqualTo(new long[]{6, 6, 6});
    }

    @Test
    public void randomMatricesTest() {
        /*
          создаём несколько случайных пар квадратных матриц случайного размера, вычисляем их произведение
          последовательно и параллельно и проверяем, что результат вычислений одинаковый
         */
        int count = 10;
        int minSize = 1;
        int maxSize = 100;
        long minValue = -1000;
        long maxValue = 1000;

        boolean[] resultsAreEquals = new boolean[count];
        for (int i = 0; i < count; i++) {
            int size = ThreadLocalRandom.current().nextInt(minSize, maxSize + 1);
            long[][] A = createRandomSquareMatrix(size, minValue, maxValue);
            long[][] B = createRandomSquareMatrix(size, minValue, maxValue);

            long[][] sequentialC = new SequentialMultiply().multiply(A, B);
            long[][] parallelC = new ParallelMultiply(Runtime.getRuntime().availableProcessors() - 1).multiply(A, B);

            resultsAreEquals[i] = Arrays.deepEquals(sequentialC, parallelC);
        }

        for (int i = 0; i < count; i++) {
            assertThat(resultsAreEquals[i]).isTrue();
        }
    }

    private long[][] createRandomSquareMatrix(int size, long minValue, long maxValue) {
        long[][] matrix = new long[size][size];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextLong(minValue, maxValue + 1);
            }
        }
        return matrix;
    }
}
