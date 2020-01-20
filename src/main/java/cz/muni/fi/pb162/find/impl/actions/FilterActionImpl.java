package cz.muni.fi.pb162.find.impl.actions;

import cz.muni.fi.pb162.find.ApplicationOptions;
import cz.muni.fi.pb162.find.actions.FilterAction;
import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import cz.muni.fi.pb162.find.impl.filters.FileContentFilter;
import cz.muni.fi.pb162.find.impl.filters.FileNameFilter;
import cz.muni.fi.pb162.find.impl.filters.MaxSizeFilter;
import cz.muni.fi.pb162.find.impl.filters.MinSizeFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Marek Hurban
 */
public class FilterActionImpl implements FilterAction {
    private ApplicationOptions options;

    /**
     *
     * @param opts options for filter action
     */
    public FilterActionImpl(ApplicationOptions opts) {
        options = opts;
    }

    @Override
    public List<SearchEntry> filter(List<SearchEntry> entries) {
        String nameRegex = options.getNameRegex();
        String textRegex = options.getTextRegex();
        Long[] sizes = {options.getSizeMin(), options.getSizeMax()};
        List<SearchEntry> filtered = new ArrayList<>();

        if(textRegex != null) {
            FileContentFilter contentFilter = new FileContentFilter(entries, textRegex);
            filtered.addAll(entries.stream().filter(contentFilter::filter).collect(Collectors.toList()));
        }
        if(nameRegex != null) {
            FileNameFilter nameFilter = new FileNameFilter(entries, nameRegex);
            filtered.addAll(entries.stream().filter(nameFilter::filter).collect(Collectors.toList()));
        }
        if(sizes[0] != null) {
            MinSizeFilter minSizeFilter = new MinSizeFilter(entries, sizes[0]);
            filtered.addAll(entries.stream().filter(minSizeFilter::filter).collect(Collectors.toList()));
        }
        if(sizes[1] != null) {
            MaxSizeFilter maxSizeFilter = new MaxSizeFilter(entries, sizes[1]);
            filtered.addAll(entries.stream().filter(maxSizeFilter::filter).collect(Collectors.toList()));
        }
        if (filtered.isEmpty()) {
            return entries;
        }
        return filtered;
    }

}
