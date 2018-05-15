package Maman18;

/* Implementation of Hash Table.
 * Content of the hash table is of a type L
 * Every item of the table is a linked list with 2 more nodes with the same hash value */
public class HashTable<L> {
	/* Entry in a hash table which hold a key the item L and the next linked list */
	private class Record {
		String key;
		L item;
		Record next;

		// A new record to the hash table with the item and a key
		public Record(String key, L item) {
			this.key = key;
			this.item = item;
		}
	}
	/* Size of the hash table */
	private static final int TABLE_SIZE = 809;
		/* Hash table content */
	private Record[] table = new HashTable.Record[TABLE_SIZE];
	
	/* Calculate the and return the table size*/ 
	private int hash(String key) {
		return (Math.abs(key.hashCode())) % TABLE_SIZE;
	}
	
	/* Finds the item with a specific key in the hash table */
	public L find(String key) {
		int hash = hash(key);
		Record entry = table[hash];
		while (entry != null) {
			if (entry.key.equals(key)) {
				return entry.item;
			} else {
				entry = entry.next;
			}
		}
		return null;
	}
	
	/* Removes the key from the hash table */
	public void remove(String key) {
		int hash = hash(key);
		Record prevEntry = null;
		Record entry = table[hash];
		while (entry != null) {
			if (entry.key.equals(key)) {
				if (prevEntry == null) {
					table[hash] = entry.next;
				} else {
					prevEntry.next = entry.next;
				}			
				return;
			} else {
				prevEntry = entry;
				entry = entry.next;
			}
		}
	}
	
	//* Insert an item with a specific key to the hash table*/
	public void insert(String key, L item) {
		int hash = hash(key);
		Record entry = new Record(key, item);
		if (table[hash] != null) {
			entry.next = table[hash];
		}
		table[hash] = entry;
	}
}
