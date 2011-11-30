/**
 * 
 */
package ex1;

/**
 * @author Dima
 *
 */
public class CellImpl implements ILivingCell, IUpdateableCell {
	final Position _position;
	int _currentGeneration;
	boolean[] _lastTwoStates;

	/* (non-Javadoc)
	 * @see ex1.Living#getStatus(int)
	 */
	
	public CellImpl(int x, int y){
		_position = new Position(x, y);
		_lastTwoStates = new boolean[2];
	}
	
	public int getGeneration(){
		return _currentGeneration;
	}
	
	@Override
	public boolean getStatus(int generation) throws CellException {
		if (generation > _currentGeneration)
			throw new NotYetReadyException();
		if (generation < _currentGeneration - 1)
			throw new TooOldException();
		
		return _lastTwoStates[generation % 2];
	}

	@Override
	public int hashCode() {
		return _position.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof CellImpl))
			return false;
		CellImpl rts = (CellImpl) obj;
		return (_position == rts._position);
	}
	
	public void update(int generation, boolean status) throws CellException{
		if (generation != _currentGeneration + 1)
			throw new UnknownException();
		
		_currentGeneration = generation;
		_lastTwoStates[generation] = status;;
	}
	
}
