package ex1;


import java.util.LinkedList;

class SynchronizedActionQueue{
	LinkedList<Action> actionList; 
	public SynchronizedActionQueue(){
		actionList = new LinkedList<Action>();
	};
	public synchronized boolean insert(Action newAction){
		return actionList.add(newAction);
	}
	public synchronized Action pop(){
		//this returns the head, and removes it
		return actionList.remove();
	}
	
	public synchronized boolean isEmpty(){
		return actionList.isEmpty();
	}
}