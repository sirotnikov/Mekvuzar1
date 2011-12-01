/**
 * 
 */
package ex1;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Dima
 * 
 */
public class Section {

	HashMap<Point, ActualCell> _cellsMap;
	HashMap<Point, ILivingCell> _neighborCellCache;

	SynchronizedActionQueue _actions;
	NeighborArray<Section> _nearbySections;

	LinkedList<ActualCell> _cellsActive;
	LinkedList<ActualCell> _cellsStuck;

	CellLinkFactory _linkMaker;
	ActualCellFactory _cellMaker;

	int _width;
	int _height;
	int _xOffset;
	int _yOffset;

	int _totalBoardWidth;
	int _totalBoardHeight;

	public Section(int height, int width, int xOffset, int yOffset,
			int boardHeight, int boardWidth, boolean initalBoard[][]) {
		_width = width;
		_height = height;
		_xOffset = xOffset;
		_yOffset = yOffset;
		_totalBoardHeight = boardHeight;
		_totalBoardWidth = boardWidth;

		_cellsMap = new HashMap<Point, ActualCell>();
		_neighborCellCache = new HashMap<Point, ILivingCell>();
		_linkMaker = new CellLinkFactory(_neighborCellCache);

		_cellsActive = new LinkedList<ActualCell>();
		_cellsStuck = new LinkedList<ActualCell>();
		_actions = new SynchronizedActionQueue();
		_cellMaker = new ActualCellFactory(initalBoard, _cellsActive,
				_cellsStuck, _actions);

		_nearbySections = new NeighborArray<Section>();

		initCells(initalBoard);

	}

	public void setNeighbor(Directions dir, Section section) {
		_nearbySections.put(dir, section);
	}

	private void readActions(){
	 	while(!_actions.isEmpty()){
			Action a = _actions.pop()
			a.perform(); 	//for this we must have Action
							//know about _neighborCellCache;
							//Maybe, ActionFactory? 
		} 	
	}

	public void Solve() throws CellException {
		// TODO: this should be run by threads

		initNeighbors();

		while (!_cellsActive.isEmpty() || !_cellsStuck.isEmpty()) {
			for (ActualCell c : _cellsActive) {
				c.attemptAdvancing();
			}
			// TODO: read messages

			LinkedList<ActualCell> temp = _cellsActive;
			_cellsActive = _cellsStuck;
			_cellsStuck = temp;
		}
	}

	/**
	 * 
	 */
	private void initCells(boolean initalBoard[][]) {
		for (int y = _yOffset; y < _yOffset + _height; y++)
			for (int x = _xOffset; x < _xOffset + _width; x++) {
				ActualCell cell = _cellMaker.makeActualCell(x, y);
				_cellsMap.put(new Point(x, y), cell);
			}
	}

	private void initNeighbors() {
		for (ActualCell cell : _cellsMap.values()) {

			int x = cell.getX();
			int y = cell.getY();

			System.out.print("(" + x + "," + y + ")");
			boolean withinBoard = checkWithinBoundries(x, y);

			if (y > 0 && x > 0) {
				setNeighbor(cell, Directions.NORTH_WEST, withinBoard);
			}
			if (y > 0) {
				setNeighbor(cell, Directions.NORTH, withinBoard);
			}
			if (y > 0 && x < _totalBoardWidth - 1) {
				setNeighbor(cell, Directions.NORTH_EAST, withinBoard);
			}
			if (x > 0) {
				setNeighbor(cell, Directions.WEST, withinBoard);
			}
			if (x < _totalBoardWidth - 1) {
				setNeighbor(cell, Directions.EAST, withinBoard);
			}
			if (y < _totalBoardHeight - 1 && x < _totalBoardWidth - 1) {
				setNeighbor(cell, Directions.SOUTH_EAST, withinBoard);
			}
			if (y < _totalBoardHeight - 1) {
				setNeighbor(cell, Directions.SOUTH, withinBoard);
			}
			if (y < _totalBoardHeight - 1 && x > 0) {
				setNeighbor(cell, Directions.SOUTH_WEST, withinBoard);
			}
		}
	}

	/**
	 * @param x
	 * @param y
	 * @return true/false - is the (x,y) not
	 */
	private boolean checkWithinBoundries(int x, int y) {
		return (x > _xOffset) && (y > _yOffset) && (x < _xOffset + _width - 1)
				&& (y < _yOffset + _width - 1);
	}

	public boolean pushAction(Action newAction) {
		return _actions.insert(newAction);
	}

	/**
	 * @param c
	 * @param direction
	 * @param withinBoard
	 */
	private void setNeighbor(ActualCell c, Directions direction,
			boolean withinBoard) {

		// direction enum can properly convert an X and a Y!
		int newX = direction.newX(c.getX());
		int newY = direction.newY(c.getY());

		Point p = new Point(newX, newY);
		ILivingCell neighbor = (withinBoard) ? _cellsMap.get(p) : _linkMaker
				.newLink(p);
		c.setNeighbor(direction, neighbor);

		if (!withinBoard)
			c.setNeighborSection(direction, _nearbySections.get(direction));
	}

}
