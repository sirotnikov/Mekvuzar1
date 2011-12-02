package ex1;

import java.util.HashSet;

public class ActualCell implements ILivingCell, IPoint {
	final CellImpl _cell;
	NeighborArray<ILivingCell> _cellsToReadFrom;
	HashSet<Section> _sectionsToUpdate;
	boolean _hasSectionsToUpdate = false;

	public ActualCell(int x, int y, boolean status) {
		_cell = new CellImpl(x, y);
		try {
			_cell.update(0, status);
		} catch (CellException e) {
		}
		_cellsToReadFrom = new NeighborArray<ILivingCell>();
		_sectionsToUpdate = new HashSet<Section>();
	}

	@Override
	public boolean getStatus(int generation) throws CellException {
		return _cell.getStatus(generation);
	}

	@Override
	public String toString() {
		return _cell.toString();
	}
	public void setNeighbor(Directions dir, ILivingCell cell) {
		_cellsToReadFrom.put(dir, cell);
	}
	
	public void addSectionToUpdate(Section sec){
		_hasSectionsToUpdate = true;
		_sectionsToUpdate.add(sec);
	}

	public int attemptAdvancing() throws CellException {
		int myGeneration = _cell.getGeneration();
		boolean alive = _cell.getStatus(myGeneration);

		int sumLiving = 0;
		for (ILivingCell cell : _cellsToReadFrom) {			
			if (cell.getStatus(myGeneration))
				sumLiving++;
		}								
		return advanceNextGeneration(alive, sumLiving);
	}

	/**
	 * @param alive
	 * @param sumLiving
	 */
	private int advanceNextGeneration(boolean alive, int sumLiving) {
		int newGeneration = -1;
		if (stayAliveConditions(alive, sumLiving)
				|| getBornConditions(alive, sumLiving)){
			newGeneration = _cell.advance(true);
		} else {
			newGeneration = _cell.advance(false);
		}

		updateNeighborSections();
		return newGeneration;
	}
	
	public int getGeneration(){
		return _cell.getGeneration();
	}

	/**
	 * 
	 */
	public void updateNeighborSections() {
		
		if (_hasSectionsToUpdate){
			for(Section sec : _sectionsToUpdate){
				Action a = new Action(_cell);
				sec.pushAction(a);
			}
			//System.out.println(Thread.currentThread() + " updated:" +
			//_sectionsToUpdate + "with gen " + _cell.getGeneration());
			
		} 
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
