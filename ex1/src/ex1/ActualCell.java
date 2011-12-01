package ex1;

import java.util.LinkedList;

public class ActualCell implements ILivingCell, IPoint {
	final CellImpl _cell;
	NeighborArray<ILivingCell> _nearbyCellsLink;
	NeighborArray<Section> _sectionsToUpdate;
	LinkedList<ActualCell> _activeList;
	LinkedList<ActualCell> _stuckList;

	public ActualCell(int x, int y, boolean status,
			LinkedList<ActualCell> activelist, LinkedList<ActualCell> stuckList) {
		_cell = new CellImpl(x, y);
		try {
			_cell.update(0, status);
		} catch (CellException e) {
		}
		_nearbyCellsLink = new NeighborArray<ILivingCell>();
		_sectionsToUpdate = new NeighborArray<Section>();
		_activeList = activelist;
		_stuckList = stuckList;
	}

	@Override
	public boolean getStatus(int generation) throws CellException {
		return _cell.getStatus(generation);
	}

	public void setNeighbor(Directions dir, ILivingCell cell) {
		_nearbyCellsLink.put(dir, cell);
	}
	
	public void setNeighborSection(Directions dir, Section sec){
		_sectionsToUpdate.put(dir,sec);
	}

	public void attemptAdvancing() throws CellException {
		int myGeneration = _cell.getGeneration();
		boolean alive = _cell.getStatus(myGeneration);

		int sumLiving = 0;
		try {
			for (ILivingCell cell : _nearbyCellsLink) {
				if (cell.getStatus(myGeneration))
					sumLiving++;
			}						
		} catch (NotYetReadyException e) {
			
			
			
			/*
			 * TODO:
			 * if action list empty
			 *     goto waiting
			 *     return null
			 * else /?
			 * 		read messages 
			 * 		attemptAdvancing()
			 * 		return; //se we don't advance twice!
			 */
			
		} 
		
		advanceNextGeneration(alive, sumLiving);
	}

	/**
	 * @param alive
	 * @param sumLiving
	 */
	private void advanceNextGeneration(boolean alive, int sumLiving) {
		if (stayAliveConditions(alive, sumLiving)
				|| getBornConditions(alive, sumLiving)){
			_cell.advance(true);
		} else {
			_cell.advance(false);
		}
		
		/*
		 * if (borderCell)
		 * 		updateNeighbors?
		 */
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

	@Override
	public int getX() {
		return _cell.getX();
	}

	@Override
	public int getY() {
		return _cell.getY();
	}

}
