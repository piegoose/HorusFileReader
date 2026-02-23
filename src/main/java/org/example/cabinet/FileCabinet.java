package org.example.cabinet;

import org.example.folder.Folder;
import org.example.folder.MultiFolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FileCabinet implements Cabinet {
    private final List<Folder> folders;

    public FileCabinet(List<Folder> folders) {
        this.folders = Objects.requireNonNullElse(folders, new ArrayList<>());
    }

    private List<Folder> getAndUnpackAllFolders() {
        List<Folder> unpackedFolders = new ArrayList<>();
        for (Folder folder : folders) {
            folderRecognize(folder, unpackedFolders);
        }
        return unpackedFolders;
    }

    private void folderRecognize(Folder folder, List<Folder> simpleFolders) {
        simpleFolders.add(folder);
        if (folder instanceof MultiFolder && ((MultiFolder) folder).getFolders() != null) {
            ((MultiFolder) folder)
                    .getFolders()
                    .forEach(f -> folderRecognize(f, simpleFolders));
        }
    }
    //todo zwraca dowolny element o podanej nazwie
    @Override
    public Optional<Folder> findFolderByName(String name) {
        List<Folder> andUnpackAllFolders = getAndUnpackAllFolders();
        return andUnpackAllFolders.stream()
                .filter(f -> Objects.equals(f.getName(), name))
                .findFirst();
    }
    //todo zwraca wsztkie foldery podanego rozmiaru SMALL / MEDIUM LARGE
    @Override
    public List<Folder> findFoldersBySize(String size) {
        return getAndUnpackAllFolders().stream()
                .filter(f -> Objects.equals(f.getSize(), size))
                .toList();
    }
    //todo zwraca liczbe wsztkichobiekltow tworzoacych strukture
    @Override
    public int count() {
        return getAndUnpackAllFolders().size();
    }
}
