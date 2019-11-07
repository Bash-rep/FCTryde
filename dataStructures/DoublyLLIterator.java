package dataStructures;

public class DoublyLLIterator<E> implements TwoWayIterator<E> {

	// Node with the first element in the iteration.
		private DListNode<E> firstNode;
		// Node with the last element in the iteration.
		private DListNode<E> lastNode;
		// Node with the next element in the iteration.
		private DListNode<E> next;
		// Node with the previous element in the iteration.
		private DListNode<E> prev;

		

		public DoublyLLIterator(DListNode<E> head, DListNode<E> tail) {
			firstNode=head;
			lastNode=tail;
			rewind();
		}

		

		@Override
		public boolean hasNext() {
			return next!=null;
		}

		@Override
		public E next() throws NoSuchElementException {
			if ( !this.hasNext() )
				throw new NoSuchElementException("No more elements.");
			E element = next.getElement();
			//TODO
			prev = next;
			next = next.next;
			return element;
		}

		@Override
		public void rewind() {
			next = firstNode;
			prev = null;
		}

		@Override
		public boolean hasPrevious() {
			return prev!=null;
		}

		@Override
		public E previous() throws NoSuchElementException {
			if ( !this.hasPrevious() )
				throw new NoSuchElementException("No more elements.");
			E element = prev.getElement();
			next = prev;
			prev = prev.previous;
			return element;
		}

		@Override
		public void fullForward() {
			prev = lastNode;
			next = null;
		}


}
