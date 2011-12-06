import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class About extends JPanel {
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JButton jButtonOK = new JButton();
    
    private Icon labelSudokuGridpic = new ImageIcon(getClass().getResource("media/sudokugrid.JPG"));
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel6 = new JLabel();

    public About() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private void jbInit() throws Exception {
        this.setLayout( null );
        this.setSize(new Dimension(325, 274));
        this.setBackground(new Color(255, 173, 82));
        jLabel1.setText("Sudoku Game");
        jLabel1.setBounds(new Rectangle(20, 5, 140, 20));
        jLabel1.setBackground(new Color(255, 123, 82));
        jLabel1.setFont(new Font("Tahoma", 0, 14));
        jLabel2.setText("Developed by Sylvia Barnai and Ryan Lane");
        jLabel2.setBounds(new Rectangle(20, 30, 290, 25));
        jLabel2.setFont(new Font("Tahoma", 0, 12));
        jLabel3.setText("Copyright 2011");
        jLabel3.setBounds(new Rectangle(20, 60, 115, 20));
        jButtonOK.setText("OK");
        jButtonOK.setBounds(new Rectangle(25, 235, 75, 21));
        jButtonOK.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButtonOK_actionPerformed(e);
                }
            });
        jLabel4.setBounds(new Rectangle(135, 150, 180, 90));
        jLabel4.setIcon(labelSudokuGridpic);
        jLabel5.setText("This program is compatible with Windows, Mac and");
        jLabel5.setBounds(new Rectangle(15, 90, 295, 15));
        jLabel5.setFont(new Font("Dialog", 1, 10));
        jLabel6.setText("Linux.");
        jLabel6.setBounds(new Rectangle(25, 105, 220, 15));
        jLabel6.setFont(new Font("Dialog", 1, 10));
        this.add(jLabel6, null);
        this.add(jLabel5, null);
        this.add(jLabel4, null);
        this.add(jButtonOK, null);

        this.add(jLabel3, null);
        this.add(jLabel2, null);
        this.add(jLabel1, null);
        SudokuGame.aboutFrame.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e)
                {
                    SudokuGame.unsetAboutExists();
                        System.out.println("About Closed");
                }
        }
        );
        
        URL imgURL = getClass().getResource("icons/about.gif");
        SudokuGame.aboutFrame.setIconImage(new ImageIcon(imgURL).getImage());
    }

   
    
    public static void createAndShowGUI() {

        SudokuGame.aboutFrame = new JFrame("About");
        SudokuGame.aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          
          // Create an instance of the panel, which is this class itself
          About panelInstance = new About();
          
          // Set up the panel instance you just created as the contents of the JFrame
          // that was created (addCDsAndDVDsToCollection) in main screen:
        SudokuGame.aboutFrame.setContentPane(panelInstance);

        SudokuGame.aboutFrame.setResizable(false);
        SudokuGame.aboutFrame.setLocationRelativeTo(null);
        SudokuGame.aboutFrame.setSize(325,300);
        SudokuGame.aboutFrame.setVisible(true);
                      }



        private void jButtonOK_actionPerformed(ActionEvent e) {
        SudokuGame.aboutFrame.dispose();
        }
 
  	
    }
  






      
