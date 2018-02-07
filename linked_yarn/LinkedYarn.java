import java.util.NoSuchElementException;

public class LinkedYarn implements LinkedYarnInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    private Node head;
    private int size, uniqueSize, modCount;


    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    LinkedYarn () {
    	this.head = null;
        this.size = 0;
        this.uniqueSize = 0;
        this.modCount = 0;
    }


    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    public boolean isEmpty () {
        return this.size == 0;
    }

    public int getSize () {
        return this.size;
    }

    public int getUniqueSize () {
    	return this.uniqueSize;
    }

    public void insert (String toAdd) {
		this.size++;
		this.modCount++;
    	if (this.contains(toAdd)) {
    		for (Node n = head; n != null; n = n.next) {
        		if (n.text.equals(toAdd)){
        			n.count++;
        			break;
                }
        	}

    	}
    	else {
    		Node currentHead = head;
            head = new Node(toAdd, 1);
            head.next = currentHead;
            if (currentHead != null) {
            	currentHead.prev = head;
            }
    		this.uniqueSize++;
    	}
    }

    public int remove (String toRemove) {
    	for (Node n = head; n != null; n = n.next) {
        	if (n.text.equals(toRemove)){
        		if (n.count == 1) {
            		this.removeAll(toRemove);
            		return 0;
            	}
            	else {
            		n.count--;
            		this.size--;
            		this.modCount++;
                	return n.count;
            	}
        	}
        }
    		return 0;
    }

    public void removeAll (String toNuke) {
    	if (this.contains(toNuke)) {
    		for (Node n = head; n != null; n = n.next) {
        		if (n.text.equals(toNuke)){
        			int nukedCount = n.count;
        			this.size -= nukedCount;
            		if (this.head.equals(n)) {
            			this.head = n.next;
            		}
            		else {
            			Node o = n.next;
            			n = n.prev;
            			n.next = o;
            		}
                }
        	}
    		this.uniqueSize--;
    		this.modCount++;
    	}
    }

    public int count (String toCount) {
    	for (Node n = head; n != null; n = n.next) {
    		if (n.text.equals(toCount)){
                return n.count;
            }
    	}
    	return 0;
    }

    public boolean contains (String toCheck) {
    	for (Node n = head; n != null; n = n.next) {
    		if (n.text.equals(toCheck)){
                return true;
            }
    	}
    	return false;
    }

    public String getMostCommon () {
    	String mostCommonText = null;
    	int highCount = 0;
    	for (Node n = head; n != null; n = n.next) {
            if (n.count >= highCount){
            	highCount = n.count;
            	mostCommonText = n.text;
            }
        }
    	return mostCommonText;
    }

    public LinkedYarn clone () {
    	LinkedYarn copy = new LinkedYarn();
    	for (Node n = head; n != null; n = n.next) {
    		for (int i = 0; i < n.count; i++) {
    			copy.insert(n.text);
    		}
    	}
    	return copy;
    }

    public void swap (LinkedYarn other) {
    	Node tempHead = this.head;
        int tempSize = this.size;
        int tempUniqueSize = this.uniqueSize;

        this.head = other.head;
        this.size = other.size;
        this.uniqueSize = other.uniqueSize;

        other.head = tempHead;
        other.size = tempSize;
        other.uniqueSize = tempUniqueSize;

        this.modCount++;
        other.modCount++;
    }

    public LinkedYarn.Iterator getIterator () {
        if (this.isEmpty()) {
        	throw new IllegalStateException();
        }
        else {
        	LinkedYarn.Iterator it = new LinkedYarn.Iterator(this);
        	return it;
        }
    }


    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------

    public static LinkedYarn knit (LinkedYarn y1, LinkedYarn y2) {
        LinkedYarn y3 = y1.clone();
        for (Node n = y2.head; n != null; n = n.next) {
    		for (int i = 0; i < n.count; i++) {
    			y3.insert(n.text);
    		}
    	}
        return y3;

    }

    public static LinkedYarn tear (LinkedYarn y1, LinkedYarn y2) {
    	LinkedYarn y3 = y1.clone();
        for (Node n = y2.head; n != null; n = n.next) {
    		if (y3.contains(n.text)) {
    			for (int i = 0; i < n.count; i++) {
    				y3.remove(n.text);
    			}
    		}
    	}
        return y3;
    }

    public static boolean sameYarn (LinkedYarn y1, LinkedYarn y2) {
    	return tear(y1, y2).isEmpty() && tear(y2, y1).isEmpty();
    }


    // -----------------------------------------------------------
    // Private helper methods
    // -----------------------------------------------------------

    // You should add some methods here!


    // Haha private helper methods ppphhtt I don't need those


    // -----------------------------------------------------------
    // Inner Classes
    // -----------------------------------------------------------

    public class Iterator implements LinkedYarnIteratorInterface {
        LinkedYarn owner;
        Node current;
        int itModCount;


        Iterator (LinkedYarn y) {
            this.owner = y;
            this.current = this.owner.head;
            this.itModCount = this.owner.modCount;
        }

        public boolean isValid () {
            return this.itModCount == this.owner.modCount;
        }

        public boolean hasNext () {
        	return (current != null && (current.position < current.count-1 || current.next != null));
        }

        public boolean hasPrev () {
        	return (current != null && (current.position > 0 || current.prev != null));
        }

        public String getString () {
        	if (this.isValid()) {
        		return current.text;
            }
            else {
            	throw new IllegalStateException();
            }
        }

        public void next () {
        	if (this.isValid()) {
        		if (this.hasNext()) {
        			if (current.position < current.count-1) {
                		current.position++;
                	}
                	else {
                		current.position = 0;
                		current = current.next;
                	}
        		}
        		else {
        			throw new NoSuchElementException();
        		}
            }
            else {
            	throw new IllegalStateException();
            }
        }

        public void prev () {
        	if (this.isValid()) {
        		if (this.hasPrev()) {
        			if (current.position > 0) {
                		current.position--;
                	}
                	else {
                		current = current.prev;
                		current.position = current.count-1;
                	}
        		}
        		else {
        			throw new NoSuchElementException();
        		}
            }
            else {
            	throw new IllegalStateException();
            }
        }

        public void replaceAll (String toReplaceWith) {
            if (this.isValid()) {
            	current.text = toReplaceWith;
            	this.owner.modCount++;
            	this.itModCount++;
            }
            else {
            	throw new IllegalStateException();
            }
        }
    }

    class Node {
    	Node next, prev;
    	String text;
    	int count;
    	int position;

    	Node (String t, int c) {
    		text = t;
    		count = c;
    		position = 0;
    	}
    }
}
