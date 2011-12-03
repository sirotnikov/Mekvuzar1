/**
 * 
 */
package ex1;

/**
 * @author Dima
 * 
 */
public class ParallelBoard {
	Section[][] _sectionsArray; // Should eventually be
								// Section[_vSections][_hSections];

	int _hSections;
	int _vSections;
	int _boardWidth;
	int _boardHeight;
	int _generations;

	ParallelBoard(int boardHeight, int boardWidth, int hSections,
			int vSections, boolean initalBoard[][], int generations) {
		
		_boardHeight = boardHeight;
		_boardWidth = boardWidth;
		_hSections = hSections;
		_vSections = vSections;
		_generations = generations;
		Point._rowLength = _boardWidth;

		_sectionsArray = new Section[_vSections][_hSections];
		
		//System.out.println("Board: [" + _hSections + "," + _vSections + "]");
		initSections(initalBoard);
		initSectionNeighbors();
	}

	/**
	 * 
	 * @param initalBoard
	 * @param generations
	 */
	private void initSections(boolean[][] initalBoard) {
		
		int normalSecHgtSize = _boardHeight / _vSections;
		int normalSecWdtSize = _boardWidth / _hSections;
		int lastSecHgtExtra = _boardHeight % _vSections;
		int lastSecWdtExtra = _boardWidth % _hSections;
		
		
//		System.out.println("initSections: normal: (" 
//				+ normalSecWdtSize + "x" + normalSecHgtSize + ")" );
		for (int j = 0; j < _vSections; j++) {
			for (int i = 0; i < _hSections; i++) {
				
				int thisWidth = normalSecWdtSize
						+ ((i == _hSections - 1) ? lastSecWdtExtra : 0);
				int thisHeight = normalSecHgtSize
						+ ((j == _vSections - 1) ? lastSecHgtExtra : 0);
				int xOffset = i * normalSecWdtSize;
				int yOffset = j * normalSecHgtSize;
				
				_sectionsArray[j][i] = new Section(
						thisHeight,	thisWidth, xOffset, yOffset,
						_boardHeight, _boardWidth, initalBoard, _generations);
			}
			//System.out.println();
		}
		//System.out.println("All sections done");
	}

	/**
	 * 
	 */
	private void initSectionNeighbors() {
		
		for (int i = 0; i < _hSections; i++) {
			for (int j = 0; j < _vSections; j++) {
				initNeighborsForSect(i, j);
			}
		}
	}

	/**
	 * @param i
	 * @param j
	 */
	private void initNeighborsForSect(int i, int j) {
		if (j > 0 && i > 0) {
			setNeighbor(i, j, Directions.NORTH_WEST);
		}

		if (j > 0) {
			setNeighbor(i, j, Directions.NORTH);
		}

		if (j > 0 && (i < (_hSections - 1))) {
			setNeighbor(i, j, Directions.NORTH_EAST);
		}

		if (i > 0) {
			setNeighbor(i, j, Directions.WEST);
		}

		if (i < (_hSections - 1)) {
			setNeighbor(i, j, Directions.EAST);
		}

		if ((j < (_vSections - 1)) && (i < (_hSections - 1))) {
			setNeighbor(i, j, Directions.SOUTH_EAST);
		}

		if (j < _vSections - 1) {
			setNeighbor(i, j, Directions.SOUTH);
		}

		if ((j < _vSections - 1) && i > 0) {
			setNeighbor(i, j, Directions.SOUTH_WEST);
		}
	}

	/**
	 * @param i
	 * @param j
	 * @param dir
	 */
	private void setNeighbor(int i, int j, Directions dir) {
		_sectionsArray[j][i].setNeighbor(dir,
				_sectionsArray[dir.newY(j)][dir.newX(i)]);
	}

	
	/**
	 * @throws Exception 
	 */
	public boolean[][] getResults() throws Exception{
		boolean[][] result = new boolean[_boardHeight][];
		for (int j = 0; j < _boardHeight; j++){
			result[j] = new boolean[_boardWidth];
		}
		
		for(int j = 0; j < _vSections; j++){
			for(int i = 0; i < _hSections; i++){
				_sectionsArray[j][i].updateResultBoard(result);
			}
		}	
		return result;
	}

	/**
	 * 
	 */
	public void Solve() {
		SectionThread[][] threads = new SectionThread[_vSections][];
		
		//Run threads and start them
		for(int j = 0; j < _vSections; j++){
			threads[j] = new SectionThread[_hSections];
			for (int i = 0; i < _hSections; i++){
				Section s = _sectionsArray[j][i];
				threads[j][i] = new SectionThread(s);
				threads[j][i].start();
			}
		}
		
		//Join threads
		for(int j = 0; j < _vSections; j++){		
			for (int i = 0; i < _hSections; i++){
				try {
					threads[j][i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
		//Done.
		
	}
}
