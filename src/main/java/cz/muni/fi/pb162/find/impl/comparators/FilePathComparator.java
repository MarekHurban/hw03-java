package cz.muni.fi.pb162.find.impl.comparators;

import cz.muni.fi.pb162.find.comparators.BasicComparator;
import cz.muni.fi.pb162.find.filesystem.SearchEntry;

import java.nio.file.Path;

/**
 * @author Marek Hurban
 */
public class FilePathComparator implements BasicComparator {

    @Override
    public BasicComparator getNextComparator() {
        return null;
    }

    @Override
    public int compare(SearchEntry t1, SearchEntry t2) {
        Path tmp1 = t1.getPath().toAbsolutePath().normalize();
        Path tmp2 = t2.getPath().toAbsolutePath().normalize();
        // neviem ako funguje t1.getPath().toAbsolutePath().compareTo(t2.getPath().toAbsolutePath().normalize();
        // ale test stym spokojny neni
        int result = tmp1.toString().length() - tmp2.toString().length();
        if (result == 0) {
            return tmp1.compareTo(tmp2);
        }
        return result;

    }
}
