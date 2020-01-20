package cz.muni.fi.pb162.find.impl.comparators;

import cz.muni.fi.pb162.find.comparators.BasicComparator;
import cz.muni.fi.pb162.find.filesystem.SearchEntry;


/**
 * @author Marek Hurban
 */
public class FileSizeComparator implements BasicComparator {
    private BasicComparator nextComp;
    /**
     *
     * @param comparator next comparator to be used
     */
    public FileSizeComparator(BasicComparator comparator) {
        nextComp = comparator;
    }

    @Override
    public BasicComparator getNextComparator() {
        return nextComp;
    }

    @Override
    public int compare(SearchEntry t1, SearchEntry t2) {
        int result = Long.compare(t1.getSize(), t2.getSize());
        if(result == 0) {
            int tmp = nextComp.compare(t1, t2);
            return nextComp.compare(t1, t2);
        }
        return result;
    }
}
