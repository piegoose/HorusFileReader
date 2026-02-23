package org.example.cabinet;

import org.example.folder.Folder;
import org.example.folder.MultiFolder;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileCabinetTest {


    private record SimpleFolder(String name, String size) implements Folder {
        @Override public String getName() { return name; }
        @Override public String getSize() { return size; }
    }

    private record SimpleMultiFolder(String name, String size, List<Folder> folders) implements MultiFolder {
        @Override public String getName() { return name; }
        @Override public String getSize() { return size; }
        @Override public List<Folder> getFolders() { return folders; }
    }

    @Test
    void count_should_include_nested_folders() {
        // A(Multi) -> A1, A2(Multi -> A2a)
        Folder a1 = new SimpleFolder("A1", "SMALL");
        Folder a2a = new SimpleFolder("A2a", "SMALL");
        Folder a2 = new SimpleMultiFolder("A2", "MEDIUM", List.of(a2a));
        Folder a = new SimpleMultiFolder("A", "LARGE", List.of(a1, a2));

        Folder b = new SimpleFolder("B", "SMALL");

        FileCabinet cabinet = new FileCabinet(List.of(a, b));

        // A, A1, A2, A2a, B = 5
        assertEquals(5, cabinet.count());
    }
    @Test
    void should_find_folder_by_name() {
        Folder sampleFolder = new SimpleFolder("sampleFolder", "SMALL");
        Folder sampleNestedFolderstedFolder = new SimpleMultiFolder("sampleNestedFOlder", "MEDIUM", List.of(sampleFolder));
        Folder root = new SimpleMultiFolder("ROOT", "LARGE", List.of(sampleNestedFolderstedFolder));

        FileCabinet cabinet = new FileCabinet(List.of(root));

        Optional<Folder> result = cabinet.findFolderByName("sampleFolder");

        assertTrue(result.isPresent());
        assertEquals("sampleFolder", result.get().getName());
    }
    @Test
    void should_find_folder_by_size() {
        Folder simpleFolder = new SimpleFolder("simpleFolder", "SMALL");
        FileCabinet cabinet = new FileCabinet(List.of(simpleFolder));

        Optional<Folder> result = cabinet.findFolderByName("XYZ");

        assertTrue(result.isEmpty());
    }
}
