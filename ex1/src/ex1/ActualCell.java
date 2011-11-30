package ex1;


public class ActualCell implements ILivingCell {
	final CellImpl _cell;
	NeighborArray<ILivingCell> _nearbyCells;

	
	public ActualCell(int x, int y){
		_cell = new CellImpl(x, y);
		_nearbyCells = new NeighborArray<ILivingCell>();
	}
	@Override
	public boolean getStatus(int generation) throws CellException {
		return _cell.getStatus(generation);
	}
	
	public void setNeighbor(Directions dir, ILivingCell cell){
		_nearbyCells.put(dir, cell);
	}
	
	public void advanceGeneration() throws CellException{
		int myGeneration = _cell.getGeneration();
		boolean alive = _cell.getStatus(myGeneration);
		
		int sumLiving = 0;
		
		for(ILivingCell cell : _nearbyCells){
			if (cell.getStatus(myGeneration))
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
