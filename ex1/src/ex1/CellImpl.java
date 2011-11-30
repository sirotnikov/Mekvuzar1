/**
 * 
 */
package ex1;

import java.util.HashMap;

/**
 * @author Dima
 *
 */
public class CellImpl implements ILivingCell, IUpdateableCell {
	final Position _position;
	int _currentGeneration;
	HashMap<Integer, Boolean> _lastTwoGenerations;

	/* (non-Javadoc)
	 * @see ex1.Living#getStatus(int)
	 */
	
	public CellImpl(int x, int y, int rowLength){
		_position = new Position(x, y, rowLength);
		_currentGeneration = -1;
		_lastTwoGenerations = new HashMap<Integer,Boolean>();
	}
	
	@Override
	public boolean getStatus(int generation) throws CellException {
		if (generation > _currentGeneration)
			throw new NotYetReadyException();
		if (generation < _currentGeneration - 1)
			throw new TooOldException();
		
		if (! (_lastTwoGenerations.containsKey(generation)))
			throw new UnknownException();
		
		return _lastTwoGenerations.get(generation);
	}

	public void update(int generation, boolean status) throws CellException{
		if (generation != _currentGeneration + 1)
			throw new UnknownException();
		
		_currentGeneration = generation;
		_lastTwoGenerations.put(generation, status);
		_lastTwoGenerations.remove(generation - 2);
	}
	
}
