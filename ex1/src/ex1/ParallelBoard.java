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
		int normalSecHgtSize = boardHeight / hSections;
		int normalSecWdtSize = boardWidth / vSections;
		int lastSecHgtSize = boardHeight % hSections;
		int lastSecWdtSize = boardWidth % vSections;
		for(int i=0;i<hSections;i++)
			for(int j=0;j<vSections;j++){
				_sectionsArray[i][j].setTotalBoardHeight(boardHeight);
				_sectionsArray[i][j].setTotalBoardWidth(boardWidth);
				_sectionsArray[i][j].setXOffset(i*normalSecHgtSize);
				_sectionsArray[i][j].setYOffset(normalSecWdtSize);
				if(i != hSections-1){
					_sectionsArray[i][j].setHeight(normalSecHgtSize); 
				}
				else
				{
					_sectionsArray[i][j].setHeight(lastSecHgtSize); 
				}
				if(i != hSections-1){
					_sectionsArray[i][j].setWidth(normalSecWdtSize);
				}
				else
				{
					_sectionsArray[i][j].setWidth(lastSecWdtSize);
				}
			}
		
		
	}
	public boolean sectionCopy(CellImpl sourceBoard,CellImpl targetBoard[][]){
		return true;
	}

}
