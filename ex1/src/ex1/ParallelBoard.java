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

	public static int _hSections;
	public static int _vSections;
	public static int _boardWidth;
	public static int _boardHeight;
	public static int _generations;

	public static int getBoardWidth() {
		return _boardWidth;
	}

	ParallelBoard(int boardHeight, int boardWidth, int hSections,
			int vSections, boolean initalBoard[][], int generations) {

		_sectionsArray = new Section[hSections][vSections];
		_boardHeight = boardHeight;
		_boardWidth = boardWidth;
		_hSections = hSections;
		_vSections = vSections;
		_generations = generations;

		int normalSecHgtSize = _boardHeight / _hSections;
		int normalSecWdtSize = _boardWidth / _vSections;
		int lastSecHgtExtra = _boardHeight % _hSections;
		int lastSecWdtExtra = _boardWidth % _vSections;

		initSections(initalBoard, normalSecHgtSize, normalSecWdtSize,
				lastSecHgtExtra, lastSecWdtExtra);

		initSectionNeighbors();

	}

	/**
	 * @param initalBoard
	 * @param normalSecHgtSize
	 * @param normalSecWdtSize
	 * @param lastSecHgtExtra
	 * @param lastSecWdtExtra
	 */
	private void initSections(boolean[][] initalBoard, int normalSecHgtSize,
			int normalSecWdtSize, int lastSecHgtExtra, int lastSecWdtExtra) {
		
		for (int i = 0; i < _hSections; i++) {
			for (int j = 0; j < _vSections; j++) {
				_sectionsArray[j][i] = new Section(
						normalSecHgtSize
								+ ((i == _hSections - 1) ? lastSecHgtExtra : 0),
						normalSecWdtSize
								+ ((i == _vSections - 1) ? lastSecWdtExtra : 0),
						i * normalSecWdtSize, j * normalSecHgtSize,
						_boardHeight, _boardWidth, initalBoard);
			}
		}
		
	}

	/**
	 * 
	 */
	private void initSectionNeighbors() {
		
		for (int i = 0; i < _hSections; i++) {
			for (int j = 0; j < _vSections; j++) {

				if (j > 0 && i > 0) {
					_sectionsArray[j][i].setNeighbor(Directions.NORTH_WEST,
							_sectionsArray[j - 1][i - 1]);
				}

				if (j > 0) {
					_sectionsArray[j][i].setNeighbor(Directions.NORTH,
							_sectionsArray[j - 1][i]);
				}

				if (j > 0 && i < _hSections - 1) {
					_sectionsArray[j][i].setNeighbor(Directions.NORTH_EAST,
							_sectionsArray[j - 1][i + 1]);
				}

				if (i > 0) {
					_sectionsArray[j][i].setNeighbor(Directions.WEST,
							_sectionsArray[j][i - 1]);
				}

				if (i < _hSections - 1) {
					_sectionsArray[j][i].setNeighbor(Directions.EAST,
							_sectionsArray[j][i + 1]);
				}

				if (j < _vSections - 1 && i < _hSections - 1) {
					_sectionsArray[j][i].setNeighbor(Directions.SOUTH_EAST,
							_sectionsArray[j + 1][i + 1]);
				}

				if (j < _vSections - 1) {
					_sectionsArray[j][i].setNeighbor(Directions.SOUTH,
							_sectionsArray[j + 1][i]);
				}

				if (j < _vSections - 1 && i > 0) {
					_sectionsArray[j][i].setNeighbor(Directions.SOUTH_WEST,
							_sectionsArray[j + 1][i - 1]);
				}

			}
		}
	}

	/**
	 * TODO: Add a new class which inherits Threads Each such thread will be
	 * constructed with a ptr to a Section And will do "section.solve();"
	 */

	/**
	 * 
	 * @param sourceBoard
	 * @param targetBoard
	 * @return
	 */

	public boolean sectionCopy(CellImpl sourceBoard, CellImpl targetBoard[][]) {
		return true;
	}
}
