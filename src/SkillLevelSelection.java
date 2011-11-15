import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class SkillLevelSelection extends JFrame implements ActionListener {
    private JButton jButton1 = new JButton();
    private JLabel jLabel1 = new JLabel();
    private JButton jButton2 = new JButton();
    private JButton jButton3 = new JButton();
    
   public SudokuGame parent;

    public SkillLevelSelection(SudokuGame parent) {
        try {
            jbInit();
            this.parent = parent;
            //this.parent = parent;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize(new Dimension(273, 193));
        jButton1.setText("Beginner");
        jButton1.setBounds(new Rectangle(85, 50, 100, 25));
        jButton1.setBackground(new Color(231, 115, 0));
        jLabel1.setText("Please select a skill level:");
        jLabel1.setBounds(new Rectangle(65, 20, 145, 25));
        jLabel1.setFont(new Font("Tahoma", 0, 14));
        jButton2.setText("Intermediate");
        jButton2.setBounds(new Rectangle(85, 80, 100, 25));
        jButton2.setBackground(new Color(247, 247, 0));
        jButton2.addActionListener(this);
        jButton3.setText("Advanced");
        jButton3.setBounds(new Rectangle(85, 110, 100, 25));
        jButton3.setBackground(new Color(0, 181, 0));
        jButton3.addActionListener(this);
        this.getContentPane().add(jButton3, null);
        this.getContentPane().add(jButton2, null);
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(jButton1, null);
    }

    public void actionPerformed(ActionEvent e) {
        int level = 0;
        if (e.getActionCommand()== "Beginner")
        {
        System.out.println("Beginner clicked");
        level = 1;
        }
        else if (e.getActionCommand()== "Intermediate")
        {
        System.out.println("Intermediate clicked");
        level = 2;
        }
        else
        {
        level = 3;
        System.out.println("Advanced clicked");
        }
        parent.setLevelSelected(level);
        this.dispose();
    }

   
}
