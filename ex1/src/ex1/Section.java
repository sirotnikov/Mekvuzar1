/**
 * 
 */
package ex1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author Dima
 *
 */
public class Section {

	HashMap<Point,ActualCell> _cellsMap;
	HashMap<Point,ILivingCell> _neighborCellCache;
	
	SynchronizedActionQueue _actions;
	
	/**
	 * TODO: add function which allows inserting into _actions
	 * TODO: add function which traverses actions:
	 * 
	 * 	while(!_actions.isEmpty()){
	 *		Action a = _actions.pop()
	 *		a.perform(); 	//for this we must have Action
	 *						//know about _neighborCellCache;
	 *						//Maybe, ActionFactory? 
	 *	} 	
	 */
	
	LinkedList<ActualCell> _cellsActive;
	LinkedList<ActualCell> _cellsStuck;
	
	CellLinkFactory _linkMaker;
	/**
	 * TODO: create ActualCellFactory
	 * it will hold for itself
	 * ptr->initalBoard
	 * ptr->cellsWaiting
	 * ptr->cellsStuck
	 * totalBoardWidth (maybe - we can't have this *public* *static*
	 * 
	 * these will be copied into the cell.
	 * this info will sit inside each ActualCel, allowing the cell
	 * to handle itself: move itself on fail and or success.
	 */

	
	int _width;
	int _height;
	int _xOffset;
	int _yOffset;

	int _totalBoardWidth;
	int _totalBoardHeight;
	
	public Section(int height,int width, int xOffset, int yOffset,
			int boardHeight, int boardWidth, boolean initalBoard[][]){
		_width = width;
		_height = height;
		_xOffset = xOffset;
		_yOffset = yOffset;
		_totalBoardHeight = boardHeight;
		_totalBoardWidth = boardWidth;


		_cellsMap = new HashMap<Point,ActualCell>();	
		_neighborCellCache = new HashMap<Point,ILivingCell>();
		_linkMaker = new CellLinkFactory(_neighborCellCache);
		
		initCells(initalBoard);
		
	}
	
	public void Solve(){
		//TODO: this should be run by threads
		
		initNeighbors();
	}
	
	
	/**
	 * 
	 */
	private void initCells(boolean initalBoard[][]) {
		for (int y = _yOffset; y < _yOffset + _height; y++)
			for (int x = _xOffset; x < _xOffset + _width; x++){
				ActualCell cell = new ActualCell(x, y, initalBoard[y][x]);
				_cellsMap.put(new Point(x,y), cell);
			}
	}
	
	private void initNeighbors() {
		for (ActualCell cell : _cellsMap.values()){
			
			int x = cell.getX();
			int y = cell.getY();
			
			System.out.print("("+x+","+y+")");
			boolean withinBoard = checkWithinBoundries(x, y); 
			
			if(y > 0 && x > 0){
				setNeighbor(cell, Directions.NORTH_WEST, withinBoard);
			}
							
			if (y > 0){
				setNeighbor(cell, Directions.NORTH, withinBoard);
			}
			
			if (y > 0 && x < _totalBoardWidth - 1){
				setNeighbor(cell, Directions.NORTH_EAST, withinBoard);
			}
			
			if (x > 0){
				setNeighbor(cell, Directions.WEST, withinBoard);
			}
			
			if (x < _totalBoardWidth - 1){
				setNeighbor(cell, Directions.EAST, withinBoard);
			}
			
			if(y < _totalBoardHeight - 1 && x <_totalBoardWidth - 1){
				setNeighbor(cell, Directions.SOUTH_EAST, withinBoard);
			}
			
			if(y < _totalBoardHeight - 1){
				setNeighbor(cell, Directions.SOUTH, withinBoard);
			}
			
			if(y < _totalBoardHeight -1 && x > 0){
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
		return (x > _xOffset) && (y > _yOffset) && 
				(x < _xOffset + _width - 1) && (y < _yOffset + _width - 1);
	}
	
	/**
	 * @param c
	 * @param direction
	 * @param withinBoard
	 */
	private void setNeighbor(ActualCell c, Directions direction,
			boolean withinBoard) {
		
		//direction enum can properly convert an X and a Y!
		int newX = direction.newX(c.getX());
		int newY = direction.newY(c.getY());
		
		Point p = new Point(newX,newY);
		ILivingCell neighbor = (withinBoard) ?  _cellsMap.get(p) :	_linkMaker.newLink(p);
		c.setNeighbor(direction, neighbor);
	}
	
}
