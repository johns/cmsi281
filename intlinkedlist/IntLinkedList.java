/**
* Class IntLinkedList
* Simple, demo implementation of the Linked List ADT
* specified for ints
*/
public class IntLinkedList {

    private Node head;
    private int  size;

    IntLinkedList () {
        head = null;
        size = 0;
    }

    public int getSize () {
        return size;
    }

    public void prepend (int toAdd) {
        Node currentHead = head;
        head = new Node(toAdd);
        head.next = currentHead;
        size++;
    }

    public void removeAt (int index) {
        Node current = head,
        prev = null;

        // Find the Node just before the one to remove
        // (if there is one before it)
        while (current != null && index > 0) {
            // [!] Remove it!
            prev = current;
            current = current.next;
            index--;
        }

        if (current == null) {return;}
        if (current == head) {
            head = current.next;
        }
        if (prev != null) {
            prev.next = current.next;
        }
        size--;
    }

    public Iterator getIteratorAt (int index) {
        if (index > size || index < 0) {
            throw new IllegalArgumentException();
        }
        Iterator it = new Iterator ();
        while (index > 0) {
            it.next();
            index--;
        }
        return it;
    }

    /**
    * Class Node
    * Used as internal data storage for linked list
    */
    private class Node {

        int  data;
        Node next;

        Node (int d) {
            data = d;
            next = null;
        }

    }


    /**
    * Class IntLinkedList.Iterator
    * Used to efficiently iterate through the linked list's elements
    */
    public class Iterator {

        private Node current;

        Iterator () {
            current = head;
        }

        public boolean hasNext () {
            return current != null && current.next != null;
        }

        public void next () {
            if (current == null) {return;}
            current = current.next;
        }

        public int getCurrentInt () {
            return current.data;
        }

    }

}
