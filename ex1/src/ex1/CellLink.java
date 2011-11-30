/**
 * 
 */
package ex1;

/**
 * @author Dima
 *
 */
public class CellLink implements ILivingCell {
	CellImpl _cellLink;

	
	public CellLink(CellImpl c){
		_cellLink = c;
	}
	/* (non-Javadoc)
	 * @see ex1.Living#getStatus(int)
	 */
	@Override
	public boolean getStatus(int generation) throws CellException {
		return _cellLink.getStatus(generation);
	}

}
