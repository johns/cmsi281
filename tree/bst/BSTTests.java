public class BSTTests {

    public static void main(String[] args) {
        BST tree = new BST();
        tree.add(10);
        tree.add(2);
        tree.add(12);
        tree.add(4);
        tree.add(3);
        tree.add(11);
        System.out.println(tree.contains(10)); // true
        System.out.println(tree.contains(3));  // true
        System.out.println(tree.contains(14)); // false
        System.out.println(tree.contains(1));  // false
    }

}
