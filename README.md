# HorusFileReader
# FileCabinet task (Java)

## What’s implemented
- `count()` – counts all folders including nested ones
- `findFolderByName(String name)` – returns first match as `Optional`
- `findFoldersBySize(String size)` – returns all matches

## Approach
Recursive traversal of a tree-like structure (Composite pattern).  
To avoid duplication, the hierarchy is flattened once and reused by all methods.

## Run tests
```bash
mvn test
