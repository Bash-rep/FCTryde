package dataStructures;

import java.io.Serializable;

public interface SortedMap<K extends Comparable<K>, V> extends Map<K,V>, Serializable {
	
	// Returns the entry with the smallest key in the SortedMap.
	// @throws NoElementException if isEmpty()
	Entry<K,V> minEntry( ) throws NoElementException;
	 
	// Returns the entry with the largest key in the SortedMap.
	// @throws NoElementException if isEmpty()
	Entry<K,V> maxEntry( ) throws NoElementException;
	
}
