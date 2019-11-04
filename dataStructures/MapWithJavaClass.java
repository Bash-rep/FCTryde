package dataStructures;

public class MapWithJavaClass<K, V> implements Map<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected java.util.Map<K, V> elementos;
	protected int capPrevista;

	public MapWithJavaClass(int prevusers) {
		elementos = new java.util.HashMap<K, V>(prevusers);
		capPrevista = prevusers;
	}

	public MapWithJavaClass() {
		elementos = new java.util.HashMap<K, V>();
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
		return new KeyIterator<K,V>(iterator());
	}

	@Override
	public Iterator<V> values() throws NoElementException {
		return new ValuesIterator<K,V>(iterator());
	}

	@Override
	public Iterator<Entry<K, V>> iterator() throws NoElementException {
		return new EntryIteratorWithJavaClass<K,V>(elementos);
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

}
