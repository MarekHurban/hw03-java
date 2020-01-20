package cz.muni.fi.pb162.find.impl;

import cz.muni.fi.pb162.find.ApplicationOptions;
import cz.muni.fi.pb162.find.filesystem.DirEntry;
import cz.muni.fi.pb162.find.filesystem.FSWalkResult;
import cz.muni.fi.pb162.find.filesystem.FileEntry;
import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import cz.muni.fi.pb162.find.tools.FileTools;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Marek Hurban
 */
public class FSWalkResultImpl extends FSWalkResult {
    private List<SearchEntry> dirs;
    private List<SearchEntry> files;

    /**
     *
     * @param opts for walking through file system
     */
    public FSWalkResultImpl(ApplicationOptions opts) {
        super(opts);
        dirs = new ArrayList<>();
        files = new ArrayList<>();
    }

    @Override
    public List<SearchEntry> getDirectories() {

        return Collections.unmodifiableList(dirs);
    }

    @Override
    public List<SearchEntry> getFiles() {
        return Collections.unmodifiableList(files);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        Objects.requireNonNull(dir);
        Objects.requireNonNull(attrs);
        DirEntry dirEntry = new DirEntry(dir);
        dirEntry.setSize(FileTools.dirSize(dir));
        dirs.add(dirEntry);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        Objects.requireNonNull(file);
        Objects.requireNonNull(attrs);
        FileEntry fileEntry = new FileEntry(file);
        fileEntry.setSize(FileTools.dirSize(file));
        files.add(fileEntry);
        return FileVisitResult.CONTINUE;
    }
}
