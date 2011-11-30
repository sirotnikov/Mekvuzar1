package ex1;

import java.util.Iterator;

public class ActualCell implements ILivingCell {
	final CellImpl _cell;
	NeighborArray<ILivingCell> _nearbyCells;

	
	public ActualCell(int x, int y, int rowLength){
		_cell = new CellImpl(x, y, rowLength);
	}
	@Override
	public boolean getStatus(int generation) throws CellException {
		return _cell.getStatus(generation);
	}
	
	public void setNeighbors(NeighborArray<ILivingCell> nearbyCells){
		_nearbyCells = nearbyCells;
	}
	
	public void advanceGeneration() throws CellException{
		Iterator<ILivingCell> iterator = _nearbyCells.iterator();
		int myGeneration = _cell.getGeneration();
		boolean alive = _cell.getStatus(myGeneration);
		
		int sumLiving = 0;
		
		while(iterator.hasNext()){
			//this can throw, and then we need to advance
			if (iterator.next().getStatus(myGeneration))
				sumLiving++;
		}
		
		if (stayAliveConditions(alive, sumLiving) || getBornConditions(alive, sumLiving))
			_cell.update(myGeneration + 1, true);
	}
	/**
	 * @param alive
	 * @param sumLiving
	 * @return
	 */
	private boolean getBornConditions(boolean alive, int sumLiving) {
		return !alive && (sumLiving == 3);
	}
	/**
	 * @param alive
	 * @param sumLiving
	 * @return
	 */
	private boolean stayAliveConditions(boolean alive, int sumLiving) {
		return alive && (sumLiving == 2 || sumLiving == 3);
	}

}