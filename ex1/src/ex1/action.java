package ex1;

class Action{
	private int gen,position;
	private boolean value;
	
	public Action(int newGen,boolean newVal,int newPos){
		gen = newGen;
		value = newVal;
		position = newPos;
	}
	public int getGen(){return gen;};
	public int getPos(){return position;};
	public boolean getVal(){return value;};
	public void setGen(int newGen){gen = newGen;};
	public void getPos(int newPos){position = newPos;};
	public void setVal(boolean newVal){value = newVal;};
}