## File Utils ##

A collection of useful file utilities.

Check if File exists

```java
boolean exists = FileUtils.exists(file);
boolean exists = FileUtils.exists(path);
boolean exists = FileUtils.exists(pathString);
```

Read File to string

```java
String content = FileUtils.readFile(file);
String content = FileUtils.readFile(path);
String content = FileUtils.readFile(pathString);
```

Parse File to List

```java
List<String> lines = FileUtils.parseFile(file);
List<String> lines = FileUtils.parseFile(path);
List<String> lines = FileUtils.parseFile(pathString);
```

Create File

```java
FileUtils.createFile(path);
FileUtils.createFile(pathString);
```

Create File with content

```java
FileUtils.createFile(path, content);
FileUtils.createFile(pathString, content);
```

Write to File

```java
FileUtils.writeToFile(path, content);
FileUtils.writeToFile(pathString, content);
```

Write to File list of contents

```java
List<String> contents = ...
FileUtils.writeToFile(path, contents);
FileUtils.writeToFile(pathString, contents);
```

Append to File

```java
FileUtils.writeToFile(path, content);
FileUtils.writeToFile(pathString, content);
```

Append to File list of contents

```java
List<String> contents = ...
FileUtils.appendToFile(path, contents);
FileUtils.appendToFile(pathString, contents);
```

Append To File with new line

```java
FileUtils.writeToFile(path, content);
FileUtils.writeToFile(pathString, content, true);
```

Append to File list of contents with new line

```java
List<String> contents = ...
FileUtils.appendToFile(path, contents);
FileUtils.appendToFile(pathString, contents, true);
```

Rename File

```java
FileUtils.renameFile(path, filename);
FileUtils.renameFile(pathString, filename);
```

Move File

```java
FileUtils.moveFile(sourcePath, targetPath);
FileUtils.moveFile(sourcePathString, targetPathString);
```

Copy File

```java
FileUtils.copyFile(sourcePath, targetPath);
FileUtils.copyFile(sourcePathString, targetPathString);
```

Truncate File

```java
FileUtils.truncateFile(path);
FileUtils.truncateFile(pathString);
```

Delete File

```java
FileUtils.deleteFile(path);
FileUtils.deleteFile(pathString);
```

List Files (not including directories)

```java
List<File> files = FileUtils.listFiles(path);
List<File> files = FileUtils.listFiles(pathString);
```

List Directories

```java
List<File> directories = FileUtils.listDirectories(path);
List<File> directories = FileUtils.listDirectories(pathString);
```

Filter Files by prefix

```java
List<File> filteredFiles = FileUtils.filterByPrefix(files, "prop-");
```

**Note:** This will return files that start with "prop-"

Filter Files by suffix

```java
List<File> filteredFiles = FileUtils.filterBySuffix(files, ".txt");
```

**Note:** This will return files that end with ".txt"

Order Files by last modified (touch) date

```java
List<File> orderedFiles = FileUtils.orderByLastModifiedDate(files);
```

Order Files by last modified (touch) date descending

```java
List<File> orderedFiles = FileUtils.orderByLastModifiedDate(files, true);
```

Read resource File

```java
String content = FileUtils.readResourceFile(this.getClass(), pathString);
```

Parse resource File

```java
String lines = FileUtils.parseResourceFile(this.getClass(), pathString);
```

File Extension

```java
String extension = FileUtils.getFileExtension(file);
```
