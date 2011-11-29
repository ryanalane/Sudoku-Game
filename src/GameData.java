import java.util.ArrayList;

public class GameData {
	private boolean has_duplicates = false;
	static private String[] difficulty_levels = {"Beginner", "Intermediate", "Advanced"};
	private int difficulty_level_id = 0;
	private int[] generated_solution;
	private ArrayList<Integer> given_squares;
	private SquareData[] squares;
	
	// GameData Methods
	public boolean getHasDuplicates() {
		return has_duplicates;
	}
	public void setHasDuplicates(boolean new_has_duplicates) {
		has_duplicates = new_has_duplicates;
	}
	public int getDifficultyLevelId() {
		return difficulty_level_id;
	}
	public void setDifficultyLevelId(int new_difficulty_level_id) {
		difficulty_level_id = new_difficulty_level_id;
	}
	public int[] getGeneratedSolution() {
		return generated_solution;
	}
	public void setGeneratedSolution(int[] new_generated_solution) {
		generated_solution = new_generated_solution;
	}
	public ArrayList<Integer> getGivenSquares() {
		return given_squares;
	}
	public void setGivenSquares(ArrayList<Integer> new_given_squares) {
		given_squares = new_given_squares;
	}
	public void addGivenSquare(int new_given_value) {
		given_squares.add(new_given_value);
	}
	public void resetGivenSquares() {
		given_squares.clear();
	}
	public String getDifficultyLevel() {
		return difficulty_levels[getDifficultyLevelId()];
	}
	public void generateSolution(int desired_difficulty_level_id) {
		
	}
	GameData() {
		given_squares = new ArrayList<Integer>();
		for(int i = 0; i < 81; ++i)
			squares[i] = new SquareData(i);
	}
	GameData(int new_difficulty_level_id) {
		setDifficultyLevelId(new_difficulty_level_id);
		generateSolution(getDifficultyLevelId());
	}
}
