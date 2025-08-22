import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class NoteEditor extends JFrame {
    private JScrollPane editorPane;
    private JTextArea editorTextArea;
    private JFileChooser myChooser= new JFileChooser();
    private JMenuBar editorMenuBar= new JMenuBar();
    private JMenu fileMenu= new JMenu("File");
    private JMenuItem newMenuItem= new JMenuItem("New");
    private JMenuItem openMenuItem= new JMenuItem("Open");
    private JMenuItem saveMenuItem= new JMenuItem("Save");
    private JMenuItem exitMenuItem= new JMenuItem("Exit");
    private JMenu formatMenu= new JMenu("Format");
    private JCheckBoxMenuItem boldMenuItem= new JCheckBoxMenuItem("Bold",false);
    private JCheckBoxMenuItem italicMenuItem= new JCheckBoxMenuItem("Italic",false);
    private JMenu sizeMenu = new JMenu("Size");
    private ButtonGroup sizeGroup = new ButtonGroup();
    private JRadioButtonMenuItem smallMenuItem= new JRadioButtonMenuItem("Small",true);
    private JRadioButtonMenuItem mediumMenuItem= new JRadioButtonMenuItem("Medium",false);
    private JRadioButtonMenuItem largeMenuItem= new JRadioButtonMenuItem("Large",false);
    private JMenu helpMenu= new JMenu("Help");
    private JMenuItem aboutMenuItem= new JMenuItem("About Note Editor");
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
        helpMenu.setMnemonic('H');
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke('N', Event.CTRL_MASK));
        boldMenuItem.setAccelerator(KeyStroke.getKeyStroke('B', Event.CTRL_MASK));
        italicMenuItem.setAccelerator(KeyStroke.getKeyStroke('I', Event.CTRL_MASK));
        smallMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));
        mediumMenuItem.setAccelerator(KeyStroke.getKeyStroke('M',Event.CTRL_MASK));
        largeMenuItem.setAccelerator(KeyStroke.getKeyStroke('L',Event.CTRL_MASK));
        aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke('A',Event.CTRL_MASK));
        editorMenuBar.add(fileMenu);
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
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
        editorMenuBar.add(helpMenu);
        helpMenu.add(aboutMenuItem);

        newMenuItem.addActionListener(this::newMenuItemActionPerformed);
        openMenuItem.addActionListener(this::openMenuItemActionPerformed);
        saveMenuItem.addActionListener(this::saveMenuItemActionPerformed);
        exitMenuItem.addActionListener(this::exitMenuItemActionPerformed);
        boldMenuItem.addActionListener(this::formatMenuItemActionPerformed);
        italicMenuItem.addActionListener(this::formatMenuItemActionPerformed);
        smallMenuItem.addActionListener(this::formatMenuItemActionPerformed);
        mediumMenuItem.addActionListener(this::formatMenuItemActionPerformed);
        largeMenuItem.addActionListener(this::formatMenuItemActionPerformed);
        aboutMenuItem.addActionListener(e->{
            JOptionPane.showMessageDialog(null,"This application is to change the style of paragraph in the text box",
                    "About",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        int fontSize = 1; // default to Small if not specified or on error
        try (BufferedReader inputFile = new BufferedReader(new FileReader("note.ini"))) {
            boldMenuItem.setSelected(Boolean.parseBoolean(inputFile.readLine()));
            italicMenuItem.setSelected(Boolean.parseBoolean(inputFile.readLine()));
            fontSize = Integer.parseInt(inputFile.readLine());
        } catch (IOException ex) {
            JOptionPane.showConfirmDialog(null, ex.getMessage(),
                    "Error Reading Configuration File", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE);
        }

        switch (fontSize) {
            case 1:

                smallMenuItem.doClick();
                break;
            case 2:
                mediumMenuItem.doClick();
                break;
            case 3:
                largeMenuItem.doClick();
                break;

        }


        pack();

    }

    private void saveMenuItemActionPerformed(ActionEvent e) {
        myChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        myChooser.setDialogTitle("Save Text File");
        myChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files","txt"));
        if(myChooser.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
            if(myChooser.getSelectedFile().exists()){
                int response;
                response = JOptionPane.showConfirmDialog(
                        null,
                        myChooser.getSelectedFile().toString() + " exists. Overwrite?",
                        "Confirm Save",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if(response == JOptionPane.NO_OPTION){
                    return;
                }
            }
            String fileName = myChooser.getSelectedFile().toString();
            int dotlocation = fileName.lastIndexOf('.')    ;
            if (dotlocation == -1) {
                // No extension provided; default to .txt
                fileName += ".txt";
            } else {
                // Replace existing extension with .txt to normalize
                fileName = fileName.substring(0, dotlocation) + ".txt";
            }
            try (PrintWriter outputFile = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
                // Write entire text content at once to avoid per-line offset calculations
                outputFile.print(editorTextArea.getText());
                outputFile.flush();
            } catch (IOException ex) {
                JOptionPane.showConfirmDialog(null, ex.getMessage(), "Error Writing File",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void openMenuItemActionPerformed(ActionEvent e) {
        String myLine;
        myChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        myChooser.setDialogTitle("Open Text File");
        myChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        if(myChooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
            try (BufferedReader inputFile = new BufferedReader(new FileReader(myChooser.getSelectedFile().toString()))) {
                editorTextArea.setText("");
                while ((myLine = inputFile.readLine()) != null) {
                    editorTextArea.append(myLine + "\n");
                }
            } catch (IOException ex) {
                JOptionPane.showConfirmDialog(null, ex.getMessage(), "Error Opening File",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            }
        }
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
