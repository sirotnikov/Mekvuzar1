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

	public static int _hSections;
	public static int _vSections;
	public static int _boardWidth;
	public static int _boardHeight;	
	
	public static int getBoardWidth(){
		return _boardWidth;
	}

	ParallelBoard(int boardHeight,int boardWidth,int hSections,int vSections,boolean serialBoard[][]){
		
		_sectionsArray = new Section[hSections][vSections];
		_boardHeight = boardHeight;
		_boardWidth = boardWidth;
		_hSections = hSections;
		_vSections = vSections;
		
		int normalSecHgtSize = _boardHeight / _hSections;
		int normalSecWdtSize = _boardWidth / _vSections;
		int lastSecHgtExtra = _boardHeight % _hSections;
		int lastSecWdtExtra = _boardWidth % _vSections;
		for(int i=0; i < _hSections;i++)
			for(int j=0; j< _vSections;j++){
				_sectionsArray[i][j].setTotalBoardHeight(_boardHeight);
				_sectionsArray[i][j].setTotalBoardWidth(_boardWidth);
				_sectionsArray[i][j].setXOffset(i*normalSecHgtSize);
				_sectionsArray[i][j].setYOffset(normalSecWdtSize);
				if(i != hSections-1){
					_sectionsArray[i][j].setHeight(normalSecHgtSize); 
				}
				else
				{
					_sectionsArray[i][j].setHeight(lastSecHgtExtra); 
				}
				if(i != hSections-1){
					_sectionsArray[i][j].setWidth(normalSecWdtSize);
				}
				else
				{
					_sectionsArray[i][j].setWidth(lastSecWdtExtra);
				}
			}
		
		
	}
	public boolean sectionCopy(CellImpl sourceBoard,CellImpl targetBoard[][]){
		return true;
	}
}
