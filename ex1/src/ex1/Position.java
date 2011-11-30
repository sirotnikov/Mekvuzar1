package ex1;

public class Position {
	final int _x;
	final int _y;
	final int _hash;
	
	Position(int x, int y, int rowLength){
		_x = x;
		_y = y;
		_hash = y * rowLength + x;
	}
	
	public int getX(){
		return _x;
	}
	
	public int getY(){
		return _y;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
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
