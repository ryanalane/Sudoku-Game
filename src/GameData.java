import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class GameData {
	private boolean has_duplicates = false;
	static private String[] difficulty_levels = {"Beginner", "Intermediate", "Advanced"};
	static final private int[][][] solutions = {
		// Beginner Solutions
		{
			{3,6,1,4,7,9,8,2,5,8,5,9,6,2,3,4,1,7,4,7,2,8,1,5,3,6,9,5,9,3,1,4,7,6,8,2,6,2,4,3,5,8,9,7,1,1,8,7,9,6,2,5,3,4,7,3,5,2,9,6,1,4,8,2,1,8,5,3,4,7,9,6,9,4,6,7,8,1,2,5,3},
			{3,6,4,1,8,2,5,7,9,9,7,8,6,3,5,2,4,1,1,2,5,4,7,9,6,3,8,5,8,2,7,6,1,4,9,3,6,3,1,5,9,4,7,8,2,7,4,9,8,2,3,1,5,6,4,9,3,2,1,7,8,6,5,2,5,6,3,4,8,9,1,7,8,1,7,9,5,6,3,2,4},
			{6,3,4,9,1,5,7,2,8,2,7,8,6,4,3,5,1,9,5,9,1,2,7,8,6,4,3,4,5,7,3,6,9,2,8,1,9,8,6,4,2,1,3,5,7,3,1,2,8,5,7,4,9,6,1,2,5,7,8,6,9,3,4,8,6,3,5,9,4,1,7,2,7,4,9,1,3,2,8,6,5},
			{1,8,2,3,7,4,5,6,9,3,9,5,1,2,6,4,8,7,6,7,4,9,5,8,1,2,3,8,5,1,2,6,9,3,7,4,2,6,9,4,3,7,8,5,1,4,3,7,5,8,1,6,9,2,9,2,8,6,1,3,7,4,5,7,4,3,8,9,5,2,1,6,5,1,6,7,4,2,9,3,8},
			{5,3,4,6,7,8,9,1,2,6,7,2,1,9,5,3,4,8,1,9,8,3,4,2,5,6,7,8,5,9,7,6,1,4,2,3,4,2,6,8,5,3,7,9,1,7,1,3,9,2,4,8,5,6,9,6,1,5,3,7,2,8,4,2,8,7,4,1,9,6,3,5,3,4,5,2,8,6,1,7,9}
		},
		// Intermediate Solutions
		{
			{8,2,3,9,1,7,6,4,5,9,4,7,6,2,5,3,8,1,5,6,1,4,8,3,7,9,2,2,1,9,7,4,6,8,5,3,6,3,8,2,5,1,4,7,9,4,7,5,3,9,8,2,1,6,3,5,4,8,6,9,1,2,7,7,9,2,1,3,4,5,6,8,1,8,6,5,7,2,9,3,4},
			{8,6,1,7,4,3,9,5,2,4,2,7,1,5,9,6,3,8,3,9,5,6,8,2,7,1,4,7,4,8,9,3,5,2,6,1,9,1,3,2,7,6,8,4,5,2,5,6,4,1,8,3,7,9,5,8,4,3,2,7,1,9,6,6,3,2,5,9,1,4,8,7,1,7,9,8,6,4,5,2,3},
			{9,6,1,8,3,2,4,5,7,4,5,3,7,9,6,2,1,8,7,8,2,1,4,5,3,9,6,5,3,7,6,1,4,8,2,9,1,2,8,3,5,9,6,7,4,6,4,9,2,7,8,5,3,1,8,1,4,5,2,7,9,6,3,3,9,5,4,6,1,7,8,2,2,7,6,9,8,3,1,4,5},
			{5,1,4,6,2,9,8,3,7,9,8,7,1,5,3,4,2,6,6,3,2,4,8,7,9,5,1,1,7,5,8,3,4,2,6,9,8,6,4,9,7,2,5,1,4,4,2,9,5,6,1,7,8,3,7,9,8,2,1,6,3,4,5,2,4,6,3,9,5,1,7,8,3,5,1,7,4,8,6,9,2},
			{1,9,8,5,2,6,3,4,7,7,2,5,3,4,1,6,9,8,3,4,6,9,7,8,2,1,5,9,8,1,2,5,7,4,6,3,5,6,4,1,3,9,8,7,2,2,3,7,6,8,4,1,5,9,4,7,3,8,1,5,9,2,6,8,1,9,7,6,2,5,3,4,6,5,2,4,9,3,7,8,1}	
		},
		// Advanced Solutions
		{
			{5,9,8,7,3,6,2,4,1,3,2,7,1,4,8,5,6,9,1,4,6,5,9,2,3,8,7,7,6,5,9,8,3,1,2,4,4,8,1,2,6,7,9,5,3,9,3,2,4,5,1,8,7,6,8,1,4,3,7,5,6,9,2,2,5,9,6,1,4,7,3,8,6,7,3,8,2,9,4,1,5},
			{8,2,4,9,5,3,6,7,1,6,3,5,8,1,7,9,2,4,7,1,9,6,2,4,8,5,3,5,8,7,2,9,1,3,4,6,1,4,2,7,3,6,5,8,9,3,9,6,4,8,5,2,1,7,2,6,1,5,4,9,7,3,8,4,7,8,3,6,2,1,9,5,9,5,3,1,7,8,4,6,2},
			{2,3,1,8,4,7,6,5,9,7,5,4,1,6,9,3,2,8,8,9,6,3,5,2,7,4,1,4,1,5,2,7,3,9,8,6,9,2,3,4,8,6,1,7,5,6,7,8,9,1,5,4,3,2,5,4,7,6,2,1,8,9,3,1,8,9,5,3,4,2,6,7,3,6,2,7,9,8,5,1,4},
			{4,6,5,3,2,9,7,1,8,7,2,1,4,5,8,9,3,6,9,3,8,6,1,7,2,5,4,3,5,7,2,8,4,1,6,9,2,1,9,5,3,6,8,4,7,6,8,4,7,9,1,5,2,3,8,9,6,1,4,5,3,7,2,5,7,2,9,6,3,4,8,1,1,4,3,8,7,2,6,9,5},
			{5,6,8,9,3,4,1,2,7,7,2,1,8,6,5,2,9,4,2,9,4,2,7,1,6,5,8,6,1,5,3,9,3,8,7,2,2,4,3,7,8,6,9,1,5,9,8,7,1,5,2,4,3,6,8,7,6,5,1,9,2,4,3,4,3,9,6,2,7,5,8,1,1,5,2,3,4,8,7,6,9}	
		}
	};
	
	@SuppressWarnings("unchecked")
	static final private ArrayList<ArrayList<ArrayList<Integer>>> given_values = new ArrayList<ArrayList<ArrayList<Integer>>>(Arrays.asList(new ArrayList<ArrayList<Integer>>(Arrays.asList(
	// Beginner Given Values
	new ArrayList<Integer>(Arrays.asList(1,3,5,6,8,9,13,16,17,18,23,24,25,29,32,33,36,39,40,42,44,47,49,50,52,53,55,57,59,60,62,63,64,65,67,70,72,74,76,78,79,80)),
	new ArrayList<Integer>(Arrays.asList(1,6,8,13,15,17,18,21,22,23,25,26,29,32,34,36,37,40,42,45,46,48,50,53,56,57,59,60,61,64,65,67,70,74,76,81)),
	new ArrayList<Integer>(Arrays.asList(4,7,8,9,10,11,12,15,17,20,25,26,29,32,34,39,43,47,50,55,58,60,62,63,67,69,73,74,76,79,81)),
	new ArrayList<Integer>(Arrays.asList(1,3,6,9,12,13,17,21,23,25,27,28,30,31,40,42,51,52,54,55,57,59,61,65,66,69,73,76,79,81)),
	new ArrayList<Integer>(Arrays.asList(1,2,5,10,13,14,15,20,21,26,28,32,36,37,40,42,45,46,50,54,56,61,62,67,68,69,72,77,80,81))
	)), new ArrayList<ArrayList<Integer>>(Arrays.asList(
	// Intermediate Given Values
	new ArrayList<Integer>(Arrays.asList(4,6,10,14,18,20,21,25,26,28,29,35,36,39,43,46,47,53,54,56,57,59,61,62,64,68,72,76,78)),
	new ArrayList<Integer>(Arrays.asList(1,8,10,12,13,20,22,26,27,31,33,34,36,37,45,46,48,49,51,55,56,60,62,69,70,72,74,81)),
	new ArrayList<Integer>(Arrays.asList(4,7,8,13,16,19,22,24,27,28,29,36,37,39,43,45,46,53,54,55,58,60,63,66,69,74,75,78)),
	new ArrayList<Integer>(Arrays.asList(2,4,6,8,12,14,16,28,29,31,33,35,36,37,45,46,47,49,51,53,54,66,68,70,74,76,78,80)),
	new ArrayList<Integer>(Arrays.asList(2,5,6,8,12,13,18,23,25,30,32,36,38,42,44,46,50,51,52,57,59,64,69,70,74,76,80))
	)), new ArrayList<ArrayList<Integer>>(Arrays.asList(
	// Advanced Given Values
	new ArrayList<Integer>(Arrays.asList(1,4,5,8,13,18,21,24,26,29,32,35,36,46,47,50,53,56,58,61,64,69,74,77,78,81)),
	new ArrayList<Integer>(Arrays.asList(2,5,7,14,16,22,25,27,28,31,35,38,39,43,44,47,51,54,55,57,60,66,68,75,77,80)),
	new ArrayList<Integer>(Arrays.asList(5,6,7,9,11,18,19,23,24,25,28,29,30,37,41,52,54,58,60,62,65,69,70,73,80)),
	new ArrayList<Integer>(Arrays.asList(3,4,7,11,26,31,37,41,45,52,60,63,65,77,81)),
	new ArrayList<Integer>(Arrays.asList(1,16,29,41,53,66,81))
	))));
			
	private int difficulty_level_id = 0;
	private int generated_solution_id = 0;
	private int save_game_id = 0;
	
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
	public int getGeneratedSolutionId() {
		return generated_solution_id;
	}
	public void setGeneratedSolutionId(int new_generated_solution_id) {
		generated_solution_id = new_generated_solution_id;
	}
	public int[] getSolution() {
		return solutions[difficulty_level_id][generated_solution_id];
	}
	public void generateNewSolution() {
		int solution_id = (int)(Math.random() * 5);
		setGeneratedSolutionId(solution_id);
	}
	public void generateNewSolution(int desired_difficulty_level_id) {
		setDifficultyLevelId(desired_difficulty_level_id);
		generateNewSolution();
	}
	public ArrayList<Integer> getGivenValues() {
		return given_values.get(getDifficultyLevelId()).get(getGeneratedSolutionId());
	}
	public String getDifficultyLevel() {
		return difficulty_levels[getDifficultyLevelId()];
	}
	
	public int getSaveGameId () {
		return save_game_id;
	}
	public void setSaveGameId (int new_save_game_id) {
		save_game_id = new_save_game_id;
	}
	
	public void saveGame(int[][] current_square_array) throws IOException {
		// Method for saving the game
		int answer = JOptionPane.showConfirmDialog(null, "Do you want to save this file?"); 
		if (answer==0)
		{
		if (getSaveGameId() == 0) {
			File saved_game_dir = new File("saved_games/");
			FilenameFilter file_filter = new FilenameFilter() {
				public boolean accept(File dir, String name) {
					String lowercaseName = name.toLowerCase();
					if (lowercaseName.endsWith(".txt")) {
						return true;
					} else {
						return false;
					}
				}
			};
			setSaveGameId(saved_game_dir.listFiles(file_filter).length+1);
		}
		FileWriter new_saved_game = new FileWriter("saved_games/game_"+ Integer.toString(getSaveGameId()) + ".txt");
		// Write the save_game_id
		new_saved_game.write(Integer.toString(getSaveGameId()));
		new_saved_game.write(" ");
		// Write the difficultly level
		new_saved_game.write(Integer.toString(getDifficultyLevelId()));
		new_saved_game.write(" ");
		// Write the solution id
		new_saved_game.write(Integer.toString(getGeneratedSolutionId()));
		new_saved_game.write(" ");
		// Write the current square data
		for (int i = 0; i < 81; i++) {
			int row_id = SquareData.getRowId(i);
            int col_id = SquareData.getColId(i);
            new_saved_game.write(Integer.toString(current_square_array[row_id][col_id]));
            new_saved_game.write(" ");
		}
		new_saved_game.close();
	}
	}
	public int[][] loadGame() {
		// Method for loading the game
		int[][] current_square_array = new int[9][9];
		
		JFileChooser jfc = new JFileChooser();
		
			try
			{
				File f = new File(new File(".").getCanonicalPath() + "/saved_games/");
				jfc.setCurrentDirectory(f);
				
				jfc.setFileFilter(new TextFileFilter());
				jfc.showOpenDialog(null);
				Scanner saved_game = new Scanner(jfc.getSelectedFile());
				setSaveGameId(saved_game.nextInt());
				setDifficultyLevelId(saved_game.nextInt());
				setGeneratedSolutionId(saved_game.nextInt());
				for (int i = 0; i < 81; i++) {
					int row_id = SquareData.getRowId(i);
					int col_id = SquareData.getColId(i);
					current_square_array[row_id][col_id] = saved_game.nextInt();
				}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException except)
		{
			System.out.println("Unable to set current directory!");
		}
		return current_square_array;
	}
	GameData() {
		generateNewSolution(0);
	}
	GameData(int new_difficulty_level_id) {
		generateNewSolution(new_difficulty_level_id);
	}
	private class TextFileFilter extends javax.swing.filechooser.FileFilter
  	{
  		public boolean accept(File f)
  		{
  			return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
  			
  		}
  		public String getDescription()
  		{
  			return "Text Files(*.txt)";
  			
  		}
  	}
}
