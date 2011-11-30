package ex1;

import java.util.EnumMap;
import java.util.Iterator;

public class NeighborArray<T> implements Iterable<T>{
	EnumMap<Directions, T> _map;
	
	public NeighborArray() {
		_map = new EnumMap<Directions,T>(Directions.class);
	}
	
	public void put(Directions dir, T value){
		_map.put(dir, value);
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
		return _map.values().iterator();
	}
}
