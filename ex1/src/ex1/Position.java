package ex1;

public class Position {
	final int _x;
	final int _y;
	final int _hash;
	
	Position(int x, int y){
		_x = x;
		_y = y;
		_hash = (y * ParallelBoard._rowLength) + x;
	}
	
	public int getX(){
		return _x;
	}
	
	public int getY(){
		return _y;
	}
	
	@Override
	public int hashCode() {
		return _hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof Position))
			return false;
		Position rts = (Position) obj;
		return ((_x == rts._x) && (_y == rts._y));
	}
}
