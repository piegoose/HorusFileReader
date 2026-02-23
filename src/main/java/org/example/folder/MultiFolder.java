package org.example.folder;

import java.util.List;

public interface MultiFolder extends Folder {
    List<Folder> getFolders();
}
