package ex1;

import java.util.HashMap;
import java.util.Iterator;

public class NeighborArray<T> implements Iterable<T>{
	HashMap<Position, T> _array;
	
	public NeighborArray() {
		_array = new HashMap<Position,T>();
		_array.put(new Position(1,1,3), null);
	}
	
	public void put(int x, int y, T value){
		evalXY(x, y);
		_array.put(new Position(x, y, 3), value);
	}
	
	public T get(int x, int y){
		evalXY(x, y);
		return _array.get(new Position(x,y,3));
	}
	
	/**
	 * @param x
	 * @param y
	 * @throws IndexOutOfBoundsException
	 */
	private void evalXY(int x, int y) throws IndexOutOfBoundsException {
		if (x == 1 && y == 1)
			throw new IndexOutOfBoundsException("Can't put central in neighbours");
		
		if (x < 0 || x > 2 || y < 0 || y > 2)
			throw new IndexOutOfBoundsException("Neighbours is 3x3 only. Got (" + x + "," + y + ")");
	}

	@Override
	public Iterator<T> iterator() {
		return new  MyIterator<T>(_array);
	}
}
