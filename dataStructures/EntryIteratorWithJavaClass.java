package dataStructures;

public class EntryIteratorWithJavaClass<K,V> implements Iterator<Entry<K,V>> {
	java.util.Iterator<java.util.Map.Entry<K, V>> it;
	java.util.Map<K,V> elements;
	
	public EntryIteratorWithJavaClass(java.util.Map<K,V> elements) {
		this.elements = elements;
		rewind();
	}	
	
	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public Entry<K,V> next() throws NoSuchElementException {
		java.util.Map.Entry<K, V> o = it.next();
		return new EntryClass<>(o.getKey(), o.getValue());
	}

	@Override
	public void rewind() {
		it = elements.entrySet().iterator();                    
	}

}
