package cz.muni.fi.pb162.find.impl.filters;

import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import cz.muni.fi.pb162.find.filters.BasicFilter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author Marek Hurban
 */
public class FileContentFilter extends BasicFilter {
    private Pattern pattern;

    /**
     * @param paths paths to search
     * @param regex pattern
     *
     */
    public FileContentFilter(List<SearchEntry> paths, String regex) {
        super(paths);
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean filter(SearchEntry path) {
        try {
            Matcher paths = pattern.matcher(Files.readString(Paths.get(path.getFileName().toString())));
            return paths.find();
        } catch (IOException e) {
            return false;
        }
    }
}
