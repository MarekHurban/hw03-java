package cz.muni.fi.pb162.find.impl.filters;

import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import cz.muni.fi.pb162.find.filters.BasicFilter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author Marek Hurban
 */
public class FileNameFilter extends BasicFilter {
    private Pattern pattern;
    /**
     * @param paths paths to search
     * @param regex pattern
     *
     */
    public FileNameFilter(List<SearchEntry> paths, String regex) {
        super(paths);
        if (paths == null || regex == null) {
            throw new IllegalArgumentException("Paths or regex is null.");
        }
        this.pattern = Pattern.compile("^" + regex + "$");
    }
    @Override
    public boolean filter(SearchEntry path) {
        if (path == null) {
            return false;
        }
        String name = path.getPath().getFileName().toString();
        Matcher paths = pattern.matcher(name);
        return paths.find();
    }
}
