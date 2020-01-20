package cz.muni.fi.pb162.find.impl.actions;

import cz.muni.fi.pb162.find.ApplicationOptions;
import cz.muni.fi.pb162.find.actions.SortAction;
import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import cz.muni.fi.pb162.find.tools.SortFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marek Hurban
 */
public class SortActionImpl implements SortAction {
    private ApplicationOptions options;

    /**
     *
     * @param options for sorting action
     */
    public SortActionImpl(ApplicationOptions options) {
        this.options = options;
    }

    @Override
    public List<SearchEntry> sorted(List<SearchEntry> entries) {
        if (options == null || entries == null) {
            return entries;
        }
        String howToSort = this.options.getSort();
        List<SearchEntry> result = new ArrayList<>(entries);
        System.out.println(entries);
        result.sort(SortFactory.create(howToSort));
        return result;
    }
}
