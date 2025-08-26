import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Debugging extends JFrame {
    private JButton procedure1Button = new JButton();
    private JButton procedure2Button= new JButton();
    int xCount = 0;
    int ySum = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new Debugging().setVisible(true);
        });
    }
    public Debugging() {
        //frame constructor
        setTitle("Debug");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();
        procedure1Button.setText("Run Procedure 1");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(20, 30, 0, 30);
        getContentPane().add(procedure1Button, gridConstraints);
        procedure1Button.addActionListener(this::procedure1ButtonActionPerformed);
        procedure2Button.setText("Run Procedure 2");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(0, 30, 20, 30);
        getContentPane().add(procedure2Button, gridConstraints);
        procedure2Button.addActionListener(this::procedure2ButtonActionPerformed);

        pack();
        setLocationRelativeTo(null);
    }

    private void procedure1ButtonActionPerformed(ActionEvent actionEvent) {
        int x1=-1,y1;
        do{
            x1++;
            y1=fcn(x1);
            System.out.println("In Procedure 1, x1= "+x1+" y1="+y1);
            xCount++;
            ySum+=y1;
        }while(x1<20);
    }

    private void procedure2ButtonActionPerformed(ActionEvent actionEvent) {
        int x2,y2;
        for(x2=-10;x2<=10;x2++){
            y2=5*fcn(x2);
            System.out.println("In Procedure 2, x2= "+x2+" y2="+y2);
            xCount++;
            ySum+=y2;
        }
    }

    public int fcn(int x) {
        double value;
        value = 0.1*x*x;
        return (int)value;
    }

}
