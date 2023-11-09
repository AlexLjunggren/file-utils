package io.ljunggren.fileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {
    
    public static final String NEW_LINE = System.lineSeparator();

    public static boolean exists(File file) {
        return file == null ? false : file.exists();
    }
    
    public static boolean exists(String path) {
        return path == null ? false : exists(Paths.get(path));
    }
    
    public static boolean exists(Path path) {
        return path == null ? false : Files.exists(path);
    }
    
    public static String readFile(File file) throws IOException {
        return readFile(file == null ? null : file.getAbsolutePath());
    }

    public static String readFile(String path) throws IOException {
        return readFile(path == null ? null : Paths.get(path));
    }
    
    public static String readFile(Path path) throws IOException {
        if (path == null) {
            throw new IOException("Path is null");
        }
        return new String(Files.readAllBytes(path));
    }
    
    public static List<String> parseFile(File file) throws IOException {
        return parseFile(file == null ? null : file.getAbsolutePath());
    }
    
    public static List<String> parseFile(String path) throws IOException {
        return parseFile(path == null ? null : Paths.get(path));
    }
    
    public static List<String> parseFile(Path path) throws IOException {
        if (path == null) {
            throw new IOException("Path is null");
        }
        return Files.readAllLines(path);
    }
    
    public static String createFile(String path) throws IOException {
        return createFile(path == null ? null : Paths.get(path)).toString();
    }
    
    public static Path createFile(Path path) throws IOException {
        return createFile(path, null);
    }
    
    public static String createFile(String path, String content) throws IOException {
        return createFile(path == null ? null : Paths.get(path), content).toString();
    }
    
    public static Path createFile(Path path, String content) throws IOException {
        if (path == null) {
            throw new IOException("Path is null");
        }
        byte[] bytes = content == null ? new byte[] {} : content.getBytes();
        return Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
    
    public static String writeToFile(String path, String content) throws IOException {
        return writeToFile(path == null ? null : Paths.get(path), content).toString();
    }
    
    public static Path writeToFile(Path path, String content) throws IOException {
        if (path == null) {
            throw new IOException("Path is null");
        }
        byte[] bytes = content == null ? new byte[] {} : content.getBytes();
        return Files.write(path, bytes, StandardOpenOption.WRITE);
    }
    
    public static String writeToFile(String path, List<String> contents) throws IOException {
        return writeToFile(path == null ? null : Paths.get(path), contents).toString();
    }

    public static Path writeToFile(Path path, List<String> contents) throws IOException {
        return writeToFile(path, contents == null ? null : String.join(NEW_LINE, contents));
    }
    
    public static String appendToFile(String path, String content) throws IOException {
        return appendToFile(path, content, false);
    }
    
    public static String appendToFile(String path, String content, boolean newLine) throws IOException {
        return appendToFile(path == null ? null : Paths.get(path), content, newLine).toString();
    }
    
    public static Path appendToFile(Path path, String content, boolean newLine) throws IOException {
        if (path == null) {
            throw new IOException("Path is null");
        }
        if (content == null) {
            return Files.write(path, new byte[] {}, StandardOpenOption.APPEND);
        }
        content = newLine ? NEW_LINE + content : content;
        return Files.write(path, content.getBytes(), StandardOpenOption.APPEND);
    }
    
    public static String appendToFile(String path, List<String> contents) throws IOException {
        return appendToFile(path, contents, false);
    }
    
    public static String appendToFile(String path, List<String> contents, boolean newLine) throws IOException {
        return appendToFile(path == null ? null : Paths.get(path), contents, newLine).toString();
    }
    
    public static Path appendToFile(Path path, List<String> contents, boolean newLine) throws IOException {
        return appendToFile(path, contents == null ? null : String.join(System.lineSeparator(), contents), newLine);
    }
    
    public static String renameFile(String path, String newName) throws IOException {
        return renameFile(path == null ? null : Paths.get(path), newName).toString();
    }

    public static Path renameFile(Path path, String newName) throws IOException {
        if (path == null) {
            throw new IOException("Path is null");
        }
        return Files.move(path, path.resolveSibling(newName), StandardCopyOption.REPLACE_EXISTING);
    }

    public static String moveFile(String sourcePath, String targetPath) throws IOException {
        return moveFile(sourcePath == null ? null : Paths.get(sourcePath), 
                targetPath == null ? null : Paths.get(targetPath)).toString();
    }
    
    public static Path moveFile(Path sourcePath, Path targetPath) throws IOException {
        if (sourcePath == null) {
            throw new IOException("Source path is null");
        }
        if (targetPath == null) {
            throw new IOException("Target path is null");
        }
        return Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
    }
    
    public static String copyFile(String sourcePath, String targetPath) throws IOException {
        return copyFile(sourcePath == null ? null : Paths.get(sourcePath), 
                targetPath == null ? null : Paths.get(targetPath)).toString();
    }
    
    public static Path copyFile(Path sourcePath, Path targetPath) throws IOException {
        if (sourcePath == null) {
            throw new IOException("Source path is null");
        }
        if (targetPath == null) {
            throw new IOException("Target path is null");
        }
        return Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
    }
    
    public static String truncateFile(String path) throws IOException {
        return truncateFile(path == null ? null : Paths.get(path)).toString();
    }

    public static Path truncateFile(Path path) throws IOException {
        if (path == null) {
            throw new IOException("Path is null");
        }
        return Files.write(path, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static void deleteFile(String path) throws IOException {
        deleteFile(path == null ? null : Paths.get(path));
    }

    public static void deleteFile(Path path) throws IOException {
        if (path == null) {
            throw new IOException("Path is null");
        }
        Files.delete(path);
    }

    public static List<File> listFiles(String path) throws IOException {
        if (path == null ) {
            throw new IOException("Path is null");
        }
        return Arrays.stream(new File(path).listFiles())
                .filter(file -> file.isFile())
                .collect(Collectors.toList());
    }
    
    public static List<File> listFiles(Path path) throws IOException {
        return listFiles(path == null ? null : path.toString());
    }
    
    public static List<File> listDirectories(String path) throws IOException {
        if (path == null) {
            throw new IOException("Path is null");
        }
        return Arrays.stream(new File(path).listFiles())
                .filter(file -> !file.isFile())
                .collect(Collectors.toList());
    }
    
    public static List<File> listDirectories(Path path) throws IOException {
        return listDirectories(path == null ? null : path.toString());
    }

    public static List<File> filterByPrefix(List<File> files, String prefix) {
        if (files == null || prefix == null) {
            return new ArrayList<File>();
        }
        return files.stream()
                .filter(file -> file.getName().startsWith(prefix))
                .collect(Collectors.toList());
    }

    public static List<File> filterBySuffix(List<File> files, String suffix) {
        if (files == null || suffix == null) {
            return new ArrayList<File>();
        }
        return files.stream()
                .filter(file -> file.getName().endsWith(suffix))
                .collect(Collectors.toList());
    }
    
    public static void orderByLastModifiedDate(List<File> files) {
        orderByLastModifiedDate(files, false);
    }

    public static void orderByLastModifiedDate(List<File> files, boolean descending) {
        if (files == null) {
            return;
        }
        Collections.sort(files, new Comparator<File>() {
            public int compare(File f1, File f2) {
                return descending ?
                        ((Long) f2.lastModified()).compareTo((Long) f1.lastModified()) :
                        ((Long) f1.lastModified()).compareTo((Long) f2.lastModified());
            }
        });
    }

    public static String readResourceFile(Class<?> clazz, String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream in = clazz.getClassLoader().getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
    
    public static List<String> parseResourceFile(Class<?> clazz, String path) throws IOException {
        List<String> lines = new ArrayList<>();
        InputStream in = clazz.getClassLoader().getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }
    
}
