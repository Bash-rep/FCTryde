package dataStructures;

public class KeyIterator<K, V> implements Iterator<K>{
	private Iterator<Entry<K, V>> iterator;

	public KeyIterator(Iterator<Entry<K, V>> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public K next() throws NoSuchElementException {
		return iterator.next().getKey();
	}

	@Override
	public void rewind() {
		iterator.rewind();
	}
}
