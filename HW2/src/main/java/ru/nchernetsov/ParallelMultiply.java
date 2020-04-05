package ru.nchernetsov;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Параллельное умножение матриц
 */
public class ParallelMultiply implements MatrixMultiply {

    private int N;

    /**
     * Массив мьютексов, используемых для того, чтобы каждый элемент результирующей матрицы рассчитывался одним потоком
     */
    private Lock[][] mutexes;

    /**
     * Массив флагов, показывающих, вычислен ли соответствующий элемент результирующей матрицы
     */
    private boolean[][] calculated;

    /**
     * Количество потоков для вычисления произведения матриц
     */
    private final int threadsCount;

    public ParallelMultiply(int threadsCount) {
        this.threadsCount = threadsCount;
    }

    @Override
    public long[][] multiply(long[][] A, long[][] B) {
        checkSizeAndSquareness(A, B);
        N = A.length;
        long[][] C = new long[N][N];

        // инициализируем мьютексы и флаги вычисления
        initMutexesAndCalculatedFlags(N);

        // инициализируем пул потоков
        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);

        // запускаем вычисления
        List<Callable<Boolean>> calculations = new ArrayList<>();
        for (int i = 0; i < threadsCount; i++) {
            calculations.add(() -> calculate(A, B, C));
        }

        try {
            // дожидаемся окончания работы всех потоков
            executor.invokeAll(calculations);
        } catch (InterruptedException ignored) {
        } finally {
            // закрываем пул потоков
            executor.shutdown();
        }

        // удаляем мьютексы и флаги вычисления
        clearMutexesAndCalculatedFlags(N);

        return C;
    }

    private boolean calculate(long[][] A, long[][] B, long[][] C) {
        // пока вся матрица не вычислена, пытаемся найти следующий элемент для вычисления и вычислить его
        while (resultIsNotCalculated()) {
            Pair<Integer, Integer> nextElementToCalculate = findNextElementToCalculate();
            // если есть следующий элемент для вычисления
            if (nextElementToCalculate != null) {
                int i = nextElementToCalculate.getFirst();
                int j = nextElementToCalculate.getSecond();
                Lock mutex = mutexes[i][j];
                // пытаемся захватить мьютекс для него
                if (mutex.tryLock()) {
                    // если мьютекс захвачен, то вычисляем элемент результирующей матрицы
                    try {
                        long sum = 0;
                        for (int k = 0; k < N; k++) {
                            sum += A[i][k] * B[k][j];
                        }
                        C[i][j] = sum;
                        calculated[i][j] = true;  // элемент i,j вычислен
                    } finally {
                        mutex.unlock();
                    }
                }
            }
        }
        // когда для потока больше нет вычислительных задач, завершаем работу
        return true;
    }

    private boolean resultIsNotCalculated() {
        // если последний элемент вычислен, то и результат вычислен (т.к. поиск элементов идёт слева-направо и сверху-вниз)
        return !calculated[N - 1][N - 1];
    }

    private Pair<Integer, Integer> findNextElementToCalculate() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // если элемент ещё не вычислен
                if (!calculated[i][j]) {
                    return Pair.of(i, j);
                }
            }
        }
        return null;
    }

    private void initMutexesAndCalculatedFlags(int N) {
        mutexes = new ReentrantLock[N][N];
        calculated = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mutexes[i][j] = new ReentrantLock();
                calculated[i][j] = false;
            }
        }
    }

    private void clearMutexesAndCalculatedFlags(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mutexes[i][j] = null;
            }
        }
        mutexes = null;
        calculated = null;
    }
}
