package exercise;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;

class App {

    public static CompletableFuture<String> unionFiles(
            String firstStringPath, String secondStringPath, String targetStringPath) {
        Path firstPath = Paths.get(firstStringPath);
        Path secondPath = Paths.get(secondStringPath);
        Path targetPath = Paths.get(targetStringPath);

        CompletableFuture<String> firstPathContent = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(firstPath);
            } catch (NoSuchFileException e) {
                System.out.println("Файл не найден: " + firstStringPath);
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> secondPathContent = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(secondPath);
            } catch (NoSuchFileException e) {
                System.out.println("Файл не найден: " + secondStringPath);
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> combined = firstPathContent.thenCombine(secondPathContent, (first, second) -> {
            try {
                if (Files.notExists(targetPath)) {
                    Files.writeString(targetPath, first, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } else {
                    Files.writeString(targetPath, first, StandardOpenOption.APPEND);
                }
                Files.writeString(targetPath, second, StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return first + second;
        });


        return combined.exceptionally(ex -> {
            Throwable cause = ex.getCause();
            if (cause instanceof NoSuchFileException) {
                System.out.println("Файл не найден (в цепочке CompletableFuture): " + cause.getMessage());
            } else {
                System.out.println("Ошибка: " + ex.getMessage());
            }
            return "";
        });
    }

    public static void main(String[] args) throws Exception {
        CompletableFuture<String> unitedFile = unionFiles(
                "src/main/resources/file1.txt",
                "src/main/resources/file2.txt",
                "src/main/resources/united.txt");

        unitedFile.get();
    }
}