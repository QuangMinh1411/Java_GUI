package JTextPane;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class NoteEditor extends JFrame {
    private static final long serialVersionUID = 1L;

    // Menus and items
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu fileMenu = new JMenu("File");
    private final JMenu formatMenu = new JMenu("Format");
    private final JMenu sizeMenu = new JMenu("Size");
    private final JMenu helpMenu = new JMenu("Help");

    private final JMenuItem newMenuItem = new JMenuItem("New");
    private final JMenuItem openMenuItem = new JMenuItem("Open");
    private final JMenuItem saveMenuItem = new JMenuItem("Save");
    private final JMenuItem exitMenuItem = new JMenuItem("Exit");
    private final JMenuItem aboutMenuItem = new JMenuItem("About Note Editor");

    // Editor
    private final JTextPane editorTextPane = new JTextPane();
    private final JScrollPane scrollPane = new JScrollPane(editorTextPane);

    // File chooser
    private final JFileChooser fileChooser = new JFileChooser();

    // Toolbar and buttons
    private final JToolBar toolBar = new JToolBar();
    private final JButton newButton = new JButton(loadIcon("/images/new.png"));
    private final JButton openButton = new JButton(loadIcon("/images/open.png"));
    private final JButton saveButton = new JButton(loadIcon("/images/save.png"));

    // Formatting actions
    private final Action boldAction = createStyledAction(new StyledEditorKit.BoldAction(),
            "Bold", "/images/bold.png", "Bold selected text", KeyEvent.VK_B);
    private final Action italicAction = createStyledAction(new StyledEditorKit.ItalicAction(),
            "Italic", "/images/italic.png", "Italic selected text", KeyEvent.VK_I);
    private final Action underlineAction = createStyledAction(new StyledEditorKit.UnderlineAction(),
            "Underline", "/images/underline.png", "Underline selected text", KeyEvent.VK_U);

    private final Action smallAction = createSizedAction("Small", 12, KeyEvent.VK_S);
    private final Action mediumAction = createSizedAction("Medium", 18, KeyEvent.VK_M);
    private final Action largeAction = createSizedAction("Large", 24, KeyEvent.VK_L);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NoteEditor().setVisible(true));
    }

    public NoteEditor() {
        setTitle("Note Editor");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configure file chooser once
        fileChooser.setDialogTitle("Text file");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

        initMenuBar();
        initToolbar();
        initEditorArea();

        pack();
        setLocationRelativeTo(null);
    }

    private void initMenuBar() {
        setJMenuBar(menuBar);

        fileMenu.setMnemonic('F');
        formatMenu.setMnemonic('O');
        helpMenu.setMnemonic('H');

        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

        newMenuItem.addActionListener(this::newFile);
        openMenuItem.addActionListener(this::openFile);
        saveMenuItem.addActionListener(this::saveFile);
        exitMenuItem.addActionListener(e -> System.exit(0));
        aboutMenuItem.addActionListener(this::showAbout);

        menuBar.add(fileMenu);
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        menuBar.add(formatMenu);
        formatMenu.add(boldAction);
        formatMenu.add(italicAction);
        formatMenu.add(underlineAction);
        formatMenu.add(sizeMenu);

        sizeMenu.add(smallAction);
        sizeMenu.add(mediumAction);
        sizeMenu.add(largeAction);

        menuBar.add(helpMenu);
        helpMenu.add(aboutMenuItem);
    }

    private void initToolbar() {
        toolBar.setFloatable(false);
        // toolBar.setBackground(Color.BLUE); // Keep default LAF background for better integration

        // Provide text fallback when icon is missing
        if (newButton.getIcon() == null) newButton.setText("New");
        if (openButton.getIcon() == null) openButton.setText("Open");
        if (saveButton.getIcon() == null) saveButton.setText("Save");

        newButton.setToolTipText("New file");
        openButton.setToolTipText("Open a text file");
        saveButton.setToolTipText("Save file");

        newButton.addActionListener(this::newFile);
        openButton.addActionListener(this::openFile);
        saveButton.addActionListener(this::saveFile);

        toolBar.add(newButton);
        toolBar.add(openButton);
        toolBar.add(saveButton);
        toolBar.addSeparator();

        toolBar.add(boldAction);
        toolBar.add(italicAction);
        toolBar.add(underlineAction);
    }

    private void initEditorArea() {
        editorTextPane.setFont(new Font("Arial", Font.PLAIN, 12));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(toolBar, BorderLayout.PAGE_START);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void showAbout(ActionEvent e) {
        String message = "Note Editor\n\n" +
                "A simple note editor built with Swing JTextPane.\n" +
                "Features: Bold, Italic, Underline, and font size adjustments.\n\n" +
                "Use File > Open to load .txt files and File > Save to write them.";
        JOptionPane.showMessageDialog(this, message, "About Note Editor", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveFile(ActionEvent e) {
        fileChooser.setDialogTitle("Save text file");
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String path = file.getAbsolutePath();
            if (!path.toLowerCase().endsWith(".txt")) {
                file = new File(path + ".txt");
            }

            if (file.exists()) {
                int response = JOptionPane.showConfirmDialog(this,
                        file + " exists. Overwrite?",
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

    private void openFile(ActionEvent e) {
        fileChooser.setDialogTitle("Open text file");
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (FileReader inputFile = new FileReader(file)) {
                editorTextPane.read(inputFile, null);
            } catch (IOException ex) {
                JOptionPane.showConfirmDialog(this, ex.getMessage(),
                        "Error Opening File", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void newFile(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to start a new file?",
                "New File", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            editorTextPane.setText("");
        }
    }

    private static ImageIcon loadIcon(String resourcePath) {
        URL url = NoteEditor.class.getResource(resourcePath);
        return url != null ? new ImageIcon(url) : null;
    }

    private static Action createStyledAction(Action base, String name, String iconPath, String shortDesc, int acceleratorKey) {
        base.putValue(Action.NAME, name);
        base.putValue(Action.SMALL_ICON, loadIcon(iconPath));
        base.putValue(Action.SHORT_DESCRIPTION, shortDesc);
        base.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(acceleratorKey, InputEvent.CTRL_DOWN_MASK));
        return base;
    }

    private static Action createSizedAction(String name, int size, int acceleratorKey) {
        Action action = new StyledEditorKit.FontSizeAction(name, size);
        action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(acceleratorKey, InputEvent.CTRL_DOWN_MASK));
        return action;
    }
}
