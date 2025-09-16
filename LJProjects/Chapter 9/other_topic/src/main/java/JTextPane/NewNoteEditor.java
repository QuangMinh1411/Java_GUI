package JTextPane;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NewNoteEditor extends JFrame {
    JMenuBar editorMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem newMenuItem = new JMenuItem("New");
    JMenuItem openMenuItem = new JMenuItem("Open");
    JMenuItem saveMenuItem = new JMenuItem("Save");
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    JMenu formatMenu = new JMenu("Format");
    JMenu sizeMenu = new JMenu("Size");
    JMenu helpMenu = new JMenu("Help");
    JMenuItem aboutMenuItem = new JMenuItem("About Note Editor");
    JScrollPane editorPane = new JScrollPane();
    JTextPane editorTextPane = new JTextPane();
    JFileChooser myChooser = new JFileChooser();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->new NewNoteEditor().setVisible(true));
    }

    public NewNoteEditor() {
        setTitle("Note Editor");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //build menu
        setJMenuBar(editorMenuBar);
        fileMenu.setMnemonic('F');
        formatMenu.setMnemonic('O');
        helpMenu.setMnemonic('H');
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke('N', ActionEvent.CTRL_MASK));
        editorMenuBar.add(fileMenu);
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        editorMenuBar.add(formatMenu);

        Action boldAction = new StyledEditorKit.BoldAction();
        boldAction.putValue(Action.NAME, "Bold");
        boldAction.putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke('B',ActionEvent.CTRL_MASK));
        formatMenu.add(boldAction);
        Action italicAction = new StyledEditorKit.ItalicAction();
        italicAction.putValue(Action.NAME,"Italic");
        italicAction.putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke('I', Event.CTRL_MASK));
        formatMenu.add(italicAction);
        Action underlineAction = new StyledEditorKit.UnderlineAction();
        underlineAction.putValue(Action.NAME,"Underline");
        underlineAction.putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke('U',Event.CTRL_MASK));
        formatMenu.add(underlineAction);
        formatMenu.add(sizeMenu);
        Action smallAction = new StyledEditorKit.FontSizeAction("Small",12);
        smallAction.putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke('S',Event.CTRL_MASK));
        sizeMenu.add(smallAction);
        Action mediumAction = new StyledEditorKit.FontSizeAction("Medium",18);
        mediumAction.putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke('M',Event.CTRL_MASK));
        sizeMenu.add(mediumAction);
        Action largeAction = new StyledEditorKit.FontSizeAction("Large",24);
        largeAction.putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke('L',Event.CTRL_MASK));
        sizeMenu.add(largeAction);
        editorMenuBar.add(helpMenu);
        helpMenu.add(aboutMenuItem);
        newMenuItem.addActionListener((e)->{
            newMenuItemActionPerformed(e);
        });
        openMenuItem.addActionListener((e)->{
            openMenuItemActionPerformed(e);
        });
        saveMenuItem.addActionListener((e)->{
            saveMenuItemActionPerformed(e);
        });
        exitMenuItem.addActionListener((e)->{
            System.exit(0);
        });
        aboutMenuItem.addActionListener((e)->{
            aboutMenuItemActionPerformed(e);
        });

        getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints gridConstraints = new GridBagConstraints();
        editorPane.setPreferredSize(new Dimension(300,150));
        editorPane.setViewportView(editorTextPane);
        editorTextPane.setFont(new Font("Arial",Font.PLAIN,12));
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        getContentPane().add(editorPane,gridConstraints);
        pack();
        setLocationRelativeTo(null);
    }

    private void aboutMenuItemActionPerformed(ActionEvent e) {
        String message = "Note Editor\n\n" +
                "A simple note editor built with Swing JTextPane.\n" +
                "Features: Bold, Italic, Underline, and font size adjustments.\n\n" +
                "Use File > Open to load .txt files and File > Save to write them.";
        JOptionPane.showMessageDialog(this, message, "About Note Editor", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveMenuItemActionPerformed(ActionEvent e) {
        myChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        myChooser.setDialogTitle("Save text file");
        myChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files","txt"));
        if (myChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = myChooser.getSelectedFile();
            String path = file.getAbsolutePath();
            if (!path.toLowerCase().endsWith(".txt")) {
                file = new java.io.File(path + ".txt");
            }

            if (file.exists()) {
                int response = JOptionPane.showConfirmDialog(this,
                        file.toString() + " exists. Overwrite?",
                        "Confirm Save", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response != JOptionPane.YES_OPTION) return;
            }

            try (FileWriter outputFile = new FileWriter(file)) {
                editorTextPane.write(outputFile);
            } catch (IOException ex) {
                JOptionPane.showConfirmDialog(this, ex.getMessage(),
                        "Error Saving File", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void openMenuItemActionPerformed(ActionEvent e) {
        String myLine;
        myChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        myChooser.setDialogTitle("Open text file");
        myChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files","txt"));
        if(myChooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
            try{
                FileReader inputFile = new FileReader(myChooser.getSelectedFile().toString());
                editorTextPane.read(inputFile,null);
                inputFile.close();
            }catch (IOException ex){
                JOptionPane.showConfirmDialog(null,ex.getMessage(),
                        "Error Opening File",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void newMenuItemActionPerformed(ActionEvent e) {
        if(JOptionPane.showConfirmDialog(null,"Are you sure you want to start a new file?",
                "New File",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
            editorTextPane.setText("");
        };
    }

}
