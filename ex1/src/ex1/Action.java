package ex1;

class Action{
	private Point _position;
	private int _gen;
	private boolean _value;
	/**
	 * 
	 * @param newGen
	 * @param newVal
	 * @param newPos
	 */
	public Action(int newGen,boolean newVal,Point newPos){
		_gen = newGen;
		_value = newVal;
		_position = newPos;
	}
	
	public Action(CellImpl cell){
		try {	//because of getStatus
			_position = new Point(cell.getX(), cell.getY());
			_gen = cell.getGeneration();
			_value = cell.getStatus(_gen);
		} catch (CellException e) {
		}
	}
	
	public int getGen(){return _gen;};
	public Point getPos(){return _position;};
	public boolean getVal(){return _value;};
}