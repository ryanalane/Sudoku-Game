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

import javax.swing.ImageIcon;

public class About extends JPanel {
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JButton jButtonOK = new JButton();

    public About() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private void jbInit() throws Exception {
        this.setLayout( null );
        this.setSize(new Dimension(583, 333));
        this.setBackground(new Color(255, 173, 82));
        jLabel1.setText("Sudoku Game");
        jLabel1.setBounds(new Rectangle(25, 20, 140, 35));
        jLabel1.setBackground(new Color(255, 123, 82));
        jLabel1.setFont(new Font("Tahoma", 0, 16));
        jLabel2.setText("Developed by Sylvia Barnai and Ryan Lane");
        jLabel2.setBounds(new Rectangle(25, 65, 290, 35));
        jLabel2.setFont(new Font("Tahoma", 0, 14));
        jLabel3.setText("Copyright 2011");
        jLabel3.setBounds(new Rectangle(25, 110, 115, 20));
        jButtonOK.setText("OK");
        jButtonOK.setBounds(new Rectangle(45, 285, 75, 21));
        jButtonOK.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButtonOK_actionPerformed(e);
                }
            });
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
        
        URL imgURL = getClass().getResource("icons/keyboard.gif");
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
  






      
