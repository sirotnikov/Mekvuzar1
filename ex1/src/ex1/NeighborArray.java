package ex1;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;


public class NeighborArray<T> implements Iterable<T>{
	EnumMap<Directions, T> _map;
	HashSet<T> _set;
	
	public NeighborArray() {
		_map = new EnumMap<Directions,T>(Directions.class);
		_set = new HashSet<T>();
	}
	/**
	 * 
	 * @param dir
	 * @param value
	 */
	public void put(Directions dir, T value){
		_map.put(dir, value);
		_set.add(value);
	}
	
	public T get(Directions dir){
		return _map.get(dir);
	}
	
	/**
	 * @param x
	 * @param y
	 * @throws IndexOutOfBoundsException
	 */

	@Override
	public Iterator<T> iterator() {
		return _set.iterator();
	}

	public boolean isEmpty() {
		return _set.isEmpty();
	}
}
