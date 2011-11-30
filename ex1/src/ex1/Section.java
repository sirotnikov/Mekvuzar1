/**
 * 
 */
package ex1;

/**
 * @author Dima
 *
 */
public class Section {
	CellImpl[][] _cellsArray;
	int _width;
	int _height;
	int _xOffset;
	int _yOffset;
	int _totalBoardWidth;
	int _totalBoardHeight;
	public Section(int height,int width){
		_cellsArray = new CellImpl[height][width];
		_width = width;
		_height = height;
		
	}
	public int getWidth(){
		return _width;
	}
	public int getHeight(){
		return _height;
	}
	public int getXOffset(){
		return _xOffset;
	}
	public int getYOffset(){
		return _yOffset;
	}
	public int getTotalBoardWidth(){
		return _totalBoardWidth;
	}
	public int getTotalBoardHeight(){
		return _totalBoardHeight;
	}
	public void setWidth(int newWidth){
		_width = newWidth;
	}
	public void setHeight(int newHeight){
		_height = newHeight;
	}
	public void setXOffset(int newXOffset){
		_xOffset = newXOffset;
	}
	public void setYOffset(int newYoffset){
		_yOffset = newYoffset;
	}
	public void setTotalBoardWidth(int newTotalBoardWidth){
		_totalBoardWidth = newTotalBoardWidth;
	}
	public void setTotalBoardHeight(int newTotalBoardHeight){
		 _totalBoardHeight = newTotalBoardHeight;
	}

}
