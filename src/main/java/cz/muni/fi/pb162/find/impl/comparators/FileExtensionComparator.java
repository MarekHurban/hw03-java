package cz.muni.fi.pb162.find.impl.comparators;

import cz.muni.fi.pb162.find.comparators.BasicComparator;
import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import cz.muni.fi.pb162.find.tools.FileTools;


/**
 * @author Marek Hurban
 */
public class FileExtensionComparator implements BasicComparator {
    private BasicComparator nextComp;
    /**
     *
     * @param nextComp next comparator to be used
     */
    public FileExtensionComparator(BasicComparator nextComp) {
        this.nextComp = nextComp;
    }

    @Override
    public int compare(SearchEntry t1, SearchEntry t2) {
        String ext1 = FileTools.fileExtension(t1.getPath());
        String ext2 = FileTools.fileExtension(t2.getPath());
        if (ext1 == null || ext2 == null) {
            return nextComp.compare(t1, t2);
        }
        int result = ext1.compareTo(ext2);
        if (result == 0) {
            return nextComp.compare(t1, t2);
        }
        return result;
    }

    @Override
    public BasicComparator getNextComparator() {
        return nextComp;
    }
}
