package ex1;

class Action{
	final private Point _position;
	final private int _gen;
	final private boolean value;
	
	public Action(int newGen,boolean newVal,Point newPos){
		_gen = newGen;
		value = newVal;
		_position = newPos;
	}
	public int getGen(){return _gen;};
	public Point getPos(){return _position;};
	public boolean getVal(){return value;};
}