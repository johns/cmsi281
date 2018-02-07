import java.util.ArrayList;

public class Autocompleter implements AutocompleterInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    TTNode root;


    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    Autocompleter () {
        root = null;
    }


    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------

    public boolean isEmpty () {
        return root == null;
    }

    public void addTerm (String toAdd) {
        String normToAdd = normalizeTerm(toAdd);
        root = addTermHelper(root, normToAdd.toCharArray(), 0);
    }

    public boolean hasTerm (String query) {
        String normQuery = normalizeTerm(query);
        return hasTermHelper(root, normQuery.toCharArray(), 0);
    }

    public String getSuggestedTerm (String query) {
        String normQuery = normalizeTerm(query);
        return getSuggestedTermHelper(root, normQuery.toCharArray(), 0, "");
    }

    public ArrayList<String> getSortedTerms () {
        ArrayList<String> sorted = new ArrayList<String>();
        return getSortedTermsHelper(root, sorted, "");
    }


    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------

    private String normalizeTerm (String s) {
        // Edge case handling: empty Strings illegal
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException();
        }
        return s.trim().toLowerCase();
    }

    /*
    * Returns:
    *   int less than 0 if c1 is alphabetically less than c2
    *   0 if c1 is equal to c2
    *   int greater than 0 if c1 is alphabetically greater than c2
    */
    private int compareChars (char c1, char c2) {
        return Character.toLowerCase(c1) - Character.toLowerCase(c2);
    }

    // [!] Add your own helper methods here!

    private TTNode addTermHelper(TTNode n, char[] toHelp, int i) {

        if (n == null) {
            n = new TTNode(toHelp[i], false);
        }

        if (compareChars(toHelp[i], n.letter) < 0) {
            n.left = addTermHelper(n.left, toHelp, i);
        }
        else if (compareChars(toHelp[i], n.letter) > 0) {
            n.right = addTermHelper(n.right, toHelp, i);
        }
        else
        {
            if (i + 1 < toHelp.length) {
                n.mid = addTermHelper(n.mid, toHelp, i + 1);
            }
            else {
                n.wordEnd = true;
            }
        }
        return n;
    }


    private boolean hasTermHelper(TTNode n, char[] toHelp, int i) {

        if (n == null) {
            return false;
        }

        if (compareChars(toHelp[i], n.letter) < 0) {
            return hasTermHelper(n.left, toHelp, i);
        }
        else if (compareChars(toHelp[i], n.letter) > 0) {
            return hasTermHelper(n.right, toHelp, i);
        }
        else
        {
            if (n.wordEnd && i == toHelp.length - 1) {
                return true;
            }
            else if (i == toHelp.length - 1) {
                return false;
            }
            else {
                return hasTermHelper(n.mid, toHelp, i + 1);
            }
        }

    }


    private String getSuggestedTermHelper(TTNode n, char[] toHelp, int i, String s) {

        if (n == null) {
            return null;
        }

        if (compareChars(toHelp[i], n.letter) < 0) {
            return getSuggestedTermHelper(n.left, toHelp, i, s);
        }
        else if (compareChars(toHelp[i], n.letter) > 0) {
            return getSuggestedTermHelper(n.right, toHelp, i, s);
        }
        else
        {
            if (n.wordEnd && i == toHelp.length - 1) {
                return new String(toHelp);
            }
            else if (i == toHelp.length - 1) {
                ArrayList<String> suffixes = new ArrayList<String>();
                if (getSortedTermsHelper(n, suffixes, "").isEmpty()) {
                    return null;
                }
                else {
                    String prefix = new String(toHelp);
                    return prefix.substring(0, prefix.length()-1) + getSortedTermsHelper(n, suffixes, "").get(0);
                }
            }
            else {
                return getSuggestedTermHelper(n.mid, toHelp, i + 1, s);
            }
        }

    }


    private ArrayList<String> getSortedTermsHelper(TTNode n, ArrayList<String> l, String s) {

        if (n != null) {

            getSortedTermsHelper(n.left, l, s);

            s += n.letter;
            if (n.wordEnd) {
                l.add(s);
            }

            getSortedTermsHelper(n.mid, l, s);
            s = s.substring(0, s.length() - 1);

            getSortedTermsHelper(n.right, l, s);

        }
        return l;

    }


    // -----------------------------------------------------------
    // TTNode Internal Storage
    // -----------------------------------------------------------

    /*
    * Internal storage of autocompleter search terms
    * as represented using a Ternary Tree with TTNodes
    */
    private class TTNode {

        boolean wordEnd;
        char letter;
        TTNode left, mid, right;

        TTNode (char c, boolean w) {
            letter  = c;
            wordEnd = w;
            left    = null;
            mid     = null;
            right   = null;
        }

    }

}
