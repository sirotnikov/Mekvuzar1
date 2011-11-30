package ex1;

public class Point implements IPoint {
	 final int _x;
	 final int _y;
	
	Point(int x, int y){
		if (x < 0 || y < 0)
			throw new IndexOutOfBoundsException("Point can only be positive: ("+x+","+y+")");
		_x = x;
		_y = y;
	}

	public int getX(){
		return _x;
	}
	
	public int getY(){
		return _y;
	}
	
	@Override
	public int hashCode() {
		return (_y * ParallelBoard._boardWidth) + _x;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof Point))
			return false;
		Point rts = (Point) obj;
		return ((_x == rts._x) && (_y == rts._y));
	}
}
