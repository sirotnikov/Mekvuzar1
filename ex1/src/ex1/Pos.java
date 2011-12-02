package ex1;

public class Pos implements IPoint {
	 final int _x;
	 final int _y;
	 public static int _rowLength;
	
	Pos(int x, int y){
		if (x < 0 || y < 0)
			throw new IndexOutOfBoundsException("Point can only be positive: ("+x+","+y+")");
		_x = x;
		_y = y;
	}

	@Override
	public String toString() {
		return new String("(" + _x + "," + _y + ")");
	}
	/**
	 * @return int describing the X position of the point
	 */
	public int getX(){
		return _x;
	}
	/**
	 * @return int describing the Y position of the point
	 */	
	public int getY(){
		return _y;
	}
	
	@Override
	public int hashCode() {
		return (_y * _rowLength) + _x;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof Pos))
			return false;
		Pos rts = (Pos) obj;
		return ((_x == rts._x) && (_y == rts._y));
	}
}
