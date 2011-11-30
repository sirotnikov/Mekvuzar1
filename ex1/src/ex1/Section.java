/**
 * 
 */
package ex1;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Dima
 *
 */
public class Section {

	HashMap<Point,ActualCell> _cellsMap;
	HashSet<ActualCell> _cellsSet;
	HashMap<Point,ILivingCell> _neighborsMap;
	CellLinkFactory _linkMaker;
	int _width;
	int _height;
	int _xOffset;
	int _yOffset;

	int _totalBoardWidth;
	int _totalBoardHeight;
	public Section(int height,int width){
		_width = width;
		_height = height;
		_cellsMap = new HashMap<Point,ActualCell>();	
		_cellsSet = new HashSet<ActualCell>();
		_neighborsMap = new HashMap<Point,ILivingCell>();
		_linkMaker = new CellLinkFactory(_neighborsMap);
		
		initCells();
		
		//this should be done by threads
		initNeighbors();
	}
	/**
	 * 
	 */
	private void initCells() {
		for (int y = _yOffset; y < _yOffset + _height; y++)
			for (int x = _xOffset; x < _xOffset + _width; x++){
				ActualCell cell = new ActualCell(x, y); 
				_cellsSet.add(cell);
				_cellsMap.put(new Point(x,y), cell);
			}
	}
	
	private void initNeighbors() {
		for (ActualCell cell : _cellsSet){
			
			int x = cell.getX();
			int y = cell.getY();
			boolean withinBoard = ((x > _xOffset) && (y > _yOffset) && 
					(x < _xOffset + _width - 1) && (y < _yOffset + _width - 1)); 
			
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
	
	public int getWidth(){
		return _width;
	}
	public int getHeight(){
		return _height;
	}
	public int getXOffset(){
		return _xOffset;
	}
	public int getYOffset(){
		return _yOffset;
	}
	public int getTotalBoardWidth(){
		return _totalBoardWidth;
	}
	public int getTotalBoardHeight(){
		return _totalBoardHeight;
	}
	public void setWidth(int newWidth){
		_width = newWidth;
	}
	public void setHeight(int newHeight){
		_height = newHeight;
	}
	public void setXOffset(int newXOffset){
		_xOffset = newXOffset;
	}
	public void setYOffset(int newYoffset){
		_yOffset = newYoffset;
	}
	public void setTotalBoardWidth(int newTotalBoardWidth){
		_totalBoardWidth = newTotalBoardWidth;
	}
	public void setTotalBoardHeight(int newTotalBoardHeight){
		 _totalBoardHeight = newTotalBoardHeight;
	}

	
}
