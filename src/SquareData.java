public final class SquareData {
	static private int[][] sector_values = new int[81][3];
	static {
		for (int i = 0; i < sector_values.length; ++i) {
			int cell_number = i+1;
			boolean last_column = (cell_number%9 == 0);
			int row_id = sector_values[i][0] = last_column ? cell_number/9-1 : cell_number/9;
			int col_id = sector_values[i][1] = last_column ? 8 : cell_number%9-1;
			sector_values[i][2] = col_id/3 + (row_id/3)*3; // Box_id Value
		}
	}
	
	static public int getRowId(int cell_id) {
		return sector_values[cell_id][0];
	}
	static public int getColId(int cell_id) {
		return sector_values[cell_id][1];
	}
	static public int getBoxId(int cell_id) {
		return sector_values[cell_id][2];
	}
}
