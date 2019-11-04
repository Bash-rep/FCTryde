package dataStructures;

public class ValuesIterator<K, V> implements Iterator<V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Iterator<Entry<K, V>> iterator;

	public ValuesIterator(Iterator<Entry<K, V>> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public V next() throws NoSuchElementException {
		return iterator.next().getValue();
	}

	@Override
	public void rewind() {
		iterator.rewind();
	}

}
