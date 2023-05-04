package java21days.lesson14;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DiceRoller extends JFrame implements ActionListener, PropertyChangeListener{

    JTextField[] total = new JTextField[16];
    JButton roll;
    JTextField quantity;
    DiceWorker worker;


    public DiceRoller() {
        super("Dice Roller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 145);

        // set up top row
        JPanel topPane = new JPanel();
        GridLayout paneGrid = new GridLayout(1, 16);
        topPane.setLayout(paneGrid);

        for (int i = 0; i < 16; i++){
            total[i] = new JTextField("0", 4);
            JLabel label = new JLabel((i+3) + ": ");
            JPanel cell = new JPanel();
            cell.add(label);
            cell.add(total[i]);
            topPane.add(cell);
        }
        

        JPanel bottomPane = new JPanel();
        JLabel quantityLabel = new JLabel("Times to Roll: ");
        quantity = new JTextField("0", 5);
        roll = new JButton("Roll");
        roll.addActionListener(this);
        bottomPane.add(quantityLabel);
        bottomPane.add(quantity);
        bottomPane.add(roll);

        GridLayout frameGrid = new GridLayout(2,1);
        setLayout(frameGrid);
        add(topPane);
        add(bottomPane);

        setVisible(true);

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            int [] result = (int[]) worker.get();

            for (int i =0; i < result.length; i++){
                total[i].setText("" + result[i]);
            }
        }catch (Exception exc){
            System.out.print(exc.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int timesToRoll;

        try{
            timesToRoll = Integer.parseInt(quantity.getText());
            roll.setEnabled(false);

            worker = new DiceWorker(timesToRoll);

            worker.addPropertyChangeListener(this);

            worker.execute();
        }catch (NumberFormatException exc){
            System.out.println(exc.getMessage());
        }
    }

    public static void main(String[] args){
        DiceRoller app = new DiceRoller();
    }
    
}
