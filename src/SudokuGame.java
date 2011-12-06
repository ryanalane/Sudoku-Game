//Packages that need to be imported into the program
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

import javax.swing.JSeparator;
import javax.swing.text.*;
import java.util.Scanner;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class SudokuGame extends JFrame {
	private GameData current_game = new GameData(0);
	
	//Creating class level variables (properties) for use by all methods in this class
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu();
    private JMenuItem menuNewGame = new JMenuItem();
    private JMenuItem menuLoadGame = new JMenuItem();
    private JMenuItem menuSaveGame = new JMenuItem();
    private JMenuItem menuFileExit = new JMenuItem();
    private JMenu menuHelp = new JMenu();
    private JMenuItem menuHelpAbout = new JMenuItem();
    private JMenuItem menuHelpContents = new JMenuItem();
    private JButton jButtonUndo = new JButton();
    private JButton jButtonReset = new JButton();
    private JButton jButtonCheckSolution = new JButton();
    private JButton jButtonShowSolution = new JButton();
    private JPanel jPanel1 = new JPanel();
    public static SkillLevelSelection skillLevelSelection;
    public static JFrame aboutFrame;
    private static boolean aboutExists = false;
    //Icon for main class
    URL imgURL = getClass().getResource("icons/sudoku.gif");

    // Add array of buttons to class
    private JButton[] btnarray = new JButton[81];
    
    //ButtonListener and KeyListener instances:
    private ArrayButtonListener arrayButtonListener = new ArrayButtonListener();
    private MyKeyListener myKeyListener = new MyKeyListener();
    private ButtonListener buttonListener = new ButtonListener();
    
    //Constants
    private final int NUM_IN_A_ROW = 9;
    private final int NUM_IN_A_COLUMN = 9;
    private final int NUM_OF_BUTTONS = 81;
    
    //ints and Strings used for accessing information about buttons in array
    private int rowNumberAccessed;
    private int colNumberAccessed;
    private String numberUserEntered;
    private int buttonNumberPushed;
    
    //Creating various arrays to be used throughout the main class
    
    int[][] currentArrayOfNumbers = new int[NUM_IN_A_ROW][NUM_IN_A_COLUMN];
    
     // int[][] solutionIntArray = new int[rowNumberAccessed][colNumberAccessed]; -- when randomPuzzle solutions are added
    
    //    int[][] solutionIntArray = { { 3, 9, 8, 4, 1, 5, 2, 7, 6 }, { 1, 2, 4, 3, 6, 7, 5, 8, 9 },
   //   { 5, 6, 7, 2, 8, 9, 1, 3, 4 }, { 7, 1, 2, 5, 3, 4, 6, 9, 8 },
   //   { 6, 3, 5, 1, 9, 8, 4, 2, 7 }, { 8, 4, 9, 6, 7, 2, 3, 1, 5 },
   //   { 4, 7, 1, 8, 2, 6, 9, 5, 3 }, { 2, 8, 6, 9, 5, 3, 7, 4, 1 },
   //   { 9, 5, 3, 7, 4, 1, 8, 6, 2 } }; // - temporary, to be deleted
	
    //Arraylist to be used for values that are omitted in puzzles
    ArrayList<Integer> skippedValues = new ArrayList<Integer>();
    
    //Boolean used to control whether the solution is shown or not
    boolean solutionShowed = false;
    
    GameData gameData = new GameData();
    
    // ArrayList for holding a list of data records:
    private ArrayList<SavedDataRecord> savedDataObjectsList = new ArrayList<SavedDataRecord>();
    private boolean lastButtonPushedWasSave = false;

    public SudokuGame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setJMenuBar(menuBar);
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(591, 444));
        this.setTitle("Sudoku Game");
        menuFile.setText("File");
        menuNewGame.setText("New Game");
        menuNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                newGame_ActionPerformed(ae);
            }
        });
        menuLoadGame.setText("Load Game");
        menuLoadGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                loadGame_ActionPerformed(ae);
            }
        });
        menuSaveGame.setText("Save Game");
        menuSaveGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                saveGame_ActionPerformed(ae);
            }
        });
        menuFileExit.setText("Exit");
        menuFileExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                fileExit_ActionPerformed(ae);
            }
        });
        menuHelp.setText("Help");
        menuHelpAbout.setText("About");
        menuHelpAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                helpAbout_ActionPerformed(ae);
            }
        });
        menuHelpContents.setText("Help Contents");
        menuHelpContents.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                helpContents_ActionPerformed(ae);
            }
        });


        //Modify for other menus -- menuHelpAbout.addActionListener( new ActionListener() { public void actionPerformed( ActionEvent ae ) { helpAbout_ActionPerformed( ae ); } } );

        jButtonUndo.setText("Undo");
        jButtonUndo.setBounds(new Rectangle(175, 330, 75, 21));
        jButtonUndo.addActionListener(buttonListener);
        jButtonUndo.setBackground(new Color(0, 214, 214));
        jButtonReset.setText("Reset");
        jButtonReset.setBounds(new Rectangle(315, 330, 75, 21));
        jButtonReset.setBackground(new Color(0, 214, 214));
        jButtonReset.addActionListener(buttonListener);
        jButtonCheckSolution.setText("Check Solution");
        jButtonCheckSolution.setBounds(new Rectangle(85, 360, 165, 20));
        jButtonCheckSolution.setBackground(new Color(0, 214, 214));
        jButtonCheckSolution.addActionListener(buttonListener);
        jButtonShowSolution.setText("Show Solution");
        jButtonShowSolution.setBounds(new Rectangle(315, 360, 170, 20));
        jButtonShowSolution.addActionListener(buttonListener);

        jButtonShowSolution.setBackground(new Color(0, 214, 214));
        jPanel1.setBounds(new Rectangle(85, 15, 425, 300));
        menuFile.add(menuNewGame);
        menuFile.add(menuLoadGame);
        menuFile.add(menuSaveGame);
        menuFile.add(menuFileExit);
        menuBar.add(menuFile);
        menuHelp.add(menuHelpAbout);
        menuHelp.add(menuHelpContents);
        menuBar.add(menuHelp);


        // Setting a grid layout to the JPanel )Layout manager has to be defined)
        this.getContentPane().add(jButtonShowSolution, null);
        this.getContentPane().add(jButtonCheckSolution, null);
        this.getContentPane().add(jButtonReset, null);
        this.getContentPane().add(jButtonUndo, null);
        this.getContentPane().add(jPanel1, null);
        this.getContentPane().add(jButtonShowSolution, null);
        this.getContentPane().add(jButtonCheckSolution, null);
        jPanel1.setLayout(new GridLayout(9, 9));

        jPanel1.setBackground(Color.white);
        this.setIconImage(new ImageIcon(imgURL).getImage());

        for (int i = 0; i < 81; i++) {
            btnarray[i] = new JButton();
            btnarray[i].addActionListener(arrayButtonListener);
            btnarray[i].addKeyListener(myKeyListener);
            jPanel1.add(btnarray[i]);
            Font font = new Font("Arial", Font.BOLD, 16);
            btnarray[i].setFont(font);
        
            
            
        }

        
    }

    public static void unsetAboutExists() {
        aboutExists = false;
    }

    public int getRowNumberAccessed() {
        return rowNumberAccessed;
    }

    public int getColNumberAccessed() {
        return colNumberAccessed;
    }

    public static void main(String[] args) {
        SudokuGame sudokuGame = new SudokuGame();
        sudokuGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sudokuGame.getContentPane().setBackground(new Color(0x00, 0xFF, 0x00));
        sudokuGame.setLocationRelativeTo(null);
        sudokuGame.setResizable(false);
        sudokuGame.setVisible(true);
    }
    void newGame_ActionPerformed(ActionEvent e) {
        
        
        skillLevelSelection = new SkillLevelSelection(this);
        skillLevelSelection.setResizable(false);
        skillLevelSelection.setAlwaysOnTop(true);
        skillLevelSelection.setLocationRelativeTo(null);
        skillLevelSelection.setVisible(true); 
        

        System.out.println("New game clicked");

    }

    void loadGame_ActionPerformed(ActionEvent e) {
        System.out.println("Load game clicked");
    }

    void saveGame_ActionPerformed(ActionEvent e) {
        System.out.println("Save game clicked");
    }

    void fileExit_ActionPerformed(ActionEvent e) {     
        System.exit(0);
    }

    private void helpAbout_ActionPerformed(ActionEvent e) {
        System.out.println("About clicked");
        if (aboutExists == false) {
            aboutExists = true;
            System.out.println("About Opened");
            About.createAndShowGUI();
        } else {
            aboutFrame.toFront();
        }
    }


    void helpContents_ActionPerformed(ActionEvent e) {
        //Open pdf file containing Help in the designated folder
      try {
                         
                          if ((System.getProperty("os.name").contains("Win")))
                              {
                                  Process p = Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler SudokuGameHelp.pdf");
                                  p.waitFor();
                              }
                          else {  // it is a "civilized OS not requiring running a library as an application runner, civilized such as Linux or Mac, only Java code needed
                             
                                  try {
                                          Desktop desktop = null;
                                              if (Desktop.isDesktopSupported()) {
                                              desktop = Desktop.getDesktop();
                                              desktop.open(new File("SudokuGameHelp.pdf"));
                                      }
                                         
                                           } catch (IOException ioe) {
                                              ioe.printStackTrace();
                                      }

                                  }

                          }
                            
                       catch (Exception ex) {
                          //Shows where error occurred in Windows
                          ex.printStackTrace();
                      }


        System.out.println("Help Contents clicked");
    }

    public void setLevelSelected(int level) {
        current_game.setDifficultyLevelId(level);
    }


    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            {
                if (e.getActionCommand().equals("Undo")) {
                    try{
                        
                        int lastSavedNumber =  savedDataObjectsList.get(savedDataObjectsList.size()-1).getIntegerData();
                        int lastSavedX = savedDataObjectsList.get(savedDataObjectsList.size()-1).getXCoordinateOfButton();    
                        int lastSavedY = savedDataObjectsList.get(savedDataObjectsList.size()-1).getYCoordinateOfButton();
                        int lastSavedButtonNumber = savedDataObjectsList.get(savedDataObjectsList.size()-1).getButtonNumber();
                    
                    System.out.println("lastSavedNumber = " + lastSavedNumber);
                    System.out.println("lastSavedX = " + lastSavedX);
                    System.out.println("lastSavedY = " + lastSavedY);
                    System.out.println("lastSavedButtonNumber = " + lastSavedButtonNumber);
                    
                    System.out.println("Last saved Data Acquired");    
                        
                    //update the GUI with the retrieved data, unless zero, if zero update GUI with "":
                        if(lastSavedNumber != 0)
                        btnarray[buttonNumberPushed].setText(String.valueOf(lastSavedNumber));
                        else
                        btnarray[buttonNumberPushed].setText("");
                        
                    // Update the current array with retrieved number:
                    currentArrayOfNumbers[lastSavedX][lastSavedY] =  lastSavedNumber;   
                        
                        
                     //   savedDataObjectsList.remove(savedDataObjectsList.size()-1);   // remove the last record from list
                            // restore later !!!
                        
                    // Undo operation problem here!!!!
                        
                        // for now, remove ALL elements from arraylist, not just last one.
                        // For the demo it will be SINGLE ELEMENT UNDO:

                        savedDataObjectsList.clear();  // Remove all elements from arraylist after first one
                        // Temporary, for teacher demo.

                    }
                    catch (Exception exe){
                        
                        System.out.println("The arraylist has no more elements");
                        JOptionPane.showMessageDialog(null, "You can only undo one piece of data, for now." , "Unable to Load Data" , JOptionPane.WARNING_MESSAGE);
                        // JOptionPane.showMessageDialog(null, "You have reached your first entry." , "Unable to Load Data" , JOptionPane.WARNING_MESSAGE);
                    }
                    
                    
                    
                    System.out.println("Undo button clicked");
                    
                } else if (e.getActionCommand().equals("Reset"))
                {
                    for (int i = 0; i < currentArrayOfNumbers.length; i++) {
                        for (int j = 0; j < currentArrayOfNumbers[i].length; j++) {
                    for (int n = 0; n < 81; n++) {
                            btnarray[n].setText("");
                            btnarray[n].setForeground(new Color(0x00, 0x00, 0x00));  // RESET COLOR
                            currentArrayOfNumbers[i][j]=0;
                            
                    }
                        }
                    }
                System.out.println("Reset button clicked");
                }
                    
                
                    else if (e.getActionCommand().equals("Check Solution")) {
                    
                    if (solutionShowed == true) {
                               JOptionPane.showMessageDialog(null,
                                                             "You are currently looking at the solution.",
                                                             "Solution Information",
                                                             JOptionPane.INFORMATION_MESSAGE); 
                               return;
                               
                           }
                    checkSolution();
                    System.out.println("Check solution clicked");
                    
                } else if (e.getActionCommand().equals("Show Solution")) {
                    //calls showSolution method
                    solutionShowed = true;
                    showSolution();
                    System.out.println("Show solution clicked");
                    jButtonShowSolution.setText("Hide Solution");
                    
                    
                    
                }
                else if (e.getActionCommand().equals("Hide Solution")) {
                        //set buttons back to currentArray
                    int currentButtonNumber = 0;
                    
                    solutionShowed = false;
        for (int i = 0; i < currentArrayOfNumbers.length; i++) {
            for (int j = 0; j < currentArrayOfNumbers[i].length; j++) {
                currentButtonNumber = (9 * (i) + j);
                
               if (currentArrayOfNumbers[i][j] == 0) {
                   btnarray[currentButtonNumber].setText("");
               }
                else 
                btnarray[currentButtonNumber].setText(String.valueOf(currentArrayOfNumbers[i][j]));
            }
        }
                        
                        System.out.println("Hide solution clicked");
                        jButtonShowSolution.setText("Show Solution");
                    }
            }
        }
    }

    // I put this in manually:   INNER CLASS ACTIONLISTENER FOR BUTTON ARRAY implementing ActionListener adaptor ******************************

    private class ArrayButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            

            String objectInfoString = e.getSource().toString();
            String buttonCoordinatesString =
                objectInfoString.substring(objectInfoString.indexOf("[") + 2,
                                           getIndexOfThirdComma(objectInfoString));

            System.out.println("buttonCoordinatesString: " +
                               buttonCoordinatesString);

            //copy paste all 81 coordinates in the JPanel for each button in the array
            String[] buttonCoordinatesInPanelArray =
            { "0,0", "47,0", "94,0", "141,0", "188,0", "235,0", "282,0",
              "329,0", "376,0", "0,33", "47,33", "94,33", "141,33", "188,33",
              "235,33", "282,33", "329,33", "376,33", "0,66", "47,66", "94,66",
              "141,66", "188,66", "235,66", "282,66", "329,66", "376,66",
              "0,99", "47,99", "94,99", "141,99", "188,99", "235,99", "282,99",
              "329,99", "376,99", "0,132", "47,132", "94,132", "141,132",
              "188,132", "235,132", "282,132", "329,132", "376,132", "0,165",
              "47,165", "94,165", "141,165", "188,165", "235,165", "282,165",
              "329,165", "376,165", "0,198", "47,198", "94,198", "141,198",
              "188,198", "235,198", "282,198", "329,198", "376,198", "0,231",
              "47,231", "94,231", "141,231", "188,231", "235,231", "282,231",
              "329,231", "376,231", "0,264", "47,264", "94,264", "141,264",
              "188,264", "235,264", "282,264", "329,264", "376,264" };

            buttonNumberPushed = 0;

            // Determine the button number from the buttonCoordinatesString:
            for (int i = 0; i < buttonCoordinatesInPanelArray.length; i++) {
                if (buttonCoordinatesInPanelArray[i].equals(buttonCoordinatesString))
                    buttonNumberPushed = i; // Starting with 1 on button number
                   
            }

            btnarray[buttonNumberPushed].setBackground(new Color(0x00, 0xFF, 0xFF));
            System.out.println("buttonNumberPushed: " + buttonNumberPushed);


            // Calculate data array indexes int[][] array for the game from buttons pushed:
            int tempInt = buttonNumberPushed + 1;
            rowNumberAccessed = 0;
            colNumberAccessed = 0;
            int countCycles = 0;

            if (buttonNumberPushed + 1 <= NUM_IN_A_ROW) {
                colNumberAccessed =
                        buttonNumberPushed; // buttons numbered from 1
            } else {
                while (tempInt > NUM_IN_A_ROW) {
                    tempInt = tempInt - NUM_IN_A_ROW;
                    if (tempInt <= NUM_IN_A_ROW) {
                        rowNumberAccessed = countCycles + 1;
                        colNumberAccessed =
                                tempInt - 1; //minus however many columns -> 9
                        break;
                    }
                    countCycles++;
                }
            }

            // Row and column indexes for int[][] array containing numbers for the game:
            System.out.println("row index: " + rowNumberAccessed +
                               "   column index: " + colNumberAccessed + "\n");

            // make it so that the button changes color when you press it, so that the user knows which position is edited. (inside here)
            // then when they hit a number on keyboard make the colot go back to original (inside key listener class)

        }

        // Helper method inside inner class for finding the 3rd comma in string:

        private int getIndexOfThirdComma(String passedDownString) {
            int retValue = 0;
            int commaCounter = 0;

            for (int i = 0; i < passedDownString.length(); i++) {
                if (passedDownString.charAt(i) == ',')
                    commaCounter++;

                if (commaCounter == 3) {
                    retValue = i;
                    break;
                }
            }

            return retValue;
        }

    }

    // Implement KeyListener interface here in an inner class called say MyKeyListener (one method inside will retrieve the keypress, which needs to change the appropruate
    // button text in GUI, note that all abstract methods have to be implemented in KeyListener)
    // keyreleased -- most important abstract method

    private class MyKeyListener implements KeyListener

    {
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            //try & catch for Exception for nonnumeric characters
            if (!(c > '0' && c <= '9')) {

                JOptionPane.showMessageDialog(null,
                                              "User has entered nonnumeric value. This is invalid.",
                                              "Invalid Data",
                                              JOptionPane.INFORMATION_MESSAGE);
                return;

            } 
                //put number into array of integers, as well as make it show up graphically on screen
                String n = numberUserEntered;
                n = Character.toString(c);
                System.out.println(n);
                
                btnarray[buttonNumberPushed].setForeground(new Color(0xB2, 0x22, 0x22));
                btnarray[buttonNumberPushed].setText(String.valueOf(n));
                int numberEntered = Integer.parseInt(n);  
                
                System.out.println("Prev # to save:" + currentArrayOfNumbers[rowNumberAccessed][colNumberAccessed]);
                SavedDataRecord savedDataRecord = new SavedDataRecord(currentArrayOfNumbers[rowNumberAccessed][colNumberAccessed], rowNumberAccessed, colNumberAccessed, buttonNumberPushed);
            
                
                currentArrayOfNumbers[rowNumberAccessed][colNumberAccessed] = numberEntered;
                btnarray[buttonNumberPushed].setBackground(new Color(0xD3, 0xD3, 0xD3));
            try{
                    // Create a new instance of data record:
                
               // System.out.println("Prev # to save:" + currentArrayOfNumbers[rowNumberAccessed][colNumberAccessed]);
               // SavedDataRecord savedDataRecord = new SavedDataRecord(numberEntered, rowNumberAccessed, colNumberAccessed, buttonNumberPushed);
                    
                    
                    
                    savedDataObjectsList.add(savedDataRecord);
                
                    lastButtonPushedWasSave = true;
                }
                    catch (Exception ex){
                        System.out.println("Incorrect Data, Unable to Save");
                    JOptionPane.showMessageDialog(null, "Incorrect Data, Unable to Save" , "Unable to Save Data" , JOptionPane.WARNING_MESSAGE);
                }

        }

        public void keyPressed(KeyEvent e) {


        }


        public void keyReleased(KeyEvent e) {
            
            
            
            //0 - if there is nothing in the square (0 is never shown)
            //solutionIntArray
        }
    }


    private class TextFileFilter extends javax.swing.filechooser.FileFilter {
        public boolean accept(File f) {
            return f.isDirectory() ||
                f.getName().toLowerCase().endsWith(".txt");

        }

        public String getDescription() {
            return "Text Files(*.txt)";

        }
    }

    private void showSolution() {
        int[] solution = current_game.getSolution();
        for (int i = 0; i < 81; i++) {
        	btnarray[i].setText(String.valueOf(solution[i]));
        }
    }

    private void checkSolution() {
    	boolean solved = true;
    	int[] solution = current_game.getSolution();
        for (int i = 0; i < 81; i++) {
        	int row_id = SquareData.getRowId(i);
        	int col_id = SquareData.getColId(i);
            if (currentArrayOfNumbers[row_id][col_id] != solution[i]) {
            	solved = false;
            	break;
            }
        }
       if (solved == true)
    	   JOptionPane.showMessageDialog(null, "Puzzle completed successfully.", "Check Solution", JOptionPane.INFORMATION_MESSAGE);
       else
    	   JOptionPane.showMessageDialog(null, "Puzzle is incorrect.", "Check Solution", JOptionPane.WARNING_MESSAGE);
    }

   private void createNewPuzzle() {
        current_game.generateNewSolution();
        ArrayList<Integer> given_values = current_game.getGivenValues();
        int[] solution = current_game.getSolution();

        for (int i = 0; i < 81; i++) {
                int row_id = SquareData.getRowId(i);
                int col_id = SquareData.getColId(i);
            
                btnarray[i].setForeground(new Color(0x00, 0x00, 0x00));  // RESET COLOR
                
               if (given_values.contains(i)) {
            	   btnarray[i].setText(String.valueOf(solution[i]));
                   currentArrayOfNumbers[row_id][col_id] = solution[i];
               } else {
            	   currentArrayOfNumbers[row_id][col_id] = 0;
                   btnarray[i].setText("");
               }   
        }
    }
    
    // Inner class saved data record:
    private class SavedDataRecord {
        
        private int integerData;
        private int xCoordinateOfButton;
        private int yCoordinateOfButton;
        private int buttonNumber;
        
        
        // Costructor for setting the fields for inner class:
        SavedDataRecord(int integerD, int xCoordinate, int yCoordinate, int buttonNum){
            integerData = integerD;
            xCoordinateOfButton = xCoordinate;
            yCoordinateOfButton = yCoordinate;
            buttonNumber = buttonNum;
        }


        // Accessors for inner class fields:
        public void setIntegerData(int integerD) {
            integerData = integerD;
        }

        public int getIntegerData() {
            return integerData;
        }
        
        public void setXCoordinateOfButton (int xCoordinate) {
            xCoordinateOfButton = xCoordinate;
        }

        public int getXCoordinateOfButton() {
            return xCoordinateOfButton;
        }
        
        public void setYCoordinateOfButton (int yCoordinate) {
            yCoordinateOfButton = yCoordinate;
        }

        public int getYCoordinateOfButton() {
            return yCoordinateOfButton;
        }
        
        public void setButtonNumber (int buttonNum) {
            buttonNumber = buttonNum;
        }

        public int getButtonNumber() {
            return buttonNumber;
        }
    
        
    }
    
    public void skillLevelChosen() {
        createNewPuzzle();
    }
}
    
//CREATE AN INTEGER ARRAY THAT IS A COPY OF THE BUTTON ARRAY TO STORE THE INPUTTED VALUES/FUNCTIONALITY STUFF

