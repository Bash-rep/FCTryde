package dataStructures;


public class RadixSort<V> {
	
	public class AOCL<V> {
		
		DoublyLinkedList<Entry<Integer,V>>[] elements;
		int size;
		
		@SuppressWarnings("unchecked")
		public AOCL(int size) {
			elements = new DoublyLinkedList[size];
			this.size = size;
			initialize(size);
		}
		
		public void initialize(int size){
			this.size = size;
			
			for(int i = 0 ; i < size ; i++) {
				elements[i] = new DoublyLinkedList<Entry<Integer,V>>();
			}
		}
		public void insert(int index,Entry<Integer, V> element) {
			elements[index].addLast(element);
		}
		
		public DListNode<Entry<Integer, V>> concatAll() {
			DListNode<Entry<Integer, V>> tail = new DListNode<Entry<Integer, V>>(null), head = null, finalHead = null;

			for (int i = 0; i < size; i++) {
				if (elements[i].head != null) {
					if (finalHead == null) {
						finalHead = elements[i].head;
					}
					head = elements[i].head;
					tail.next = head;
					head.previous = tail;
					tail = elements[i].tail;
				}
			}

			initialize(size);
			return finalHead;
		}
	}

	
	
	public  List<Entry<Integer, V>> sort(DoublyLinkedList<Entry<Integer, V>> sortables, int maxSize) {
		
		DListNode<Entry<Integer, V>> node;
		
		if ((node = sortables.head) == null) {
			return null;
		}
		
		AOCL<V> buckets = new AOCL<>(10);
		
		int bucketIndex;
		int currentKey;
		Entry<Integer, V> currentEntry;
		
		for (int i = 1; i <= maxSize; i++) {
			do {
				currentEntry = node.getElement();
				currentKey = currentEntry.getKey();
				bucketIndex = (int) Math.floor((currentKey%(Math.pow(10, i)))/(Math.pow(10, i-1)));
				buckets.insert(bucketIndex, currentEntry);
			} while ((node = node.getNext()) != null);
			node = buckets.concatAll();
		}
		
		DoublyLinkedList<Entry<Integer, V>> sorted = new DoublyLinkedList<>();
		
		do {
			sorted.addLast(node.getElement());
		} while ((node = node.getNext()) != null);

		return sorted;
	}
}
