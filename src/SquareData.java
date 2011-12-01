import java.util.ArrayList;

public class SquareData {
	private int id = -1;
	private ArrayList<Integer> duplicate_ids;
	private int value = 0;
	private boolean given = false;
	
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
	
	// SquareData Methods
	public int getId() {
		return id;
	}
	public void setId(int new_id) {
		id = new_id;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int new_value) {
		value = new_value;
	}
	public void updateValue(int new_user_value) {
		if(new_user_value >= 1 && new_user_value <= 9)
			setValue(new_user_value);
	}
	public ArrayList<Integer> getDuplicateIds() {
		return duplicate_ids;
	}
	public void resetDuplicateIds() {
		duplicate_ids.clear();
	}
	public void addDuplicateId(int new_dup_id) {
		duplicate_ids.add(new_dup_id);
	}
	public void removeDuplicateId(int id_to_remove) {
		duplicate_ids.remove(id_to_remove);
	}
	public boolean checkForDuplicates() {
		boolean are_there_duplicates = false;
		return are_there_duplicates;
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
	
	SquareData() {
		duplicate_ids = new ArrayList<Integer>();
	}
	SquareData(int new_id) {
		this();
		setId(new_id);
	}
}
