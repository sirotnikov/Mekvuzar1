/**
 * 
 */
package ex1;

/**
 * @author Dima
 *
 */
public class NeighborCell implements IUpdateableCell, ILivingCell {
	CellImpl _cell;

	
	public NeighborCell(int x, int y) {
		_cell = new CellImpl(x, y);
	}

	public NeighborCell(Pos p) {
		_cell = new CellImpl(p);
	}
	
	/* (non-Javadoc)
	 * @see ex1.LivingCell#getStatus(int)
	 */
	@Override
	public boolean getStatus(int generation) throws CellException {
		return _cell.getStatus(generation);
	}

	/* (non-Javadoc)
	 * @see ex1.UpdateableCell#update(int, boolean)
	 */
	@Override
	public void update(int generation, boolean status) throws CellException {
		_cell.update(generation, status);
	}

}
