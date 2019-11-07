package dataStructures;

import ryde.Trip;

public class RadixSort {
	
	public static List<Entry<Integer, Trip>> sort(DoublyLinkedList<Entry<Integer, Trip>> sortables, int maxSize) {
		
		DListNode<Entry<Integer, Trip>> node;
		
		if ((node = sortables.head) == null) {
			return null;
		}
		
		AOCL buckets = new AOCL(10);
		
		int bucketIndex;
		int currentKey;
		Entry<Integer, Trip> currentEntry;
		
		for (int i = 1; i <= maxSize; i++) {
			do {
				currentEntry = node.getElement();
				currentKey = currentEntry.getKey();
				bucketIndex = (int) Math.floor((currentKey%(Math.pow(10, i)))/(Math.pow(10, i-1)));
				buckets.insert(bucketIndex, currentEntry);
			} while ((node = node.getNext()) != null);
			node = buckets.concatAll();
		}
		
		DoublyLinkedList<Entry<Integer, Trip>> sorted = new DoublyLinkedList<>();
		
		do {
			sorted.addLast(node.getElement());
		} while ((node = node.getNext()) != null);

		return sorted;
	}
}
