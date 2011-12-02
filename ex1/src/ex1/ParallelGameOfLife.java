package ex1;

public class ParallelGameOfLife implements GameOfLife {

	public boolean[][] invoke(boolean[][] initalField, int hSplit, int vSplit,
			int generations) {
		// init the field -> copy the field to the local array
		
		ParallelBoard pb = new ParallelBoard(initalField.length, initalField[0].length, 
				hSplit, vSplit, initalField, generations);
		
		
		pb.Solve();
		
		boolean[][] result = null;
		try {
			result = pb.getResults();
		} catch (Exception e) {
			System.out.print("Exception: " + e);
		}
		return result;
	}

}
