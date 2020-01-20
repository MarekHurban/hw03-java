package cz.muni.fi.pb162.find.impl.filters;

import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import cz.muni.fi.pb162.find.filters.BasicFilter;

import java.util.List;

/**
 * @author Marek Hurban
 */
public class MaxSizeFilter extends BasicFilter {
    private long maxSize;
    /**
     *
     * @param paths to search
     * @param maxSize of file to find
     */
    public MaxSizeFilter(List<SearchEntry> paths, long maxSize) {
        super(paths);
        this.maxSize = maxSize;
    }

    @Override
    public boolean filter(SearchEntry path) {
        if (path == null) {
            return false;
        }
        return path.getSize() <= this.maxSize;
    }
}
