package dataStructures;



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
