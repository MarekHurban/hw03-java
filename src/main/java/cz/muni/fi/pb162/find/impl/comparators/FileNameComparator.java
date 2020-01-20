package cz.muni.fi.pb162.find.impl.comparators;

import cz.muni.fi.pb162.find.comparators.BasicComparator;
import cz.muni.fi.pb162.find.filesystem.SearchEntry;

/**
 * @author Marek Hurban
 */
public class FileNameComparator implements BasicComparator {
    private BasicComparator nextComp;
    /**
     *
     * @param comparator next comparator to be used
     */
    public FileNameComparator(BasicComparator comparator) {
        this.nextComp = comparator;
    }

    @Override
    public BasicComparator getNextComparator() {
        return nextComp;
    }

    @Override
    public int compare(SearchEntry t1, SearchEntry t2) {
        int result = t1.getFileName().toString().compareTo(t2.getFileName().toString());
        if (result == 0) {
            return nextComp.compare(t1, t2);
        }
        return result;
    }
}
