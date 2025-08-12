import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.time.Year;
import java.util.Date;

public class DateInput extends JFrame {
    private  int monthDays;
    private JSpinner monthSpinner = new JSpinner();
    private JSpinner daySpinner = new JSpinner();
    private JLabel dateLabel = new JLabel();
    private String[] monthNames = new String[12];
    public DateInput() {

        //Frame
        setTitle("Date Input");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width -
                getWidth())), (int) (0.5 * (screenSize.height -
                getHeight())), getWidth(), getHeight());
        GridBagConstraints gridConstraints;

        monthNames[0] = "January";
        monthNames[1] = "February";
        monthNames[2] = "March";
        monthNames[3] = "April";
        monthNames[4] = "May";
        monthNames[5] = "June";
        monthNames[6] = "July";
        monthNames[7] = "August";
        monthNames[8] = "September";
        monthNames[9] = "October";
        monthNames[10] = "November";
        monthNames[11] = "December";

        monthSpinner.setPreferredSize(new Dimension(150,30));
        monthSpinner.setModel(new SpinnerListModel(monthNames));
        ((JSpinner.DefaultEditor) monthSpinner.getEditor()).getTextField().setFont(new Font("Arial", Font.PLAIN, 18));
        ((JSpinner.DefaultEditor) monthSpinner.getEditor()).getTextField().setForeground(Color.blue);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(monthSpinner, gridConstraints);
        monthSpinner.addChangeListener(e->{
            monthStateChange(e);
            dateStateChange(e);
        });

        daySpinner.setPreferredSize(new Dimension(100,30));
        monthDays = getDaysInMonth(new Date().getYear(),monthSpinner.getValue().toString());
        makeSpinnerDay();
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(daySpinner, gridConstraints);
        daySpinner.addChangeListener(this::dateStateChange);

        dateLabel.setText("January 1");
        dateLabel.setFont(new Font("Arial", Font.BOLD, 24));
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        gridConstraints.insets = new Insets(0, 0, 10, 0);
        getContentPane().add(dateLabel, gridConstraints);

        pack();
    }

    private void monthStateChange(ChangeEvent e){
        monthDays = getDaysInMonth(new Date().getYear(),monthSpinner.getValue().toString());
        makeSpinnerDay();

    }

    private void makeSpinnerDay(){
        SpinnerNumberModel dayNumberModel = new SpinnerNumberModel(1, 1, monthDays, 1);
        daySpinner.setModel(dayNumberModel);
        ((JSpinner.DefaultEditor) daySpinner.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        ((JSpinner.DefaultEditor) daySpinner.getEditor()).getTextField().setFont(new Font("Arial", Font.PLAIN, 18));
        ((JSpinner.DefaultEditor) daySpinner.getEditor()).getTextField().setForeground(Color.blue);
    }
    private void dateStateChange(ChangeEvent e) {
        dateLabel.setText(monthSpinner.getValue() + " "+ daySpinner.getValue());
    }

    private int getDaysInMonth(int year,String month) {
        switch (month) {
            case "January":
                case "March":
                    case "May":
                        case "July":
                            case "August":
                                case "October":
                                    case "December":
                                        return 31;
            case "February":
                if(Year.isLeap(year)) return 29;
                else return 28;

                default:
                    return 30;

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DateInput window = new DateInput();
            window.setVisible(true);
        });
    }
}
