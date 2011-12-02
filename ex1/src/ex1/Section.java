/**
 * 
 */
package ex1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author Dima
 * 
 */
public class Section {

	HashMap<Point, ActualCell> _cellsMap;
	HashMap<Point, NeighborCell> _neighborCellCache;

	SyncQueue<Action> _actions;
	NeighborArray<Section> _nearbySections;

	LinkedList<ActualCell> _cellsActive;
	LinkedList<ActualCell> _cellsStuck;

	CellLinkFactory _linkMaker;

	int _width;
	int _height;
	int _xOffset;
	int _yOffset;

	int _totalBoardWidth;
	int _totalBoardHeight;
	int _finalGeneration;

	public Section(int height, int width, int xOffset, int yOffset,
			int boardHeight, int boardWidth, boolean initalBoard[][],
			int generations) {
		_width = width;
		_height = height;
		_xOffset = xOffset;
		_yOffset = yOffset;
		_totalBoardHeight = boardHeight;
		_totalBoardWidth = boardWidth;
		_finalGeneration = generations;

		_cellsMap = new HashMap<Point, ActualCell>();
		_neighborCellCache = new HashMap<Point, NeighborCell>();
		_linkMaker = new CellLinkFactory(_neighborCellCache);

		_cellsActive = new LinkedList<ActualCell>();
		_cellsStuck = new LinkedList<ActualCell>();
		_actions = new SyncQueue<Action>();

		_nearbySections = new NeighborArray<Section>();

		initCells(initalBoard);

	}

	@Override
	public String toString() {
		return new String("[" + _xOffset + "," + _yOffset + "]");
	}
	public void setNeighbor(Directions dir, Section section) {
		_nearbySections.put(dir, section);
	}

	/**
	 * 
	 * @throws CellException
	 */
	public void Solve() throws CellException {
		initCellsNeighborSections();		
		initNeighborCells();
		
		while (!_cellsActive.isEmpty()) {		
			walkOverActiveCells();

			if (!_cellsActive.isEmpty()){
				continue;	
			}			
			if (_cellsStuck.isEmpty()){
				break;	//no more active or stuck cells? - we're done!
			}
			
			//Read up on updates
			readActions();
			
			//Switch
			LinkedList<ActualCell> oldEmptyActive = _cellsActive;
			_cellsActive = _cellsStuck;
			_cellsStuck = oldEmptyActive;				
		}
	}

	/**
	 * 
	 * @throws CellException
	 */
	private void readActions() throws CellException{
		if (_actions.isEmpty()){
			_actions.waitforItems();
		}
		
	 	while(!_actions.isEmpty()){
			Action a = _actions.pop();
			_neighborCellCache.get(a.getPos()).update(a.getGen(),a.getVal());
		}
	}
	
	/** 
	 * @throws CellException
	 */
	private void walkOverActiveCells() throws CellException{

		//We're using an iterator instead of "for each" to get O(1) time for removal.
		ListIterator<ActualCell> iterator = _cellsActive.listIterator();
		
		while(iterator.hasNext()){
			ActualCell currentCell = iterator.next();
			int newGeneration = -1;
			try{
				newGeneration = currentCell.attemptAdvancing();
				if (newGeneration == _finalGeneration){
					iterator.remove();
				}
			}catch (NotYetReadyException e){
				iterator.remove();	//removes currentCell
				_cellsStuck.add(currentCell);
			}	
		}
		//System.out.println(Thread.currentThread() + "done walking");
	}

	public void updateResultBoard(boolean resultBoard[][]) throws Exception{
		for (ActualCell cell : _cellsMap.values()){
			int x = cell.getX();
			int y = cell.getY();
			try {
				resultBoard[y][x] = cell.getStatus(_finalGeneration);
			} catch (NotYetReadyException e) {
				throw new Exception("There's a cell left who is the wrong generation!");
			}
		}
	}
	
	
	/**
	 * 
	 * @param initalBoard
	 */
	private void initCells(boolean initalBoard[][]) {

		for (int y = _yOffset; y < _yOffset + _height; y++){
			for (int x = _xOffset; x < _xOffset + _width; x++) {
				Point p = new Point(x, y);
				ActualCell cell = 
						new ActualCell(x, y, initalBoard[y][x]);
				_cellsMap.put(p, cell);
				_cellsActive.add(cell);
			}

		}
		
	}

	private void initNeighborCells() {
		for (ActualCell cell : _cellsMap.values()) {

			int x = cell.getX();
			int y = cell.getY();	

			if (y > 0 && x > 0) {
				setNeighborForCell(cell, Directions.NORTH_WEST);
			}
			if (y > 0) {
				setNeighborForCell(cell, Directions.NORTH);
			}
			if (y > 0 && x < _totalBoardWidth - 1) {
				setNeighborForCell(cell, Directions.NORTH_EAST);
			}
			if (x > 0) {
				setNeighborForCell(cell, Directions.WEST);
			}
			if (x < _totalBoardWidth - 1) {
				setNeighborForCell(cell, Directions.EAST);
			}
			if (y < _totalBoardHeight - 1 && x < _totalBoardWidth - 1) {
				setNeighborForCell(cell, Directions.SOUTH_EAST);
			}
			if (y < _totalBoardHeight - 1) {
				setNeighborForCell(cell, Directions.SOUTH);
			}
			if (y < _totalBoardHeight - 1 && x > 0) {
				setNeighborForCell(cell, Directions.SOUTH_WEST);
			}
		}
	}

	private void initCellsNeighborSections(){
		for (ActualCell cell : _cellsMap.values()){
			
			if (cell.getY() == _yOffset && cell.getY() != 0){
				Section target = _nearbySections.get(Directions.NORTH);
				cell.addSectionToUpdate(target);			
			}
			
			if (cell.getY() == (_yOffset + _height - 1) && cell.getY() != _totalBoardHeight - 1 ){
				Section target = _nearbySections.get(Directions.SOUTH);
				cell.addSectionToUpdate(target);
			}
			
			if ((cell.getX() == _xOffset) && (cell.getX() != 0)){
				Section target = _nearbySections.get(Directions.WEST);
				cell.addSectionToUpdate(target);
			}
			
			if ((cell.getX() == _xOffset + _width - 1) && (cell.getX() != _totalBoardWidth - 1)){
				Section target = _nearbySections.get(Directions.EAST);
				cell.addSectionToUpdate(target);			
			}
			
			/*
			 * repairs for 4 cells
			 */
			
			if (cell.getX() == _xOffset 
					&& cell.getY() == _yOffset 
					&& cell.getX() != 0
					&& cell.getY() != 0){
				Section target = _nearbySections.get(Directions.NORTH_WEST);
				cell.addSectionToUpdate(target);
			} 
			if (cell.getX() == _xOffset 
					&& cell.getY() == _yOffset + _height - 1 
					&& cell.getX() != 0
					&& cell.getY() != _totalBoardHeight - 1){
				Section target = _nearbySections.get(Directions.SOUTH_WEST);
				cell.addSectionToUpdate(target);
			}

			if ((cell.getX() == _xOffset + _width - 1) 
					&& cell.getY() == _yOffset 
					&& cell.getY() != 0
					&& cell.getX() != _totalBoardWidth - 1){
				Section target = _nearbySections.get(Directions.NORTH_EAST);
				cell.addSectionToUpdate(target);
			}

			if ((cell.getX() == _xOffset + _width - 1) 
					&& (cell.getY() == _yOffset + _height - 1)
					&& (cell.getX() != _totalBoardWidth - 1)
					&& (cell.getY() != _totalBoardHeight - 1)){
				Section target = _nearbySections.get(Directions.SOUTH_EAST);
				cell.addSectionToUpdate(target);
			}
			cell.updateNeighborSections();			
		}		
	}
	
	
	/**
	 * @param x
	 * @param y
	 * @return true/false - is the (x,y) not
	 */
	private boolean checkWithinBoundries(int x, int y) {
		return (_xOffset <= x) && (x < _xOffset + _width) &&
				(_yOffset <= y) && (y < _yOffset + _height);
	}

	public boolean pushAction(Action newAction) {
		return _actions.insert(newAction);
	}

	/**
	 * @param cell
	 * @param direction
	 * @param withinBoard
	 */
	private void setNeighborForCell(ActualCell cell, Directions direction) {		
		int newX = direction.newX(cell.getX());
		int newY = direction.newY(cell.getY());
		
		boolean newPisInBoard = checkWithinBoundries(newX, newY);

		Point p = new Point(newX, newY);
		ILivingCell neighbor = (newPisInBoard) ? _cellsMap.get(p) :
			_linkMaker.newLink(p);
		
		cell.setNeighbor(direction, neighbor);
	}

}
 
