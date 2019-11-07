package dataStructures;

public class RadixSort {
	
	public static List<Entry<Integer, Object>> sort(DoublyLinkedList<Entry<Integer, Object>> sortables, int maxSize) {
		
		DListNode<Entry<Integer, Object>> node;
		
		if ((node = sortables.head) == null) {
			return null;
		}
		
		AOCL buckets = new AOCL(10);
		
		int bucketIndex;
		int currentKey;
		Entry<Integer, Object> currentEntry;
		
		for (int i = 1; i <= maxSize; i++) {
			do {
				currentEntry = node.getElement();
				currentKey = currentEntry.getKey();
				bucketIndex = (int) Math.floor((currentKey%(Math.pow(10, i)))/(Math.pow(10, i-1)));
				buckets.insert(bucketIndex, currentEntry);
			} while ((node = node.getNext()) != null);
			node = buckets.concatAll();
		}
		
		DoublyLinkedList<Entry<Integer, Object>> sorted = new DoublyLinkedList<>();
		
		do {
			sorted.addLast(node.getElement());
		} while ((node = node.getNext()) != null);

		return sorted;
	}
}
