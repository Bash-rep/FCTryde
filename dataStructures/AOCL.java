package dataStructures;

public class AOCL {
	
	DoublyLinkedList<Entry<Integer,Object>>[] elements;
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
			elements[i] = new DoublyLinkedList<Entry<Integer,Object>>();
		}
	}
	public void insert(int index,Entry<Integer, Object> element) {
		elements[index].addLast(element);
	}
	
	public DListNode<Entry<Integer,Object>> concatAll() {
		DListNode<Entry<Integer,Object>> last= null,next = null, head = null;
		int lastIndex = -1,nextIndex = 0;
		boolean done = false;
		
		while(!done) {
			if(last == null) {
				lastIndex++;
				last = elements[lastIndex].tail;
			}
			if(next == null && last != null) {
				nextIndex++;
				next = elements[nextIndex].head;					
			}else if(next == null && last == null){
				nextIndex++;
			}
			if(last != null && next != null) {
				if(head == null) {
					head = elements[lastIndex].head;
				}
				last.next = next;
				next.previous = last;
				last = elements[nextIndex].tail;
				next = null;
			}
			if(nextIndex >= size-1) {
				done = true;
			}
		}
		
		initialize(size);
		
		return head;
	}
}
