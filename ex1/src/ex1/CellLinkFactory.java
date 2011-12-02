/**
 * 
 */
package ex1;

import java.util.HashMap;

/**
 * @author Dima
 *
 */
public class CellLinkFactory {
	HashMap<Pos, NeighborCell> _targetMap;

	public CellLinkFactory(HashMap<Pos, NeighborCell> neighborsMap) {
		_targetMap = neighborsMap;
	}

	public ILivingCell newLink(int x, int y) {
		Pos p = new Pos(x,y);
		
		if (!(_targetMap.containsKey(p))){
			NeighborCell cell = new NeighborCell(x, y);
			_targetMap.put(p, cell);
		}
		
		return _targetMap.get(p);
	}

	public ILivingCell newLink(Pos p) {
		if (!(_targetMap.containsKey(p))){
			NeighborCell cell = new NeighborCell(p);
			_targetMap.put(p, cell); 
		}
		
		return new CellLink(_targetMap.get(p));
	}

}
