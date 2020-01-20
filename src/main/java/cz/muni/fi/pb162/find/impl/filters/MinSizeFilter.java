package cz.muni.fi.pb162.find.impl.filters;

import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import cz.muni.fi.pb162.find.filters.BasicFilter;

import java.util.List;
/**
 * @author Marek Hurban
 */
public class MinSizeFilter extends BasicFilter {
    private long minSize;

    /**
     *
     * @param paths to search
     * @param minSize of file to find
     */
    public MinSizeFilter(List<SearchEntry> paths, long minSize) {
        super(paths);
        this.minSize = minSize;
    }

    @Override
    public boolean filter(SearchEntry path) {
        if (path == null) {
            return false;
        }
        return path.getSize() >= this.minSize;
    }
}
