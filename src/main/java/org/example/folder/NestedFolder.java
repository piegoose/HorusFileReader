package org.example.folder;

import java.util.List;

public class NestedFolder implements MultiFolder {

    public NestedFolder() {

    }


    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getSize() {
        return "";
    }

    @Override
    public List<Folder> getFolders() {
        return List.of();
    }
}
