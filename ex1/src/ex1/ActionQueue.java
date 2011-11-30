package ex1;

import java.util.ArrayList; 

class ActionQueue{

	
	
	
	ArrayList<Action> actionList; 
	public ActionQueue(){
		actionList = new ArrayList<Action>();
	};
	public synchronized boolean enqueue(Action newAction){
		boolean success =  actionList.add(newAction);
		return success;
	}
	public synchronized Action dequeue(){
		if (actionList.isEmpty() == true){
			throw  new ActionqueueException();
		}
		return (Action)actionList.get(0);
	}
	
	public synchronized boolean isEmpty(){
		return actionList.isEmpty();
	}
}