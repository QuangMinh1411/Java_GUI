package printing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class Countries extends JFrame {
    GraphicsPanel myPanel = new GraphicsPanel();
    JButton printButton = new JButton("Print");
    static Image myWorld = new ImageIcon("src/main/resources/images/world.png").getImage();
    static final int numCountries = 62;
    static final int countriesPerPage = 25;
    static String[] country = new String[numCountries];
    static String[] capital = new String[numCountries];
    static int lastPage = (int) ((numCountries - 1) / countriesPerPage);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Countries().setVisible(true));
    }

    public Countries() {
        setTitle("Countries");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();
        myPanel.setPreferredSize(new Dimension(500, 250));
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(myPanel, gridConstraints);

        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(printButton, gridConstraints);
        printButton.addActionListener(e -> {
            printButtonActionPerformed(e);
        });


        pack();
        setLocationRelativeTo(null);
        country[0] = "Afghanistan";
        capital[0] = "Kabul";
        country[1] = "Albania";
        capital[1] = "Tirane";
        country[2] = "Australia";
        capital[2] = "Canberra";
        country[3] = "Austria";
        capital[3] = "Vienna";
        country[4] = "Bangladesh";
        capital[4] = "Dacca";
        country[5] = "Barbados";
        capital[5] = "Bridgetown";
        country[6] = "Belgium";
        capital[6] = "Brussels";
        country[7] = "Bulgaria";
        capital[7] = "Sofia";
        country[8] = "Burma";
        capital[8] = "Rangoon";
        country[9] = "Cambodia";
        capital[9] = "Phnom Penh";
        country[10] = "China";
        capital[10] = "Peking";
        country[11] = "Czechoslovakia";
        capital[11] = "Prague";
        country[12]
                = "Denmark";
        capital[12] = "Copenhagen";
        country[13] = "Egypt";
        capital[13] = "Cairo";
        country[14] = "Finland";
        capital[14] = "Helsinki";
        country[15] = "France";
        capital[15] = "Paris";
        country[16] = "Germany";
        capital[16] = " Berlin";
        country[17] = "Greece";
        capital[17] = "Athens";
        country[18] = "Hungary";
        capital[18] = "Budapest";
        country[19] = "Iceland";
        capital[19] = "Reykjavik";
        country[20] = "India";
        capital[20] = "New Delhi";
        country[21] = "Indonesia";
        capital[21] = "Jakarta";
        country[22] = "Iran";
        capital[22] = "Tehran";
        country[23] = "Iraq";
        capital[23] = "Baghdad";
        country[24] = "Ireland";
        capital[24] = "Dublin";
        country[25] = "Israel";
        capital[25] = "Jerusalem";
        country[26] = "Italy";
        capital[26] = "Rome";
        country[27] = "Japan";
        capital[27] = "Tokyo";
        country[28] = "Jordan";
        capital[28] = "Amman";
        country[29] = "Kuwait";
        capital[29] = "Kuwait";
        country[30] = "Laos";
        capital[30] = "Vientiane";
        country[31] = "Lebanon";
        capital[31] = "Beirut";
        country[32] = "Luxembourg";
        capital[32] = "Luxembourg";
        country[33] = "Malaysia";
        capital[33] = "Kuala Lumpur";
        country[34] = "Mongolia";
        capital[34] = "Ulaanbaatar";
        country[35]
                = "Nepal";
        capital[35] = "Katmandu";
        country[36] = "Netherlands";
        capital[36] = "Amsterdam";
        country[37] = "New Zealand";
        capital[37] = "Wellington";
        country[38] = "North Korea";
        capital[38] = "Pyongyang";
        country[39] = "Norway";
        capital[39] = "Oslo";
        country[40] = "Oman";
        capital[40] = "Muscat";
        country[41] = "Pakistan";
        capital[41] = "Islamabad";
        country[42] =
                "Philippines";
        capital[42] = "Manila";
        country[43] = "Poland";
        capital[43] = "Warsaw";
        country[44] = "Portugal";
        capital[44] = "Lisbon";
        country[45] = "Romania";
        capital[45] = "Bucharest";
        country[46] = "Russia";
        capital[46] = "Moscow";
        country[47] = "Saudi Arabia";
        capital[47] = "Riyadh";
        country[48] =
                "Singapore";
        capital[48] = "Singapore";
        country[49] = "South Korea";
        capital[49] = "Seoul";
        country[50] = "Spain";
        capital[50] = "Madrid";
        country[51] = "Sri Lanka";
        capital[51] = "Colombo";
        country[52] = "Sweden";
        capital[52] = "Stockholm";
        country[53] = "Switzerland";
        capital[53] = "Bern";
        country[54] = "Syria";
        capital[54] = "Damascus";
        country[55] = "Taiwan";
        capital[55] = "Taipei";
        country[56] = "Thailand";
        capital[56] = "Bangkok";
        country[57] = "Turkey";
        capital[57] = "Ankara";
        country[58] = "United Kingdom";
        capital[58] = "London";
        country[59] = "Vietnam";
        capital[59] = "Hanoi";
        country[60] = "Yemen";
        capital[60] = "Sana";
        country[61] = "Yugoslavia";
        capital[61] = "Belgrade";
    }

    private void printButtonActionPerformed(ActionEvent e) {
        // print countries and capitals first - defined in MyDocument
        PrinterJob myPrinterJob = PrinterJob.getPrinterJob();
        myPrinterJob.setPrintable(new MyDocument());
        if (myPrinterJob.printDialog()) {
            try {
                myPrinterJob.print();
            } catch (PrinterException ex) {
                JOptionPane.showConfirmDialog(null, ex.getMessage(),
                        "Print Error", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        PrintUtilities.printComponent(myPanel);
    }

    class GraphicsPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(Countries.myWorld, 0, 0, 500, 250, this);
            g2d.dispose();
        }
    }

    class MyDocument implements Printable {

        @Override
        public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
            Font printFont;
            Rectangle2D stringRect;
            String myString;
            int y;
            int iEnd;
            Graphics2D g2D = (Graphics2D) g;
            if (pageIndex >
                    Countries.lastPage) {
                return NO_SUCH_PAGE;
            }
            // put titles and countries/capitals
            printFont = new Font("Arial", Font.BOLD, 20);
            g2D.setFont(printFont);
            myString = "Countries and Capitals - Page " +
                    String.valueOf(pageIndex + 1);
            stringRect =
                    printFont.getStringBounds(myString, g2D.getFontRenderContext());
            g2D.drawString(myString, (int) (pf.getImageableX() + 0.5 *
                    (pf.getImageableWidth() - stringRect.getWidth())), (int)
                    (pf.getImageableY() + stringRect.getHeight())); // starting y position
            printFont = new Font("Arial", Font.ITALIC, 14);
            g2D.setFont(printFont);
            myString = "Country";
            stringRect = printFont.getStringBounds(myString, g2D.getFontRenderContext());
            y = (int) (pf.getImageableX() + 4 * stringRect.getHeight());
            g2D.drawString(myString, (int) pf.getImageableX(), y);
            myString = "Capital";
            g2D.drawString(myString, (int) (pf.getImageableX() + 0.5 * pf.getImageableWidth()), y);
            y += (int) (2 * stringRect.getHeight());
            printFont = new Font("Arial", Font.PLAIN, 14);
            stringRect = printFont.getStringBounds("Test String",
                    g2D.getFontRenderContext());
            g2D.setFont(printFont);
            iEnd = Countries.countriesPerPage * (pageIndex + 1);
            if (iEnd > Countries.numCountries) {
                iEnd = Countries.numCountries;
            }
            for (int i = 0 + Countries.countriesPerPage * pageIndex; i < iEnd; i++) {
                g2D.drawString(Countries.country[i], (int) (pf.getImageableX()),
                        y);
                g2D.drawString(Countries.capital[i], (int) (pf.getImageableX() + 0.5 *
                        pf.getImageableWidth()), y);
                y += (int) (stringRect.getHeight());
            }
            return PAGE_EXISTS;
        }
    }
}
