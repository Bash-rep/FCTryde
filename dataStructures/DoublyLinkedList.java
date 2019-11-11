package dataStructures;


public class DoublyLinkedList<E> implements TwoWayList<E> {
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
		if (currentSize == 0)
			throw new NoElementException("List is empty.");
		return new DoublyLLIterator<E>(head, tail);
	}

	@Override
	public int find(E element) {
		int pos = 0;
		DListNode<E> auxNo = head;
		while(auxNo.getElement() != element) {
			auxNo = auxNo.next;
			pos++;
			if(auxNo == null)
				return -1;
		}
		return pos;
	}

	@Override
	public E getFirst() throws NoElementException {
		if (currentSize == 0)
			throw new NoElementException("No such element.");
		return getNode(0).getElement();
	}

	@Override
	public E getLast() throws NoElementException {
		if (currentSize == 0)
			throw new NoElementException("No such element.");
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
		DListNode<E> newHead = new DListNode<E>(element);
		newHead.next = head;
		if (head != null)
			head.previous = newHead;
		head = newHead;
		if (++currentSize == 1)
			tail = newHead;
	}

	@Override
	public void addLast(E element) {
		DListNode<E> newV = new DListNode<E>(element);
		if (tail != null)
			tail.next = newV;
		newV.previous = tail;
		tail = newV;
		if (++currentSize == 1)
			head = newV;
	}

	@Override
	public void add(int position, E element) throws InvalidPositionException {
		if (position < 0 || position > currentSize)
			throw new InvalidPositionException("Invalid Position.");
		if (position == 0)
			addFirst(element);
		else if (position == currentSize)
			addLast(element);
		else {
			addMiddle(position, element);
		}

	}

	@Override
	public E removeFirst() throws NoElementException {
		if (currentSize == 0)
			throw new NoElementException("No such element.");
		DListNode<E> aux;
		aux = head;
		if (currentSize == 1) {
			head = null;
			tail = null;
		} else {
			head = head.next;
			head.previous = null;
		}
		currentSize--;
		return aux.getElement();
	}

	@Override
	public E removeLast() throws NoElementException {
		if (currentSize == 0)
			throw new NoElementException("No such element.");
		DListNode<E> aux;
		aux = tail;
		if (currentSize == 1) {
			head = null;
			tail = null;
		}else {
			tail = tail.previous;
			tail.next = null;
		}
		currentSize--;
		return aux.getElement();
	}

	@Override
	public E remove(int position) throws InvalidPositionException {
		if (position < 0 || position >= currentSize)
			throw new InvalidPositionException("Invalid position.");
		if (position == 0)
			return removeFirst();
		if (position == currentSize - 1)
			return removeLast();
		return removeMiddle(position);
	}

	private void addMiddle(int position, E element) {
		DListNode<E> pre = getNode(position - 1);
		DListNode<E> aft = pre.next;
		DListNode<E> newV = new DListNode<E>(element, pre, aft);
		aft.previous = newV;
		pre.next = newV;
		currentSize++;
	}

	private E removeMiddle(int position) {
		DListNode<E> toRemove = getNode(position);
		DListNode<E> pre = toRemove.previous;
		DListNode<E> aft = toRemove.next;
		pre.next = aft;
		aft.previous = pre;
		currentSize--;
		return toRemove.getElement();
	}

	private DListNode<E> getNode(int position) {
		DListNode<E> aux = head;
		for (int i = 1; i <= position; i++)
			aux = aux.getNext();
		return aux;
	}
}