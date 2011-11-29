import java.util.ArrayList;

public class SquareData {
	private int id;
	private ArrayList<Integer> duplicate_ids;
	private int value = 0;
	private boolean given = false;
	
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
	SquareData() {
		setId(-1);
		duplicate_ids = new ArrayList<Integer>();
	}
	SquareData(int new_id) {
		this();
		setId(new_id);
	}
}
