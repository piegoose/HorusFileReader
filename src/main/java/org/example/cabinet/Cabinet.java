package org.example.cabinet;

import org.example.folder.Folder;

import java.util.List;
import java.util.Optional;

public interface Cabinet {

    //todo zwraca dowolny element o podanej nazwie
    default Optional<Folder> findFolderByName(String name){return null;}

    //todo zwraca wsztkie foldery podanego rozmiaru SMALL / MEDIUM LARGE
    List<Folder> findFoldersBySize(String size);

    //todo zwraca liczbe wsztkichobiekltow tworzoacych strukture
    int count();
}
