package ex1;


import java.util.LinkedList;

class SyncQueue<T>{
	LinkedList<T> actionList; 
	public SyncQueue(){
		actionList = new LinkedList<T>();
	};
	public synchronized boolean insert(T newAction){
		boolean r = actionList.add(newAction);
	//	System.out.println(Thread.currentThread() + "Insert =>> Queue:" + actionList);		
		notifyAll();
		return r;
	}
	public synchronized T pop(){
		//this returns the head, and removes it
		T t = actionList.remove();
		//System.out.println(Thread.currentThread() + "Remove <<= Queue:" + actionList);
		return t;
	}
	
	public synchronized boolean isEmpty(){
		//System.out.println(Thread.currentThread() + "Empty Queue:" + actionList);
		return actionList.isEmpty();
	}
	public synchronized void waitforItems() {
		//System.out.println(Thread.currentThread() + "Waiting for Queue:" + actionList);
		if(actionList.isEmpty()){
			try {
				this.wait();
			} catch (InterruptedException e) {
				//System.out.println(Thread.currentThread() + "Woke up in queue:" + actionList);
			}
		}	
	}
}