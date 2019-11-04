package dataStructures;

import java.io.Serializable;

public interface TwoWayList<E> extends List<E>, Serializable {
	
	// Returns a bidirectional iterator of the elements in the list.
	TwoWayIterator<E> iterator( ) throws NoElementException;
}
