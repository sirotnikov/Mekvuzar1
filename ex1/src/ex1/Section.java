/**
 * 
 */
package ex1;

/**
 * @author Dima
 *
 */
public class Section {
	Cell[][] _cellsArray;
	int _width;
	int _height;
	int _xOffset;
	int _yOffset;
	int _totalBoardWidth;
	int _totalBoardHeight;
	public Section(int height,int width){
		_cellsArray = new Cell[height][width];
		_width = width;
		_height = height;
		
	}

}
