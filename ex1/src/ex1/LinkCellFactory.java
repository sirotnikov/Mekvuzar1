/**
 * 
 */
package ex1;

import java.util.HashMap;

/**
 * @author Dima
 *
 */
public class LinkCellFactory {
	HashMap<Point, ILivingCell> _targetMap;

	public LinkCellFactory(HashMap<Point, ILivingCell> neighborsMap) {
		_targetMap = neighborsMap;
	}

	public ILivingCell newLink(int x, int y) {
		Point p = new Point(x,y);
		
		if (!(_targetMap.containsKey(p))){
			NeighborCell cell = new NeighborCell(x, y);
			_targetMap.put(p, cell);
		}
		
		return _targetMap.get(p);
	}

	public ILivingCell newLink(Point p) {
		if (!(_targetMap.containsKey(p))){
			NeighborCell cell = new NeighborCell(p);
			_targetMap.put(p, cell); 
		}
		
		return new CellLink(_targetMap.get(p));
	}

}
