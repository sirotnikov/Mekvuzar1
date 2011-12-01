/**
 * 
 */
package ex1;

/**
 * @author Dima
 *
 */
public class CellImpl implements ILivingCell, IUpdateableCell, IPoint {
	final Point _point;
	int _currentGeneration;
	boolean[] _lastTwoStates;

	/* (non-Javadoc)
	 * @see ex1.Living#getStatus(int)
	 */
	
	public CellImpl(int x, int y){
		_point = new Point(x, y);
		_currentGeneration = -1;
		_lastTwoStates = new boolean[2];
	}
	
	public CellImpl(Point p) {
		_point = p;
		_currentGeneration = -1;
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
		return _point.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof CellImpl))
			return false;
		CellImpl rts = (CellImpl) obj;
		return (_point == rts._point);
	}
	
	public void update(int generation, boolean status) throws CellException{
		if (generation != _currentGeneration + 1)
			throw new UnknownException();
		
		_currentGeneration = generation;
		_lastTwoStates[generation % 2] = status;;
	}

	@Override
	public int getX() {
		return _point.getX();
	}

	@Override
	public int getY() {
		return _point.getY();
	}
	
}
