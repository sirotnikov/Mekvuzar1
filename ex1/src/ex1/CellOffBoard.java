/**
 * 
 */
package ex1;

/**
 * @author Dima
 *
 */
public class CellOffBoard implements ILivingCell {

	/* (non-Javadoc)
	 * @see ex1.Living#getStatus(int)
	 */
	@Override
	public boolean getStatus(int generation) throws CellException {
		return false;
	}

}
