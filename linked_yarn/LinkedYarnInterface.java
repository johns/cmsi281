public interface LinkedYarnInterface {

    boolean isEmpty ();
    int getSize ();
    int getUniqueSize ();
    void insert (String toAdd);
    int remove (String toRemove);
    int count (String toCount);
    void removeAll (String toNuke);
    boolean contains (String toCheck);
    String getMostCommon ();
    LinkedYarn clone();
    void swap (LinkedYarn other);
    LinkedYarn.Iterator getIterator ();

}
