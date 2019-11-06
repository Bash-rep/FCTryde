package dataStructures;

public class SortedMapWithJavaClass<K extends Comparable<K>, V> implements SortedMap<K, V> {

	protected java.util.SortedMap<K, V> elementos;
	protected int capPrevista;

	public SortedMapWithJavaClass(int capPrevista) {
		elementos = new java.util.TreeMap<K, V>();
		this.capPrevista = capPrevista;
	}

	@Override
	public boolean isEmpty() {
		return elementos.isEmpty();
	}

	@Override
	public int size() {
		return elementos.size();
	}

	@Override
	public Iterator<K> keys() throws NoElementException {
		return new KeyIterator<K, V>(iterator());
	}

	@Override
	public Iterator<V> values() throws NoElementException {
		return new ValueIterator<K, V>(iterator());
	}

	@Override
	public Iterator<Entry<K, V>> iterator() throws NoElementException {
		return new EntryIteratorWithJC<K, V>(elementos);
	}

	@Override
	public V find(K key) {
		return elementos.get(key);
	}

	@Override
	public V insert(K key, V value) {
		return elementos.put(key, value);
	}

	@Override
	public V remove(K key) {
		return elementos.remove(key);
	}

	@Override
	public Entry<K, V> minEntry() throws NoElementException {
		return new EntryClass<K, V>(elementos.firstKey(), elementos.get(elementos.firstKey()));
	}

	@Override
	public Entry<K, V> maxEntry() throws NoElementException {
		return new EntryClass<K, V>(elementos.lastKey(), elementos.get(elementos.lastKey()));
	}

}
