package io.ljunggren.file.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FileUtilsTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    
    @Test
    public void instantiationTest() {
        assertNotNull(new FileUtils());
    }
    
    @Test
    public void existsTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        assertTrue(FileUtils.exists(file));
    }
    
    @Test
    public void existsNullFileTest() {
        File file = null;
        assertFalse(FileUtils.exists(file));
    }
    
    @Test
    public void existsNullStringTest() {
        String path = null;
        assertFalse(FileUtils.exists(path));
    }
    
    @Test
    public void existsNullPathTest() {
        Path path = null;
        assertFalse(FileUtils.exists(path));
    }
    
    @Test
    public void existsStringPathTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        assertTrue(FileUtils.exists(file.getAbsolutePath()));
    }
    
    @Test
    public void existsStringPathFalseTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        String path = file.getAbsolutePath();
        FileUtils.deleteFile(path);
        assertFalse(FileUtils.exists(path));
    }
    
    @Test
    public void readFileTest() throws IOException {
        String content = "This is a test file.";
        File file = temporaryFolder.newFile("test.txt");
        FileUtils.writeToFile(file.getAbsolutePath(), content);
        String readContent = FileUtils.readFile(file);
        assertEquals(content, readContent);
    }
    
    @Test(expected = IOException.class)
    public void readFileNullTest() throws IOException {
        File file = null;
        FileUtils.readFile(file);
    }
    
    @Test
    public void parseFileTest() throws IOException {
        List<String> content = Arrays.asList(new String[] {
                "This is a test file",
                "with two lines."
        });
        File file = temporaryFolder.newFile("test.txt");
        FileUtils.writeToFile(file.getAbsolutePath(), content);
        List<String> readContent = FileUtils.parseFile(file);
        assertEquals(content, readContent);
    }
    
    @Test(expected = IOException.class)
    public void parseFileNullTest() throws IOException {
        File file = null;
        FileUtils.parseFile(file);
    }
    
    @Test
    public void createFileTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        String path = file.getAbsolutePath();
        FileUtils.createFile(path);
        assertTrue(FileUtils.exists(path));
    }
    
    @Test
    public void createFileWithContentTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        String path = file.getAbsolutePath();
        String content = "This is a test file.";
        FileUtils.createFile(path, content);
        assertTrue(FileUtils.exists(path));
        assertEquals(content, FileUtils.readFile(path));
    }
    
    @Test(expected = IOException.class)
    public void createFileNullTest() throws IOException {
        String path = null;
        FileUtils.createFile(path);
    }
    
    @Test
    public void writeToFileTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        String path = file.getAbsolutePath();
        String content = "This is a test file.";
        FileUtils.createFile(path);
        FileUtils.writeToFile(path, content);
        assertEquals(content, FileUtils.readFile(path));
    }
    
    @Test
    public void writeToFileNullContentTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        String path = file.getAbsolutePath();
        FileUtils.createFile(path);
        String content = null;
        FileUtils.writeToFile(path, content);
        assertTrue(FileUtils.readFile(path).isEmpty());
    }
    
    @Test(expected = IOException.class)
    public void writeToFileNullPathTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        String path = file.getAbsolutePath();
        FileUtils.createFile(path);
        path = null;
        FileUtils.writeToFile(path, "This is a test file");
    }
    
    @Test
    public void writeToFileListTest() throws IOException {
        List<String> contents = Arrays.asList(new String[] {
                "This is a test file",
                "with two lines."
        });
        File file = temporaryFolder.newFile("test.txt");
        String path = file.getAbsolutePath();
        FileUtils.writeToFile(path, contents);
        String readContent = FileUtils.readFile(path);
        assertEquals(String.join(FileUtils.NEW_LINE, contents), readContent);
    }
    
    @Test
    public void writeToFileListNullListTest() throws IOException {
        List<String> contents = null;
        File file = temporaryFolder.newFile("test.txt");
        String path = file.getAbsolutePath();
        FileUtils.writeToFile(path, contents);
        assertTrue(FileUtils.readFile(path).isEmpty());
    }
    
    @Test(expected = IOException.class)
    public void writeToFileListNullPathTest() throws IOException {
        List<String> contents = Arrays.asList(new String[] {
                "This is a test file",
                "with two lines."
        });
        String path = null;
        FileUtils.writeToFile(path, contents);
    }

    @Test
    public void appendToFileTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        String path = file.getAbsolutePath();
        String content = "This is a test file.";
        String appendedContent = "More text";
        FileUtils.createFile(path, content);
        FileUtils.appendToFile(path, appendedContent, false);
        assertEquals(content + appendedContent, FileUtils.readFile(path));
    }
    
    @Test
    public void appendToFileNewLineTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        String path = file.getAbsolutePath();
        String content = "This is a test file.";
        String appendedContent = "More text";
        FileUtils.createFile(path, content);
        FileUtils.appendToFile(path, appendedContent, true);
        assertEquals(content + FileUtils.NEW_LINE + appendedContent, FileUtils.readFile(path));
    }
    
    @Test
    public void appendToFileNullContentTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        String path = file.getAbsolutePath();
        String content = "This is a test file.";
        String appendedContent = null;
        FileUtils.createFile(path, content);
        FileUtils.appendToFile(path, appendedContent, true);
        assertEquals(content, FileUtils.readFile(path));
    }
    
    @Test(expected = IOException.class)
    public void appendToFileNullPathTest() throws IOException {
        String path = null;
        String content = "This is a test file.";
        String appendedContent = "More text";
        FileUtils.createFile(path, content);
        FileUtils.appendToFile(path, appendedContent, true);
    }
    
    @Test
    public void appendToFileListTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        String path = file.getAbsolutePath();
        String content = "This is a test file.";
        List<String> appendedContents = Arrays.asList(new String[] {
                "This is a test file",
                "with two lines."
        });
        FileUtils.createFile(path, content);
        FileUtils.appendToFile(path, appendedContents, false);
        assertEquals(content + String.join(FileUtils.NEW_LINE, appendedContents), FileUtils.readFile(path));
    }
    
    @Test
    public void appendToFileListNewLineTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        String path = file.getAbsolutePath();
        String content = "This is a test file.";
        List<String> appendedContents = Arrays.asList(new String[] {
                "This is a test file",
                "with two lines."
        });
        FileUtils.createFile(path, content);
        FileUtils.appendToFile(path, appendedContents, true);
        assertEquals(content + FileUtils.NEW_LINE + String.join(FileUtils.NEW_LINE, appendedContents), FileUtils.readFile(path));
    }
    
    @Test
    public void appendToFileListNullContentTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        String path = file.getAbsolutePath();
        String content = "This is a test file.";
        List<String> appendedContents = null;
        FileUtils.createFile(path, content);
        FileUtils.appendToFile(path, appendedContents, true);
        assertEquals(content, FileUtils.readFile(path));
    }
    
    @Test(expected = IOException.class)
    public void appendToFileListNullPathTest() throws IOException {
        String path = null;
        String content = "This is a test file.";
        List<String> appendedContents = Arrays.asList(new String[] {
                "This is a test file",
                "with two lines."
        });
        FileUtils.createFile(path, content);
        FileUtils.appendToFile(path, appendedContents, true);
    }
    
    @Test
    public void renameFileTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        String path = file.getAbsolutePath();
        String newPath = FileUtils.renameFile(path, "test2.txt");
        assertTrue(FileUtils.exists(newPath));
    }
    
    @Test(expected = IOException.class)
    public void renameFileNullPathTest() throws IOException {
        String path = null;
        FileUtils.renameFile(path, "test2.txt");
    }
    
    @Test
    public void moveFileTest() throws IOException {
        File sourceFile = temporaryFolder.newFile("test.txt");
        String sourcePath = sourceFile.getAbsolutePath();
        String targetPath = sourceFile.getParentFile().getAbsolutePath() + "test2.txt";
        FileUtils.moveFile(sourcePath, targetPath);
        assertTrue(FileUtils.exists(targetPath));
    }
    
    @Test(expected = IOException.class)
    public void moveFileSourcePathNullTest() throws IOException {
        File sourceFile = temporaryFolder.newFile("test.txt");
        String sourcePath = null;
        String targetPath = sourceFile.getParentFile().getAbsolutePath() + "test2.txt";
        FileUtils.moveFile(sourcePath, targetPath);
    }
    
    @Test(expected = IOException.class)
    public void moveFileTargetPathNullTest() throws IOException {
        File sourceFile = temporaryFolder.newFile("test.txt");
        String sourcePath = sourceFile.getAbsolutePath();
        String targetPath = null;
        FileUtils.moveFile(sourcePath, targetPath);
    }
    
    @Test
    public void copyFileTest() throws IOException {
        File sourceFile = temporaryFolder.newFile("test.txt");
        String sourcePath = sourceFile.getAbsolutePath();
        String targetPath = sourceFile.getParentFile().getAbsolutePath() + "test2.txt";
        FileUtils.copyFile(sourcePath, targetPath);
        assertEquals(FileUtils.readFile(sourcePath), FileUtils.readFile(targetPath));
    }
    
    @Test(expected = IOException.class)
    public void copyFileSourcePathNullTest() throws IOException {
        File sourceFile = temporaryFolder.newFile("test.txt");
        String sourcePath = null;
        String targetPath = sourceFile.getParentFile().getAbsolutePath() + "test2.txt";
        FileUtils.copyFile(sourcePath, targetPath);
    }
    
    @Test(expected = IOException.class)
    public void copyFileTargetPathNullTest() throws IOException {
        File sourceFile = temporaryFolder.newFile("test.txt");
        String sourcePath = sourceFile.getAbsolutePath();
        String targetPath = null;
        FileUtils.copyFile(sourcePath, targetPath);
    }
    
    @Test
    public void truncateTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        String path = file.getAbsolutePath();
        String content = "This is a test file.";
        FileUtils.createFile(path, content);
        FileUtils.truncateFile(path);
        assertEquals("", FileUtils.readFile(path));
    }
    
    @Test(expected = IOException.class)
    public void truncateNullPathTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        String path = file.getAbsolutePath();
        String content = "This is a test file.";
        FileUtils.createFile(path, content);
        path = null;
        FileUtils.truncateFile(path);
    }
    
    @Test
    public void deleteFileTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        String path = file.getAbsolutePath();
        FileUtils.createFile(path);
        FileUtils.deleteFile(path);
        assertFalse(FileUtils.exists(path));
    }
    
    @Test(expected = IOException.class)
    public void deleteFileNullPathTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        String path = file.getAbsolutePath();
        FileUtils.createFile(path);
        path = null;
        FileUtils.deleteFile(path);
    }
    
    @Test
    public void listFilesTest() throws IOException {
        List<File> files = Arrays.asList(new File[] {
                temporaryFolder.newFile("test.txt"),
                temporaryFolder.newFile("test.pdf"),
                temporaryFolder.newFile("test.doc")
        });
        String path = temporaryFolder.getRoot().getAbsolutePath();
        List<File> readFiles = FileUtils.listFiles(Paths.get(path));
        Collections.sort(files);
        Collections.sort(readFiles);
        assertEquals(files, readFiles);
    }
    
    @Test(expected = IOException.class)
    public void listFilesNullPathTest() throws IOException {
        Path path = null;
        FileUtils.listFiles(path);
    }
    
    @Test
    public void filterByPrefixTest() throws IOException {
        List<File> files = Arrays.asList(new File[] {
                temporaryFolder.newFile("test.txt"),
                temporaryFolder.newFile("test.pdf"),
                temporaryFolder.newFile("info.doc")
        });
        List<File> filteredFiles = FileUtils.filterByPrefix(files, "test");
        assertTrue(filteredFiles.size() == 2);
    }
    
    @Test
    public void filterByPrefixEmptyListTest() {
        List<File> filteredFiles = FileUtils.filterByPrefix(new ArrayList<File>(), "test");
        assertTrue(filteredFiles.isEmpty());
    }
    
    @Test
    public void filterByPrefixNullListTest() {
        List<File> filteredFiles = FileUtils.filterByPrefix(null, "test");
        assertTrue(filteredFiles.isEmpty());
    }
    
    @Test
    public void filterByPrefixNullPrefixTest() throws IOException {
        List<File> files = Arrays.asList(new File[] {
                temporaryFolder.newFile("test.txt"),
                temporaryFolder.newFile("test.pdf"),
                temporaryFolder.newFile("info.doc")
        });
        List<File> filteredFiles = FileUtils.filterByPrefix(files, null);
        assertTrue(filteredFiles.size() == 0);
    }
    
    @Test
    public void filterBySuffixTest() throws IOException {
        List<File> files = Arrays.asList(new File[] {
                temporaryFolder.newFile("test.txt"),
                temporaryFolder.newFile("test.pdf"),
                temporaryFolder.newFile("info.txt")
        });
        List<File> filteredFiles = FileUtils.filterBySuffix(files, "txt");
        assertTrue(filteredFiles.size() == 2);
    }
    
    @Test
    public void filterBySuffixEmptyListTest() {
        List<File> filteredFiles = FileUtils.filterBySuffix(new ArrayList<File>(), "txt");
        assertTrue(filteredFiles.isEmpty());
    }
    
    @Test
    public void filterBySuffixNullListTest() {
        List<File> filteredFiles = FileUtils.filterBySuffix(null, "txt");
        assertTrue(filteredFiles.isEmpty());
    }
    
    @Test
    public void filterBySuffixNullSuffixTest() throws IOException {
        List<File> files = Arrays.asList(new File[] {
                temporaryFolder.newFile("test.txt"),
                temporaryFolder.newFile("test.pdf"),
                temporaryFolder.newFile("info.txt")
        });
        List<File> filteredFiles = FileUtils.filterBySuffix(files, null);
        assertTrue(filteredFiles.size() == 0);
    }
    
}
