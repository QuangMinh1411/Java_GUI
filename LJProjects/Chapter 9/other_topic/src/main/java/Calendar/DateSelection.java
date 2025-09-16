package Calendar;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class DateSelection extends JFrame {
    JLabel dateLabel = new JLabel();
    JCalendar myCalendar = new JCalendar();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->new DateSelection().setVisible(true));
    }
    public DateSelection() {
        setTitle("Date Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints gridConstraints = new GridBagConstraints();
        dateLabel.setFont(new Font("Arial",Font.PLAIN,18));
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(dateLabel,gridConstraints);

        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(myCalendar,gridConstraints);
        myCalendar.addPropertyChangeListener(this::myCalendarPropertyChange);

        pack();
        setLocationRelativeTo(null);
    }

    private void myCalendarPropertyChange(PropertyChangeEvent propertyChangeEvent) {
        String[] monthNames = {"January", "February", "March", "April",
                "May", "June", "July", "August", "September", "October",
                "November", "December"};
        dateLabel.setText(monthNames[myCalendar.getMonthChooser().getMonth()]+" "+
                myCalendar.getDayChooser().getDay()+" "+myCalendar.getYearChooser().getYear());
    }
}
