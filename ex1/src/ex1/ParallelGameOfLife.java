package ex1;

public class ParallelGameOfLife implements GameOfLife {

	public boolean[][] invoke(boolean[][] initalField, int hSplit, int vSplit,
			int generations) {
		// init the field -> copy the field to the local array
				boolean[][] input=new boolean[initalField.length][];
		
		ParallelBoard pb = new ParallelBoard(initalField.length, initalField[0].length, 
				hSplit, vSplit, initalField);
		
		return null;
	}

}
