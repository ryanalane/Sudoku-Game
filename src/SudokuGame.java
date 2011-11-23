import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.datatransfer.*;
import javax.swing.text.*;
import java.util.Scanner;
import java.io.IOException;  
import java.io.PrintWriter;  
import java.io.FileWriter;  
import javax.swing.text.StyledEditorKit.*;
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
    
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu();
    private JMenuItem menuNewGame = new JMenuItem();
    private JMenuItem menuLoadGame = new JMenuItem();
    private JMenuItem menuSaveGame = new JMenuItem();
    private JMenuItem menuFileExit = new JMenuItem();
    private JMenu menuHelp = new JMenu();
    private JMenuItem menuHelpAbout = new JMenuItem();
    private JMenuItem menuHelpContents = new JMenuItem();
    private JButton jButton3 = new JButton();
    private JButton jButton4 = new JButton();
    private JButton jButton5 = new JButton();
    private JButton jButton6 = new JButton();
    
    private JPanel jPanel1 = new JPanel();
    
    private int levelSelected;
    public static SkillLevelSelection skillLevelSelection;
    
    
    public static JFrame aboutFrame;
    private static boolean aboutExists = false;
    
    URL imgURL = getClass().getResource("icons/sudoku.gif");
    
    
    
    // Add array of buttons to class
    private JButton[] btnarray = new JButton[81];
    
    private ArrayButtonListener arrayButtonListener = new ArrayButtonListener();
    private MyKeyListener myKeyListener = new MyKeyListener();
    private ButtonListener buttonListener = new ButtonListener();
    
    private final int NUM_IN_A_ROW=9;
    private final int NUM_OF_BUTTONS = 81;
    
    private int rowNumberAccessed;
    private int colNumberAccessed;
    private String numberUserEntered;
    private int buttonNumberPushed;
    int[][] currentArrayOfNumbers = new int[rowNumberAccessed][colNumberAccessed];
   // int[][] solutionIntArray = new int[rowNumberAccessed][colNumberAccessed]; -- when randomPuzzle solutions are added
    
    
    
    
    
    
    //properties for File I/O
    private String currentlyLoadedFilePath = "";

    public SudokuGame() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            jbInit();
        } catch (Exception e) {
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
        menuNewGame.addActionListener(new ActionListener() { public void actionPerformed( ActionEvent ae ) { newGame_ActionPerformed( ae ); } } );
        menuLoadGame.setText("Load Game");
        menuLoadGame.addActionListener(new ActionListener() { public void actionPerformed( ActionEvent ae ) { loadGame_ActionPerformed( ae ); } } );
        menuSaveGame.setText("Save Game");
        menuSaveGame.addActionListener(new ActionListener() { public void actionPerformed( ActionEvent ae ) { saveGame_ActionPerformed( ae ); } } );
        menuFileExit.setText("Exit");
        menuFileExit.addActionListener(new ActionListener() { public void actionPerformed( ActionEvent ae ) { fileExit_ActionPerformed( ae ); } } );
        menuHelp.setText("Help");
        menuHelpAbout.setText("About");
        menuHelpAbout.addActionListener( new ActionListener() { public void actionPerformed( ActionEvent ae ) { helpAbout_ActionPerformed( ae ); } } );
        menuHelpContents.setText("Help Contents");
        menuHelpContents.addActionListener(new ActionListener() { public void actionPerformed( ActionEvent ae ) { helpContents_ActionPerformed( ae ); } } );
        
        
        //Modify for other menus -- menuHelpAbout.addActionListener( new ActionListener() { public void actionPerformed( ActionEvent ae ) { helpAbout_ActionPerformed( ae ); } } );

        jButton3.setText("Undo");
        jButton3.setBounds(new Rectangle(175, 330, 75, 21));
        jButton3.addActionListener(buttonListener);        
        jButton3.setBackground(new Color(0, 214, 214));
        jButton4.setText("Reset");
        jButton4.setBounds(new Rectangle(315, 330, 75, 21));
        jButton4.setBackground(new Color(0, 214, 214));
        jButton4.addActionListener(buttonListener); 
        jButton5.setText("Check Solution");
        jButton5.setBounds(new Rectangle(150, 360, 125, 20));
        jButton5.setBackground(new Color(0, 214, 214));
        jButton5.addActionListener(buttonListener); 
        jButton6.setText("Show Solution");
        jButton6.setBounds(new Rectangle(295, 360, 120, 20));
        jButton6.addActionListener(buttonListener); 

        jButton6.setBackground(new Color(0, 214, 214));
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
        this.getContentPane().add(jButton6, null);
        this.getContentPane().add(jButton5, null);
        this.getContentPane().add(jButton4, null);
        this.getContentPane().add(jButton3, null);
        this.getContentPane().add(jPanel1, null);
        this.getContentPane().add(jButton6, null);
        this.getContentPane().add(jButton5, null);
        jPanel1.setLayout(new GridLayout(9,9));

        jPanel1.setBackground(Color.white);
        this.setIconImage(new ImageIcon(imgURL).getImage());
        
        for (int i = 0; i < 81 ; i++){
        btnarray[i] = new JButton();
        btnarray[i].addActionListener(arrayButtonListener);
        btnarray[i].addKeyListener(myKeyListener);    
        jPanel1.add(btnarray[i]);
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

    // I put this in manually to make it runnable and system look and feel:   ******************************
    public static void main(String[] args) {
        SudokuGame sudokuGame = new SudokuGame();
        
                
        sudokuGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sudokuGame.getContentPane().setBackground(Color.GREEN);
        sudokuGame.setLocationRelativeTo(null);
        sudokuGame.setResizable(false);
        sudokuGame.setVisible(true);
        
        skillLevelSelection = new SkillLevelSelection(sudokuGame);
        skillLevelSelection.setResizable(false);
        skillLevelSelection.setAlwaysOnTop(true);
        skillLevelSelection.setLocationRelativeTo(null);
        skillLevelSelection.setVisible(true);
        
        
    }
    
    // *********************************************************************************
    
    void newGame_ActionPerformed(ActionEvent e) {
        skillLevelSelection = new SkillLevelSelection(this);
        skillLevelSelection.setResizable(false);
        skillLevelSelection.setAlwaysOnTop(true);
        skillLevelSelection.setLocationRelativeTo(null);
        skillLevelSelection.setVisible(true); 
        
        
        System.out.println("New game clicked");
        
        
    }
    
    void loadGame_ActionPerformed(ActionEvent e) {
        //filechooser stuff -- Need Functionality class before I can do this
        
    /*
        JFileChooser jfc = new JFileChooser();
        try
        {
                File f = new File(new File(".").getCanonicalPath());
                jfc.setCurrentDirectory(f);
                
                }
                catch (IOException except)
                {
                        System.out.println("Unable to set current directory!");
                }
                
                jfc.setFileFilter(new TextFileFilter());
                int result = jfc.showOpenDialog(this);
                if(result == JFileChooser.CANCEL_OPTION) return;
                
                try{
                        File file = jfc.getSelectedFile();
                        currentlyLoadedFilePath = file.getPath();
                        
                        boolean ranSuccessfully = functionality.loadDataFromInputFile(file); //need to get functionality stuff first
                        jPanel1.setText(functionality.loadTextFromFile(file));
                        if (ranSuccessfully == true)
                        JOptionPane.showMessageDialog(null, "Text file successfully opened." , "Loading Data" , JOptionPane.INFORMATION_MESSAGE);
                        
                }
                catch (Exception ex)
                {
                        JOptionPane.showMessageDialog(null, "Failure to open text file." , "Loading Data" , JOptionPane.WARNING_MESSAGE);
                      
        
    }
    */
        System.out.println("Load game clicked");
    }
    void saveGame_ActionPerformed(ActionEvent e) {
        //filechooser stuff -- Need Functionality class before I can do this
        //save data into array
    }
    
    void fileExit_ActionPerformed(ActionEvent e) {
       // JOptionPane.showMessageDialog("Are you sure you want to exit?");
        
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
                        try
                        {
                                 Process p = Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler /help/SudokuGameHelp.pdf");
                                p.waitFor();
                        }
                        catch (Exception ex)
                        {
                                //Shows where error occurred in Windows
                                ex.printStackTrace();
                        }
                        
                    
        System.out.println("Help Contents clicked");
    }

    public void setLevelSelected(int level) {
       levelSelected = level;
    }


    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
        String command = e.getActionCommand(); {
            if (e.getActionCommand().equals("Undo")) 
            {
             //if no previous values were entered in individual button:
                btnarray[buttonNumberPushed].setText("");
                
                
                /*
                
                ArrayList numbersCurrentlyEntered = new ArrayList();
                 int num = Integer.parseInt(numberUserEntered);       
                for (int i = 0; i<numbersCurrentlyEntered.size(); i++)
                {
                    numbersCurrentlyEntered.add(numberUserEntered);
                    System.out.println(numberUserEntered); 
                }
                        
                ListIterator<String> itr = numbersCurrentlyEntered.listIterator();
                    while (itr.hasPrevious()) {
                      String element = itr.previous();
                      System.out.print(element + " ");
                        btnarray[buttonNumberPushed].setText((itr.previous()));
                        //set to previous value 
                        System.out.print("element has been set to previous value" + itr.previous());
                       
                    }
                */ 
                System.out.println("Undo button clicked");
            }
            else if (e.getActionCommand().equals("Reset")) {
                /*
                for (int i = 0; i<btnarray.length; i++)
                {
                
                }
*/
                //set the buttons back to the array that was original loaded (see generateGrid() method)
                System.out.println("Reset button clicked");
            }
            else if (e.getActionCommand().equals("Check Solution")) 
            {
                
                System.out.println("Check solution clicked");
            }
             else if (e.getActionCommand().equals("Show Solution")) 
            {
                //calls showSolution method
                
            System.out.println("Show solution clicked");
            }
    }
    }
    } 
    
    // I put this in manually:   INNER CLASS ACTIONLISTENER FOR BUTTON ARRAY implementing ActionListener adaptor ******************************
    private class ArrayButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
           
            
            
            String objectInfoString = e.getSource().toString();
            String buttonCoordinatesString = objectInfoString.substring(objectInfoString.indexOf("[")+2, getIndexOfThirdComma(objectInfoString));
             
            System.out.println("buttonCoordinatesString: " + buttonCoordinatesString);
            
            //copy paste all 81 coordinates in the JPanel for each button in the array
            String[] buttonCoordinatesInPanelArray={"0,0","47,0","94,0","141,0", "188,0", "235,0", "282,0", "329,0", "376,0", "0,33", "47,33", "94,33", "141,33", "188,33", "235,33", "282,33", "329,33", "376,33", "0,66", "47,66", "94,66", "141,66", "188,66", "235,66", "282,66", "329,66", "376,66", "0,99", "47,99", "94,99", "141,99", "188,99", "235,99", "282,99", "329,99", "376,99", "0,132", "47,132", "94,132", "141,132", "188,132", "235,132", "282,132", "329,132", "376,132", "0,165", "47,165", "94,165", "141,165", "188,165", "235,165", "282,165", "329,165", "376,165", "0,198", "47,198", "94,198", "141,198", "188,198", "235,198", "282,198", "329,198", "376,198", "0,231", "47,231", "94,231", "141,231", "188,231", "235,231", "282,231", "329,231", "376,231", "0,264", "47,264", "94,264", "141,264", "188,264", "235,264", "282,264", "329,264", "376,264"};
            
            buttonNumberPushed=0;
            
            // Determine the button number from the buttonCoordinatesString:
            for (int i=0; i<buttonCoordinatesInPanelArray.length;i++){
                if(buttonCoordinatesInPanelArray[i].equals(buttonCoordinatesString))
                    buttonNumberPushed = i; // Starting with 1 on button number
                    btnarray[buttonNumberPushed].setBackground(Color.CYAN);
                    
            }
              
            System.out.println("buttonNumberPushed: " + buttonNumberPushed);  
            
            
                
            // Calculate data array indexes int[][] array for the game from buttons pushed:
            int tempInt = buttonNumberPushed+1; 
            rowNumberAccessed = 0;
            colNumberAccessed = 0;
            int countCycles = 0;
            
                if (buttonNumberPushed+1 <= NUM_IN_A_ROW){
                    colNumberAccessed = buttonNumberPushed;  // buttons numbered from 1
                }
                else {
                        while(tempInt > NUM_IN_A_ROW) {
                            tempInt = tempInt - NUM_IN_A_ROW;
                                if (tempInt <= NUM_IN_A_ROW) {
                                    rowNumberAccessed = countCycles+1;
                                    colNumberAccessed = tempInt-1; //minus however many columns -> 9
                                    break;
                                }
                            countCycles++;
                            
                        }
                }
             
            // Row and column indexes for int[][] array containing numbers for the game:    
            System.out.println("row index: " + rowNumberAccessed + "   column index: " + colNumberAccessed + "\n");
            
            // make it so that the button changes color when you press it, so that the user knows which position is edited. (inside here)
            // then when they hit a number on keyboard make the colot go back to original (inside key listener class)
            
        }
            
        // Helper method inside inner class for finding the 3rd comma in string:
            private int getIndexOfThirdComma(String passedDownString){
                int retValue = 0;
                int commaCounter = 0;
                
                for (int i=0; i<passedDownString.length();i++) {
                    if (passedDownString.charAt(i) == ',')
                        commaCounter++;
                    
                    if (commaCounter == 3) {
                        retValue=i;
                        break;
                    }
                }
                
                return retValue;
            }
            
    }
    
    // Implement KeyListener interface here in an inner class called say MyKeyListener (one method inside will retrieve the keypress, which needs to change the appropruate
    // button text in GUI, note that you have to implement all abstract methods in KeyListener, not just the one used by you)
    // keyreleased -- most important abstract method
    private class MyKeyListener implements KeyListener
    
        {
            public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            //try & catch for Exception for nonnumeric characters
            if (!(c > '0' && c <= '9')) {
            
            JOptionPane.showMessageDialog(null, "User has entered nonnumeric value. This is invalid." , "Invalid Data" , JOptionPane.INFORMATION_MESSAGE);
                   
                                    
        }
                else {
                                //put number into array of integers, as well as make it show up graphically on screen 
                                String n = numberUserEntered;
                                n = Character.toString(c);
                                System.out.println(n);
                                btnarray[buttonNumberPushed].setForeground(Color.BLUE); 
                                btnarray[buttonNumberPushed].setText(String.valueOf(n));
                                int numberEntered = Integer.parseInt(n);       
                                //int[][] currentArrayOfNumbers = new int[rowNumberAccessed][colNumberAccessed];
                                for (int i = 0; i<currentArrayOfNumbers.length; i++) {
                                    for (int j = 0; j<currentArrayOfNumbers[i].length; i++) {
                                        
                                        currentArrayOfNumbers[i][j] = numberEntered;
                                        System.out.println(numberEntered); // to be deleted
                                    }
                                
                                }
                                btnarray[buttonNumberPushed].setBackground(Color.WHITE); 
                
                            }
            /*if (c==0) {
                //there is nothing entered in the array
            }
*/
            
            
                 
            }

            public void keyPressed(KeyEvent e) {
                
                                                                                    
                                                }  
                
                
                public void keyReleased(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (c>0 && c<=9) {
                           
                        }
                    //if number is typed - setText color on square (button[i].setText)
                    //currentArrayofNumbers array is identical to other buttonArray
                    //0 - if there is nothing in the square (0 is never shown)
                    //solutionIntArray
                                                                                    }       
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
    
//CREATE AN INTEGER ARRAY THAT IS A COPY OF THE BUTTON ARRAY TO STORE THE INPUTTED VALUES/FUNCTIONALITY STUFF

