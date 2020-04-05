package ru.nchernetsov;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SequentialMultiplyTest {

    @Test
    void sequentialMultiplyTest1() {
        long[][] A = new long[][]{{1, 2}, {3, 4}};
        long[][] B = new long[][]{{1, 2}, {3, 4}};

        long[][] C = new SequentialMultiply().multiply(A, B);

        assertThat(C[0]).isEqualTo(new long[]{7, 10});
        assertThat(C[1]).isEqualTo(new long[]{15, 22});
    }

    @Test
    void sequentialMultiplyTest2() {
        long[][] A = new long[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        long[][] B = new long[][]{{2, 2, 2}, {2, 2, 2}, {2, 2, 2}};

        long[][] C = new SequentialMultiply().multiply(A, B);

        assertThat(C[0]).isEqualTo(new long[]{6, 6, 6});
        assertThat(C[1]).isEqualTo(new long[]{6, 6, 6});
        assertThat(C[2]).isEqualTo(new long[]{6, 6, 6});
    }
}
