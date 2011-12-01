/**
 * 
 */
package ex1;

import java.util.LinkedList;

/**
 * @author Dima
 *
 */

/**
 * TODO: create ActualCellFactory
 * it will hold for itself
 * ptr->initalBoard X
 * ptr->cellsWaiting X
 * ptr->cellsStuck X
 * ptr->_nearbySections X
 * totalBoardWidth (maybe - we can't have this *public* *static*
 * 
 * these will be copied into the cell.
 * this info will sit inside each ActualCel, allowing the cell
 * to handle itself: move itself on fail and or success.
 */
public class ActualCellFactory {
	boolean _initialBoard[][];
	LinkedList<ActualCell> _activeList;
	LinkedList<ActualCell> _stuckList;
	SynchronizedActionQueue _actions;
	
	public ActualCellFactory(boolean board[][], LinkedList<ActualCell> active, 
			LinkedList<ActualCell> stuck, SynchronizedActionQueue actions){
		_initialBoard = board;
		_activeList = active;
		_stuckList = stuck;
		_actions = actions;
	}
	
	public ActualCell makeActualCell(int x, int y){
		return new ActualCell(x, y, _initialBoard[y][x], _activeList, _stuckList);
	}
}
