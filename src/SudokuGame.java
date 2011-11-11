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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class SudokuGame extends JFrame implements ActionListener {
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu();
    private JMenuItem menuNewGame = new JMenuItem();
    private JMenuItem menuLoadGame = new JMenuItem();
    
    
    
    private JMenuItem menuFileExit = new JMenuItem();
    private JMenu menuHelp = new JMenu();
    private JMenuItem menuHelpAbout = new JMenuItem();
    private JButton jButton5 = new JButton();
    private JButton jButton6 = new JButton();
    private JPanel jPanel1 = new JPanel();
    
    // Add array of buttons to class
    private JButton[] btnarray = new JButton[81];
    
    private ArrayButtonListener arrayButtonListener = new ArrayButtonListener();
    private MyKeyListener myKeyListener = new MyKeyListener();
    
    private final int NUM_IN_A_ROW=9;
    
    // The cell we are currently messing with...
    private int rowNumberAccessed;
    private int colNumberAccessed;
    //private int buttonNumber; -- make an accessor (get method) for it as well!
    private int buttonNumberPushed;
    

    public SudokuGame() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setJMenuBar(menuBar);
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(415, 364));
        this.setTitle("Sudoku Game");
        menuFile.setText("File");
        menuNewGame.setText("New Game");
        menuNewGame.addActionListener(new ActionListener() { public void actionPerformed( ActionEvent ae ) { newGame_ActionPerformed( ae ); } } );
        menuLoadGame.setText("Load Game");
        menuLoadGame.addActionListener(new ActionListener() { public void actionPerformed( ActionEvent ae ) { loadGame_ActionPerformed( ae ); } } );
        
        menuFileExit.setText("Exit");
        menuFileExit.addActionListener(new ActionListener() { public void actionPerformed( ActionEvent ae ) { fileExit_ActionPerformed( ae ); } } );
        menuHelp.setText("Help");
        menuHelpAbout.setText("About");
        menuHelpAbout.addActionListener( new ActionListener() { public void actionPerformed( ActionEvent ae ) { helpAbout_ActionPerformed( ae ); } } );
        
        
        //Modify for other menus -- menuHelpAbout.addActionListener( new ActionListener() { public void actionPerformed( ActionEvent ae ) { helpAbout_ActionPerformed( ae ); } } );
        jButton5.setText("jButton5");
        jButton5.setBounds(new Rectangle(190, 240, 135, 25));
        jButton5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton5_actionPerformed(e);
                }
            });
        jButton6.setText("jButton6");
        jButton6.setBounds(new Rectangle(190, 240, 135, 25));
        jButton6.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton6_actionPerformed(e);
                }
            });
        jPanel1.setBounds(new Rectangle(65, 25, 225, 215));
        menuFile.add(menuNewGame);
        menuFile.add(menuFileExit);
        menuBar.add(menuFile);
        menuHelp.add(menuHelpAbout);
        menuBar.add(menuHelp);
        this.getContentPane().add(jPanel1, null);


        // Setting a grid layout to the JPanel )Layout manager has to be defined)
        this.getContentPane().add(jButton6, null);
        this.getContentPane().add(jButton5, null);
        jPanel1.setLayout(new GridLayout(9,9));
        
        for (int i = 0; i < 81 ; i++){
        btnarray[i] = new JButton(i+1+"");
        btnarray[i].addActionListener(arrayButtonListener);    
        jPanel1.add(btnarray[i]);
        }
        
        
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
        sudokuGame.setSize(430,360);
        sudokuGame.setResizable(false);
        sudokuGame.setLocationRelativeTo(null);
        sudokuGame.setVisible(true);
                
                // Set system look and feel:
                try {
                                       // Set the look and feel for the current OS (Windows) Scheme and
                                       UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                                   } catch (Exception e) {
                                       e.printStackTrace();
                                   }
        
    }
    // *********************************************************************************
    
    void newGame_ActionPerformed(ActionEvent e) {
        System.out.println("New game clicked");
    }
    
    void loadGame_ActionPerformed(ActionEvent e) {
        System.out.println("Load game clicked");
    }
    
    void fileExit_ActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    void helpAbout_ActionPerformed(ActionEvent e) {
        //JOptionPane.showMessageDialog(this, new About(), "About", JOptionPane.PLAIN_MESSAGE); // to be omitted and put in actual code for bringing up the About screen
    }

    private void jButton5_actionPerformed(ActionEvent e) {
        System.out.println("Button5 Pushed");
    }

    private void jButton6_actionPerformed(ActionEvent e) {
        System.out.println("Button6 Pushed");
    }


    // I put this in manually:   INNER CLASS ACTIONLISTENER FOR BUTTON ARRAY implementing ActionListener adaptor ******************************
    private class ArrayButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
              //  if (e.getActionCommand().equals("1")){   --- will NOT WORK
                
           //System.out.println("Clicked button info:\n" + e.getSource().toString());
            
            String objectInfoString = e.getSource().toString();
            String buttonCoordinatesString = objectInfoString.substring(objectInfoString.indexOf("[")+2, getIndexOfThirdComma(objectInfoString));
             
            System.out.println("buttonCoordinatesString: " + buttonCoordinatesString);
            
            //String[] buttonCoordinatesInPanelArray={"0,0","112,0","0,107","112,107"};
            String[] buttonCoordinatesInPanelArray={"0,0","25,0","50,0","75,0", ""};
            //copy paste all 81 coordinates in the JPanel for each button in the array [see above]
            buttonNumberPushed=0;
            
            // Determine the button number from the buttonCoordinatesString:
            for (int i=0; i<buttonCoordinatesInPanelArray.length;i++){
                if(buttonCoordinatesInPanelArray[i].equals(buttonCoordinatesString))
                    buttonNumberPushed = i+1;  // Starting with 1 on button number
            }
              
            System.out.println("buttonNumberPushed: " + buttonNumberPushed);    
                
            // Calculate DATA array indexes int[][] array for the game from buttons pushed:
            int tempInt = buttonNumberPushed; 
            rowNumberAccessed = 0;
            colNumberAccessed = 0;
            int countCycles = 0;
            
                if (buttonNumberPushed <= NUM_IN_A_ROW){
                    colNumberAccessed = buttonNumberPushed-1;  // buttons numbered from 1
                }
                else {
                        while(tempInt > NUM_IN_A_ROW) {
                            tempInt = tempInt - NUM_IN_A_ROW;
                                if (tempInt <= NUM_IN_A_ROW) {
                                    countCycles++; // this is in a BAD place -- only works for 2 rows, incremented only once -- placed outside of if
                                    rowNumberAccessed = countCycles;
                                    colNumberAccessed = tempInt-1; //minus however many columns -> 9
                                    break;
                                }
                        }
                }
             
            // Row and column indexes for int[][] array containing numbers for the game:    
            //System.out.println("row index: " + rowNumberAccessed + "   column index: " + colNumberAccessed + "\n");
            
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
        }
            
    
    public void actionPerformed(ActionEvent e) {
    }


    
            public void keyPressed(KeyEvent e) {
                    
                    //System.out.println(e);  // just to see the info about typed keyboard key
                
                                                                                    }  
                
                /** Handle the key released event from the text field. */
                public void keyReleased(KeyEvent e) {
                   // Do nothing here
                                                                                    }       
                } 
    
            public void actionPerformed(ActionEvent e) {

                    // Menu item actions
                    String command = e.getActionCommand();
            if (command.equals("New Game")) {
                
            }
            
            else if (command.equals("Load Game"))
            {
                
            }
            
            else if (command.equals("Load Game"))
            {
        
            }
            
            else if (command.equals("Load Game"))
            {
        
            }
            
            else if (command.equals("Exit Game"))
            {
        
            }
    }
    
}



















    
    

   


