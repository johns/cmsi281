public class BST {

    BSTNode root;

    BST () {
        root = null;
    }

    public void add (int i) {
        // Case: empty tree
        if (root == null) {
            root = new BSTNode(i);
            return;
        }

        BSTNode current = root;
        while (current != null) {
            // Case: insertion is less than current
            if (i < current.data) {
                // Case: OK to insert left
                if (current.left == null) {
                    current.left = new BSTNode(i);
                    return;
                }
                current = current.left;

                // Case: insertion greater than current
            } else {
                // Case: OK to insert right
                if (current.right == null) {
                    current.right = new BSTNode(i);
                    return;
                }
                current = current.right;
            }
        }
    }

    public boolean contains (int i) {
        BSTNode current = root;
        while (current != null) {
            // Found the query!
            if (current.data == i) {
                return true;
            }
            // Otherwise, keep looking...
            current = (i < current.data) ? current.left : current.right;
        }
        return false;
    }

    private class BSTNode {

        int data;
        BSTNode left, right;

        BSTNode (int d) {
            data = d;
            left = null;
            right = null;
        }
    }
}
