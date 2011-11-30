/**
 * 
 */
package ex1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;


/**
 * @author Dima
 *
 */
public class MyIterator <E> implements Iterator<E> {
	HashMap<Position, E> _hash;
	Collection<E> _values;
	Iterator<E> _iterator;

	public MyIterator(HashMap<Position, E> hash) {
		hash = new HashMap<Position,E>(hash);
		hash.remove(new Position(1,1,3));
		_values = hash.values();
		_iterator = _values.iterator();
	}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return _iterator.hasNext();
	}

	@Override
	public E next() {
		// TODO Auto-generated method stub
		return _iterator.next();
	}

	@Override
	public void remove() {
		return;
	}

}
