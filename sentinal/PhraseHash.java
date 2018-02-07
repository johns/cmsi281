import java.util.LinkedList;

public class PhraseHash implements PhraseHashInterface {

	// -----------------------------------------------------------
	// Fields
	// -----------------------------------------------------------

	private final static int BUCKET_COUNT = 1000;
	private int size, longest;
	private LinkedList<String>[] buckets;

	// -----------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------

	@SuppressWarnings("unchecked") // Don't worry about this >_>
	PhraseHash() {
		size = 0;
		longest = 0;
		buckets = new LinkedList[BUCKET_COUNT];
	}

	// -----------------------------------------------------------
	// Public Methods
	// -----------------------------------------------------------

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void put(String s) {
		if (this.get(s) != null) {
			return;
		}

		for (int i = 0; i < BUCKET_COUNT; i++) {
			if (buckets[hash(s)] == null) {
				LinkedList<String> l = new LinkedList<String>();
				buckets[hash(s)] = l;
				l.add(i, s);
				size++;
				String[] str = s.split(" ");
				if (longest < str.length) {
					longest = str.length;
				}
				return;
			} else {
				LinkedList<String> l = buckets[hash(s)];
				if (l.size() == i) {
					l.add(i, s);
					size++;
					String[] str = s.split(" ");
					if (longest < str.length) {
						longest = str.length;
					}
					return;
				}
			}
		}
	}

	public String get (String s) {
		if (buckets[hash(s)] == null) {
			return null;
		}
		else {
			LinkedList<String> l = buckets[hash(s)];
			if (l.contains(s)) {
				return s;
			}
			return null;
		}
	}

	public int longestLength() {
		return longest;
	}

	// -----------------------------------------------------------
	// Helper Methods
	// -----------------------------------------------------------

	private int hash(String s) {
		return s.length() % BUCKET_COUNT;
	}

}
