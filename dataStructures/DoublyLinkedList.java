package dataStructures;

public class DoublyLinkedList<E> implements TwoWayList<E>  {
	
	// Node at the head of the list.
	protected DListNode<E> head;
	
	// Node at the tail of the list.
	protected DListNode<E> tail;
	
	// Number of elements in the list.
	protected int currentSize;
	
	public DoublyLinkedList() {
		head = null;
		tail = null;
		currentSize = 0;
	}
	
	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}
	
	@Override
	public int size() {
		return currentSize;
	}
	
	@Override
	public TwoWayIterator<E> iterator() throws NoElementException {
		if (currentSize == 0) throw new NoElementException("List is empty.");
		return new DoublyLLIterator<E>(head,tail);
	}
	
	@Override
	public int find(E element) {
		DListNode<E> auxNode = head;
		for (int pos = 0; pos < currentSize; pos++) {
			if (auxNode.getElement().equals(element))
				return pos;
			auxNode = auxNode.getNext();
		}
		return -1;
	}
	
	@Override
	public E getFirst() throws NoElementException {
		if (currentSize==0) throw new NoElementException("No such element.");
		return getNode(0).getElement();
	}
	
	@Override
	public E getLast() throws NoElementException {
		if (currentSize == 0) throw new NoElementException("No such element.");
		return getNode(currentSize - 1).getElement();
	}
	
	@Override
	public E get(int position) throws InvalidPositionException {
		if (position < 0 || position >= currentSize) 
			throw new InvalidPositionException("Invalid position.");
		return getNode(position).getElement();
	}
	
	@Override
	public void addFirst(E element) {
		DListNode<E> newNode = new DListNode<E>(element, null, head);
		if (currentSize == 0)
			tail = newNode;
		head = newNode;
		currentSize++;
	}

	@Override
	public void addLast(E element) {
		DListNode<E> newNode = new DListNode<E>(element, tail, null);
		if (currentSize == 0)
			head = newNode;
		else
			tail.setNext(newNode);
		tail = newNode;
		currentSize++;
	}
	
	@Override
	public void add(int position, E element) throws InvalidPositionException {
		if (position < 0 || position > currentSize) 
			throw new InvalidPositionException("Invalid Position.");
		if (position == 0) 
			addFirst(element);
		else if (position == currentSize) 
				addLast(element);
		else
			addMiddle(position, element);
	}
	
	private void addMiddle(int position, E element) {
		DListNode<E> prevNode = getNode(position - 1);
		DListNode<E> nextNode = getNode(position);
		DListNode<E> newNode = new DListNode<E>(element, prevNode, nextNode);
		nextNode.setPrevious(newNode);
		prevNode.setNext(newNode);
		currentSize++;
	}

	private E removeMiddle(int position) {
		DListNode<E> prevNode = getNode(position - 1);
		DListNode<E> nextNode = getNode(position + 1);
		DListNode<E> nodeToRemove = getNode(position);
		E elemOfNodeToRemove = nodeToRemove.getElement();
		prevNode.setNext(nextNode);
		nextNode.setPrevious(nodeToRemove);
		return elemOfNodeToRemove;
	}

	private DListNode<E> getNode(int position) {
		DListNode<E> aux = head;
		for(int i = 1; i <= position; i++)
			aux = aux.getNext();
		return aux;
	}
	
	@Override
	public E removeFirst() throws NoElementException {
		if (currentSize == 0) 
			throw new NoElementException("No such element.");
		E oldHeadElem = head.getElement();
		if (currentSize > 1)
			head.getNext().setPrevious(null);
		else 
			tail = null;
		head = head.getNext();
		return oldHeadElem;
	}
	
	@Override
	public E removeLast() throws NoElementException {
		if (currentSize == 0) 
			throw new NoElementException("No such element.");
		E oldTailElem = tail.getElement();
		if (currentSize > 1)
			tail.getPrevious().setNext(null);
		else
			head = null;
		tail = tail.getPrevious();
		return oldTailElem;
	}
	
	@Override
	public E remove(int position) throws InvalidPositionException {
		if(position < 0 || position >= currentSize)
			throw new InvalidPositionException("Invalid position.");
		if (position == 0)
			return removeFirst();
		if (position == currentSize - 1)
			return removeLast();
		return removeMiddle(position);
	}
}
