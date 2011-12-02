package ex1;

import java.util.HashSet;

/**
 * This class represents a real "living" cell in the board.
 * It's used by the section to represent its own cells for which it has
 * full information and control over.
 * Thus they can advance up the ages.
 * @author Dima
 *
 */
public class ActualCell implements ILivingCell, IPoint {
	final CellImpl _cell;
	NeighborArray<ILivingCell> _cellsToReadFrom;
	HashSet<Section> _sectionsToUpdate;
	boolean _hasSectionsToUpdate = false;

	/**
	 * Creates a new ActualCell
	 * @param x - x pos
	 * @param y - y pos
	 * @param status - is it dead or alive
	 */
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
	
	/**
	 * Adds a neighboring cell, which has to be counted for age advances.
	 * @param dir - the direction in which the neighbor is, relative to our cell
	 * @param neighbor - the neighbor cell.
	 */
	public void setNeighbor(Directions dir, ILivingCell neighbor) {
		_cellsToReadFrom.put(dir, neighbor);
	}
	
	/**
	 * Tells our cell he has to update Section <b><tt>sectionToUpdate</tt></b> when he advances. 
	 * @param sectionToUpdate - section to update.
	 */
	public void addSectionToUpdate(Section sectionToUpdate){
		_hasSectionsToUpdate = true;
		_sectionsToUpdate.add(sectionToUpdate);
	}

	/**
	 * Tries to advance to next generation by polling its neighbors,
	 * and deciding if he should live or die.
	 * If one of the neighbors is not ready - throws a NotYetReadyException.
	 * @return - on success, returns the new age of the cell.
	 * @throws CellException
	 */
	public int attemptAdvancing() throws CellException {
		int myGeneration = _cell.getGeneration();
		boolean alive = _cell.getStatus(myGeneration);

		int sumLiving = 0;
		/*
		 * poll neighbor cells
		 */
		for (ILivingCell cell : _cellsToReadFrom) {
			if (cell.getStatus(myGeneration))	//this potentially throws exception
				sumLiving++;
		}								
		/*
		 * If' we're here - everything went OK.
		 */
		return advanceNextGeneration(alive, sumLiving);
	}

	/**
	 * Advances the cell to the next generation based on it's current
	 * @param alive - whether or not the cell was alive the previous generation
	 * @param sumLiving - amount of living cells sorrounding the cell
	 */
	private int advanceNextGeneration(boolean alive, int sumLiving) {
		int newGeneration = -1;
		
		if (stayAliveConditions(alive, sumLiving)
				|| getBornConditions(alive, sumLiving)){
			//be alive next turn
			newGeneration = _cell.advance(true);
		} else {
			//die!!
			newGeneration = _cell.advance(false);
		}

		updateNeighborSections();
		return newGeneration;
	}
	
	/**
	 * @return currentGeneration
	 */
	public int getGeneration(){
		return _cell.getGeneration();
	}

	/**
	 * Sends messages with his new generation to all neighboring sections
	 */
	public void updateNeighborSections() {
		
		if (_hasSectionsToUpdate){
			for(Section sec : _sectionsToUpdate){
				Action a = new Action(_cell);
				sec.pushAction(a);
			}
		} 
	}

	/**
	 * Checks conditions for the cell being born
	 * @param alive - is the cell currently alive
	 * @param sumLiving - the amount of sorrounding living cells
	 * @return
	 */
	private boolean getBornConditions(boolean alive, int sumLiving) {
		return !alive && (sumLiving == 3);
	}

	/**
	 * Checks conditions for a cell remaining alive
	 * @param alive - is the cell currently alive
	 * @param sumLiving - the amount of sorrounding living cells
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
