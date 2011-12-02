/**
 * 
 */
package ex1;

/**
 * @author Dima
 *
 */public enum Directions {
	NORTH_WEST {
		public int newX(int x) { return x - 1;}
		public int newY(int y) { return y - 1;}
	},
	NORTH {
		public int newY(int y) {return y - 1;}
		public int newX(int x) {return x;}
	}, 
	NORTH_EAST {
		public int newY(int y) {return y - 1;}
		public int newX(int x) {return x + 1;}
		
	}, EAST {
		public int newY(int y) {return y;}
		public int newX(int x) {return x + 1;}
	}, SOUTH_EAST {
		public int newY(int y) {return y + 1;}
		public int newX(int x) {return x + 1;}
	}, SOUTH {
		public int newX(int x) {return x;}
		public int newY(int y) {return y + 1;}
	}, SOUTH_WEST {
		public int newY(int y) {return y + 1;}	
		public int newX(int x) {return x - 1;}
	}, WEST {
		public int newX(int x) {return x - 1;}
		public int newY(int y) {return y;}
	};
	
	public int newX(int x) { System.out.println("======== NEW_X_ DEFAULT ========"); return x;}
	public int newY(int y) { System.out.println("======== NEW_Y_ DEFAULT ========"); return y;}
}
