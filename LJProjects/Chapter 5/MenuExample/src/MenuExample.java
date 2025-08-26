import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;

public class MenuExample extends JFrame {
    private JMenuBar exampleMenuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenu editMenu = new JMenu("Edit");
    private JMenu formatMenu = new JMenu("Format");
    private JMenuItem newMenuItem = new JMenuItem("New");
    private JMenuItem openMenuItem = new JMenuItem("Open");
    private JMenuItem saveMenuItem = new JMenuItem("Save");
    private JMenuItem exitMenuItem = new JMenuItem("Exit");
    private JMenuItem cutMenuItem = new JMenuItem("Cut");
    private JMenuItem copyMenuItem = new JMenuItem("Copy");
    private JMenuItem pasteMenuItem = new JMenuItem("Paste");
    private JCheckBoxMenuItem boldMenuItem = new JCheckBoxMenuItem("Bold",false);
    private JCheckBoxMenuItem italicMenuItem = new JCheckBoxMenuItem("Italic",false);
    private JMenu sizeMenu = new JMenu("Size");
    ButtonGroup sizeGroup = new ButtonGroup();
    JRadioButtonMenuItem size10MenuItem = new JRadioButtonMenuItem("10",true);
    JRadioButtonMenuItem size15MenuItem = new JRadioButtonMenuItem("15",false);
    JRadioButtonMenuItem size20MenuItem = new JRadioButtonMenuItem("20",false);
    public MenuExample() {
        setTitle("Menu Example");
        setSize(400,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(exampleMenuBar);
        exampleMenuBar.add(fileMenu);
        fileMenu.add(newMenuItem);
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke('N', Event.CTRL_MASK));
        fileMenu.add(openMenuItem);
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_MASK));
        fileMenu.add(saveMenuItem);
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK));
        fileMenu.addSeparator();

        fileMenu.add(exitMenuItem);
        exampleMenuBar.add(editMenu);
        editMenu.add(cutMenuItem);
        cutMenuItem.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK));
        editMenu.add(copyMenuItem);
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
        editMenu.add(pasteMenuItem);
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK));

        exampleMenuBar.add(formatMenu);
        formatMenu.add(boldMenuItem);
        boldMenuItem.setAccelerator(KeyStroke.getKeyStroke('B', InputEvent.CTRL_MASK));
        formatMenu.add(italicMenuItem);
        italicMenuItem.setAccelerator(KeyStroke.getKeyStroke('I', InputEvent.CTRL_MASK));
        formatMenu.add(sizeMenu);
        sizeMenu.add(size10MenuItem);
        size10MenuItem.setAccelerator(KeyStroke.getKeyStroke('0', InputEvent.CTRL_MASK));
        sizeMenu.add(size15MenuItem);
        size15MenuItem.setAccelerator(KeyStroke.getKeyStroke('1', InputEvent.CTRL_MASK));
        sizeMenu.add(size20MenuItem);
        size20MenuItem.setAccelerator(KeyStroke.getKeyStroke('2', InputEvent.CTRL_MASK));
        sizeGroup.add(size10MenuItem);
        sizeGroup.add(size15MenuItem);
        sizeGroup.add(size20MenuItem);

        fileMenu.setMnemonic('F');
        editMenu.setMnemonic('E');
        formatMenu.setMnemonic('O');
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new MenuExample().setVisible(true);
        });
    }
}
