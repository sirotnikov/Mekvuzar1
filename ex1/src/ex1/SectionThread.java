/**
 * 
 */
package ex1;

/**
 * @author Dima
 *
 */
public class SectionThread extends Thread {
	Section _section;
	
	public SectionThread(Section s) {
		_section = s;
	}
	
	@Override
	public void run() {
		try {
			//System.out.println(this + "started");
			_section.Solve();
			//System.out.println("\t\t" + this + "finished");
		} catch (CellException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return _section.toString();
	}

}
