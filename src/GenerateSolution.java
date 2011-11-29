import java.util.ArrayList;
import java.util.Collections;


public class GenerateSolution {
	public static void main(String[] args) {
		int[][] sector_values = new int[81][3];
		// Fills sector_values array with values, where sector_values[i][0-2] correspond to a row_id, column_id, and a box_id for each cell_id i, respectively
		for (int i = 0; i < sector_values.length; ++i) {
			int cell_number = i+1;
			boolean last_column = (cell_number%9 == 0);
			int row_id = sector_values[i][0] = last_column ? cell_number/9-1 : cell_number/9;
			int col_id = sector_values[i][1] = last_column ? 8 : cell_number%9-1;
			sector_values[i][2] = col_id/3 + (row_id/3)*3; // Box_id Value
		}
		
		
		// Generate Solution (single attempt)
		int[] sudoku_solution = new int[81];
		
		ArrayList<ArrayList<Integer>> row_values_taken = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> col_values_taken = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> box_values_taken = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 9; ++i) {
			row_values_taken.add(new ArrayList<Integer>());
			col_values_taken.add(new ArrayList<Integer>());
			box_values_taken.add(new ArrayList<Integer>());
		}
		
		ArrayList<Integer> mainList = new ArrayList<Integer>();
		mainList.add(1);
		mainList.add(2);
		mainList.add(3);
		mainList.add(4);
		mainList.add(5);
		mainList.add(6);
		mainList.add(7);
		mainList.add(8);
		mainList.add(9);
		
		boolean running_fine = true;
		int iteration_count = 0;
		while (sudoku_solution[80] == 0 && running_fine && iteration_count < 5) {
			for (int i = 0; i < 81; ++i) {
				int row_id = sector_values[i][0];
				int col_id = sector_values[i][1];
				int box_id = sector_values[i][2];
				ArrayList<Integer> choiceList = new ArrayList<Integer>(mainList);
				Collections.shuffle(choiceList);
				int row_taken = -1;
				int col_taken = -1;
				int box_taken = -1; 
				int caught_value = -1;
				for (int j = 0; j < choiceList.size(); ++j) {
					int test_value = choiceList.get(j);
					if (!row_values_taken.get(row_id).contains(test_value)
							&& !col_values_taken.get(col_id).contains(
									test_value)
							&& !box_values_taken.get(box_id).contains(
									test_value)) {
						sudoku_solution[i] = test_value;
						row_values_taken.get(row_id).add(test_value);
						col_values_taken.get(col_id).add(test_value);
						box_values_taken.get(box_id).add(test_value);
						break;
					} else if (row_values_taken.get(row_id).contains(test_value)) { // These else blocks serve to indicate where the offending value is located
						row_taken = row_id;
						caught_value = test_value;
					} else if (col_values_taken.get(col_id).contains(test_value)) { // These else blocks serve to indicate where the offending value is located
						col_taken = col_id;
						caught_value = test_value;
					} else if (box_values_taken.get(box_id).contains(test_value)) { // These else blocks serve to indicate where the offending value is located
						box_taken = col_id;
						caught_value = test_value;
					}
				}
				if (sudoku_solution[i] == 0) {
					System.out.println("Generator failed at: " + Integer.toString(i+1));
					System.out.println("Current col_id: " + Integer.toString(col_id));
					System.out.print("Current row: ");
					for (int x = col_id; x >0; --x)
						System.out.print(Integer.toString(sudoku_solution[i-x]) + ", ");
					System.out.print("{" + Integer.toString(caught_value) + "}\n");
					System.out.println("Offending Row: " + Integer.toString(row_taken));
					System.out.println("Offending Column: " + Integer.toString(col_taken));
					System.out.println("Offending Box: " + Integer.toString(box_taken));
					System.out.print("\n");
					
					break;
				}
			}
			++iteration_count;
		}
		
		// Print Sudoku Solution
		/*
		for (int i = 0; i < sudoku_solution.length; ++i) {
			System.out.println(Integer.toString(i) + ": " + Integer.toString(sudoku_solution[i]));
		}
		*/
		
		
	}
}
