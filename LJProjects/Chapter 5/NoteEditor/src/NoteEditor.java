import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NoteEditor extends JFrame {
    private JScrollPane editorPane;
    private JTextArea editorTextArea;
    private JMenuBar editorMenuBar= new JMenuBar();
    private JMenu fileMenu= new JMenu("File");
    private JMenuItem newMenuItem= new JMenuItem("New");
    private JMenuItem exitMenuItem= new JMenuItem("Exit");
    private JMenu formatMenu= new JMenu("Format");
    private JCheckBoxMenuItem boldMenuItem= new JCheckBoxMenuItem("Bold",false);
    private JCheckBoxMenuItem italicMenuItem= new JCheckBoxMenuItem("Italic",false);
    private JMenu sizeMenu = new JMenu("Size");
    private ButtonGroup sizeGroup = new ButtonGroup();
    private JRadioButtonMenuItem smallMenuItem= new JRadioButtonMenuItem("Small",true);
    private JRadioButtonMenuItem mediumMenuItem= new JRadioButtonMenuItem("Medium",false);
    private JRadioButtonMenuItem largeMenuItem= new JRadioButtonMenuItem("Large",false);
    public NoteEditor() {
        setTitle("Note Editor");
        setResizable(false);
        editorPane = new JScrollPane();
        editorTextArea = new JTextArea();
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        editorPane.setPreferredSize(new Dimension(300,150));
        editorTextArea.setFont(new Font("Arial",Font.PLAIN,12));
        editorTextArea.setLineWrap(true);
        editorTextArea.setWrapStyleWord(true);
        editorPane.setViewportView(editorTextArea);
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=0;
        getContentPane().add(editorPane,gridBagConstraints);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //build menu
        setJMenuBar(editorMenuBar);
        fileMenu.setMnemonic('F');
        formatMenu.setMnemonic('O');
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke('N', Event.CTRL_MASK));
        boldMenuItem.setAccelerator(KeyStroke.getKeyStroke('B', Event.CTRL_MASK));
        italicMenuItem.setAccelerator(KeyStroke.getKeyStroke('I', Event.CTRL_MASK));
        smallMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));
        mediumMenuItem.setAccelerator(KeyStroke.getKeyStroke('M',Event.CTRL_MASK));
        largeMenuItem.setAccelerator(KeyStroke.getKeyStroke('L',Event.CTRL_MASK));
        editorMenuBar.add(fileMenu);
        fileMenu.add(newMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        editorMenuBar.add(formatMenu);
        formatMenu.add(boldMenuItem);
        formatMenu.add(italicMenuItem);
        formatMenu.add(sizeMenu);
        sizeMenu.add(smallMenuItem);
        sizeMenu.add(mediumMenuItem);
        sizeMenu.add(largeMenuItem);
        sizeGroup.add(smallMenuItem);
        sizeGroup.add(mediumMenuItem);
        sizeGroup.add(largeMenuItem);

        newMenuItem.addActionListener(this::newMenuItemActionPerformed);
        exitMenuItem.addActionListener(this::exitMenuItemActionPerformed);
        boldMenuItem.addActionListener(this::formatMenuItemActionPerformed);
        italicMenuItem.addActionListener(this::formatMenuItemActionPerformed);
        smallMenuItem.addActionListener(this::formatMenuItemActionPerformed);
        mediumMenuItem.addActionListener(this::formatMenuItemActionPerformed);
        largeMenuItem.addActionListener(this::formatMenuItemActionPerformed);
        pack();

    }

    private void formatMenuItemActionPerformed(ActionEvent actionEvent) {
        int style = computeFontStyle();
        int size = computeFontSize();
        editorTextArea.setFont(new Font("Arial", style, size));
    }

    private int computeFontStyle() {
        int style = Font.PLAIN;
        if (boldMenuItem.isSelected()) {
            style |= Font.BOLD;
        }
        if (italicMenuItem.isSelected()) {
            style |= Font.ITALIC;
        }
        return style;
    }

    private int computeFontSize() {
        if (smallMenuItem.isSelected()) {
            return 12;
        }
        if (mediumMenuItem.isSelected()) {
            return 18;
        }
        if (largeMenuItem.isSelected()) {
            return 24;
        }
        // Fallback to current text area size if none explicitly selected
        return editorTextArea.getFont().getSize();
    }

    private void exitMenuItemActionPerformed(ActionEvent actionEvent) {
        System.exit(0);
    }

    private void newMenuItemActionPerformed(ActionEvent actionEvent) {
        if(JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new file?",
                "New File", JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            editorTextArea.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new NoteEditor().setVisible(true);
        });
    }
}
