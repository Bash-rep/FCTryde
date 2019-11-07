package dataStructures;

public class RadixSort {
	
	public static List<Integer> sort(DoublyLinkedList<Integer> sortables, int maxSize) {
		
		DListNode<Integer> node;
		
		if ((node = sortables.head) == null) {
			return null;
		}
		
		AOCL buckets = new AOCL(10);
		
		int bucketIndex;
		int currentInt;
		
		for (int i = 1; i <= maxSize; i++) {
			do {
				currentInt = node.getElement();
				bucketIndex = (int) Math.floor((currentInt%(Math.pow(10, i)))/(Math.pow(10, i-1)));
				buckets.insert(bucketIndex, currentInt);
			} while ((node = node.getNext()) != null);
			node = buckets.concatAll();
		}
		
		DoublyLinkedList<Integer> sorted = new DoublyLinkedList<Integer>();
		
		do {
			sorted.addLast(node.getElement());
		} while ((node = node.getNext()) != null);

		return sorted;
	}
}
