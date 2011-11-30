/**
 * 
 */
package ex1;

/**
 * @author Dima
 *
 */
public class CellLink implements ILivingCell {
	ILivingCell _cellLink;

	
	public CellLink(ILivingCell cell){
		_cellLink = cell;
	}
	/* (non-Javadoc)
	 * @see ex1.Living#getStatus(int)
	 */
	@Override
	public boolean getStatus(int generation) throws CellException {
		return _cellLink.getStatus(generation);
	}

}
