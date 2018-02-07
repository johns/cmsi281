public class Yarn implements YarnInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    private Entry[] items;
    private int size;
    private int uniqueSize;
    private final int MAX_SIZE = 100;


    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    Yarn () {
        this.items = new Entry[MAX_SIZE];
        this.size = 0;
        this.uniqueSize = 0;

    }

    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    public boolean isEmpty () {
        return (this.size == 0);
    }

    public int getSize () {
        return this.size;
    }

    public int getUniqueSize () {
        return this.uniqueSize;
    }

    public boolean insert (String toAdd) {
        if (this.uniqueSize == MAX_SIZE) {
            return false;
        }
        else {
            if (this.contains(toAdd)) {
                int i = this.getEntryIndex(toAdd);
                this.items[i].count += 1;
                this.size += 1;
                return true;
            }
            else {
                this.items[this.uniqueSize] = new Entry(toAdd, 1);
                this.size += 1;
                this.uniqueSize += 1;
                return true;
            }
        }
    }

    public int remove (String toRemove) {
        if (this.contains(toRemove)) {
            int i = this.getEntryIndex(toRemove);
            if (this.items[i].count == 1) {
                this.removeAll(toRemove);
                return 0;
            }
            else {
                this.items[i].count -= 1;
                this.size -= 1;
                return this.items[i].count;
            }
        }
        else {
            return 0;
        }
    }

    public void removeAll (String toNuke) {
        if (this.contains(toNuke)) {
            int i = this.getEntryIndex(toNuke);
            int oldSize = this.items[i].count;
            Yarn copy = new Yarn();
            for (int j = 0; j < i; j++) {
                copy.items[j] = this.items[j];
            }
            for (int k = i+1; k < this.uniqueSize; k++) {
                copy.items[k-1] = this.items[k];
            }
            this.items = copy.items;
            this.size -= oldSize;
            this.uniqueSize -= 1;
        }
    }

    public int count (String toCount) {
        if (this.contains(toCount)) {
            int i = this.getEntryIndex(toCount);
            return this.items[i].count;
        }
        else {
            return 0;
        }
    }

    public boolean contains (String toCheck) {
        if (this.getEntryIndex(toCheck) >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public String getNth (int n) {
        int textNum = 0;
        for (int i = 0; i < this.uniqueSize; i++) {
            for (int j = 0; j < this.items[i].count; j++) {
                if (textNum == n){
                    return this.items[i].text;
                }
                textNum++;
            }
        }
        return "error";
    }

    public String getMostCommon () {
        int mostCommonIndex = 0;
        int highCount = 0;
        for (int i = 0; i < this.uniqueSize; i++) {
            if (this.items[i].count >= highCount){
                highCount = this.items[i].count;
                mostCommonIndex = i;
            }
        }
        return this.items[mostCommonIndex].text;
    }

    public Yarn clone () {
        Yarn copy = new Yarn();
        copy.size = this.size;
        copy.uniqueSize = this.uniqueSize;
        for (int i = 0; i < copy.uniqueSize; i++) {
            copy.items[i] = new Entry(this.items[i].text,this.items[i].count);
        }
        return copy;
    }

    public void swap (Yarn other) {
        Yarn copy1 = this.clone();
        Yarn copy2 = other.clone();

        this.size = copy2.size;
        this.uniqueSize = copy2.uniqueSize;
        for (int i = 0; i < this.uniqueSize; i++) {
            this.items[i] = new Entry(copy2.items[i].text,copy2.items[i].count);
        }

        other.size = copy1.size;
        other.uniqueSize = copy1.uniqueSize;
        for (int i = 0; i < this.uniqueSize; i++) {
            other.items[i] = new Entry(copy1.items[i].text,copy1.items[i].count);
        }

    }


    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------

    public static Yarn knit (Yarn y1, Yarn y2) {
        Yarn knitted = y1.clone();
        for (int i = 0; i < y2.uniqueSize; i++) {
            if (knitted.contains(y2.items[i].text)){
                knitted.items[knitted.getEntryIndex(y2.items[i].text)].count += y2.items[i].count;
            }
            else {
                for (int j = 0; j < y2.items[i].count; j++) {
                    knitted.insert(y2.items[i].text);
                }
            }
        }
        return knitted;
    }

    public static Yarn tear (Yarn y1, Yarn y2) {
        Yarn knitted = y1.clone();
        for (int i = 0; i < y2.uniqueSize; i++) {
            if (knitted.contains(y2.items[i].text)){
                knitted.remove(y2.items[i].text);
            }
        }
        return knitted;
    }

    public static boolean sameYarn (Yarn y1, Yarn y2) {
        boolean same = true;
        if ((y1.size == y2.size) && (y1.uniqueSize == y2.uniqueSize)) {
            for (int i = 0; i < y1.uniqueSize; i++) {
                if (y2.contains(y1.items[i].text)) {
                    if ((y1.items[i].text.equals(y2.items[y2.getEntryIndex(y1.items[i].text)].text)) && y1.items[i].count == y2.items[y2.getEntryIndex(y1.items[i].text)].count) {
                        same = true;
                    }
                    else {
                        return false;
                    }
                }
                else {
                    return false;
                }
            }
            return same;
        }
        else {
            return false;
        }
    }


    // -----------------------------------------------------------
    // Private helper methods
    // -----------------------------------------------------------
    // Add your own here!

    public int getEntryIndex (String toText) {
        if (this.uniqueSize == 0) {
            return -1;
        }
        else {
            for (int i = 0; i < this.uniqueSize; i++) {
                if (this.items[i].text.equals(toText)){
                    return i;
                }
            }
        }
        return -1;
    }



    class Entry {
        String text;
        int count;

        Entry (String s, int c) {
            text = s;
            count = c;
        }
    }
}
