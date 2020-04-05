package ru.nchernetsov;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainProgram {

    public static void main(String[] args) {
        MainProgram mainProgram = new MainProgram();

        // читаем 2 матрицы A и B из файлов ресурсов
        long[][] A = mainProgram.readMatrixFromFile("A.txt");
        long[][] B = mainProgram.readMatrixFromFile("B.txt");

        // вычисляем их произведение
        long[][] C = new ParallelMultiply(4).multiply(A, B);

        // записываем его в файл C.txt
        mainProgram.writeMatrixToFile(C, "C.txt");
    }

    private long[][] readMatrixFromFile(String fileName) {
        URL resource = getClass().getClassLoader().getResource(fileName);
        try {
            if (resource == null) {
                throw new NoSuchFileException(fileName);
            }
            File file = new File(resource.getFile());
            List<String> lines = Files.readAllLines(file.toPath());
            int N = lines.size();
            long[][] result = new long[N][N];
            for (int i = 0; i < N; i++) {
                String line = lines.get(i);
                String[] elements = line.trim().split("\\s");
                if (elements.length != N) {
                    throw new IllegalStateException("not square matrix");
                }
                for (int j = 0; j < N; j++) {
                    long element = Long.parseLong(elements[j]);
                    result[i][j] = element;
                }
            }
            return result;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void writeMatrixToFile(long[][] matrix, String fileName) {
        Path newFilePath = Paths.get("HW2/src/main/resources/" + fileName);
        try {
            if (!Files.exists(newFilePath)) {
                Files.createFile(newFilePath);
            }
            PrintWriter writer = new PrintWriter(new FileWriter(newFilePath.toFile()));
            int N = matrix.length;
            for (int i = 0; i < N; i++) {
                long[] row = matrix[i];
                String rowString = Arrays.stream(row)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" "));
                if (i < N - 1) {
                    writer.println(rowString);
                } else {
                    writer.print(rowString);
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
