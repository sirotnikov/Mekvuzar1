/**
 * 
 */
package ex1;

/**
 * @author Dima
 *
 */
public class ParallelBoard {
	Section[][] _sectionsArray;	//Should eventually be Section[_vSections][_hSections];
	int _totalWidth;
	int _totalHeight;
	int _hSections;
	int _vSections;
	ParallelBoard(int boardHeight,int boardWidth,int hSections,int vSections,boolean serialBoard[][]){
		_sectionsArray = new Section[hSections][vSections];
		_totalHeight = boardHeight;
		_totalWidth = boardWidth;
		_hSections = hSections;
		_vSections = vSections;
		
		sectionCopy(Cell sourceBoard,Cell targetBoard[][])
	}
	GetSections(int boardHeight,int boardWidth,int hSections,int vSections,)
}
