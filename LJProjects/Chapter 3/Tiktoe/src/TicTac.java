import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TicTac extends JFrame {
    private static final int SIZE = 5; // grid size (5x5)
    private static final int WIN_LENGTH = 3; // number in a row to win (classic Tic-Tac-Toe is 3)

    private JPanel gamePanel = new JPanel();
    private JPanel controlPanel = new JPanel();
    private JLabel[][] cells = new JLabel[SIZE][SIZE];
    private char[][] board = new char[SIZE][SIZE]; // 'X', 'O', or '\0' for empty

    private ImageIcon X = new ImageIcon("src/resources/images/x.gif");
    private ImageIcon O = new ImageIcon("src/resources/images/o.gif");
    private JLabel Xplayer = new JLabel();
    private JLabel Oplayer = new JLabel();
    private boolean isX = false; // false -> O starts (as per original highlighting)

    public TicTac() {
        setTitle("TicTacToe Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        getContentPane().setLayout(new GridBagLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width -
                getWidth())), (int) (0.5 * (screenSize.height -
                getHeight())), getWidth(), getHeight());
        GridBagConstraints gridConstraints;
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        //Game panel
        gamePanel.setLayout(new GridBagLayout());
        gamePanel.setPreferredSize(new Dimension(500,500));
        gamePanel.setBackground(Color.WHITE);
        //Cells
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = new JLabel();
                cells[i][j].setBorder(blackBorder);
                cells[i][j].setPreferredSize(new Dimension(50, 50));
                gridConstraints = new GridBagConstraints();
                gridConstraints.gridx = i;
                gridConstraints.gridy = j;
                gamePanel.add(cells[i][j], gridConstraints);
                cells[i][j].addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        playerClick(e);
                    }
                });
                board[i][j] = '\0';
            }
        }


        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        getContentPane().add(gamePanel, gridConstraints);

        //Control Panel
        controlPanel.setLayout(new GridBagLayout());
        controlPanel.setBackground(Color.GRAY);
        Xplayer.setText("X");
        gridConstraints = new GridBagConstraints();
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        controlPanel.add(Xplayer, gridConstraints);
        Oplayer.setText("O");
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        controlPanel.add(Oplayer, gridConstraints);


        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        getContentPane().add(controlPanel, gridConstraints);
        pack();
        if(isX){
            Xplayer.setForeground(Color.red);
        }else{
            Oplayer.setForeground(Color.red);
        }

    }

    private void playerClick(MouseEvent e) {
        Component component = e.getComponent();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if ((JLabel) component == cells[i][j] && cells[i][j].getIcon() == null) {
                    Image icon;
                    char mark;
                    if(isX){
                        icon = X.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
                        mark = 'X';
                        Xplayer.setForeground(Color.red);
                        Oplayer.setForeground(Color.gray);
                    }else{
                        icon = O.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
                        mark = 'O';
                        Oplayer.setForeground(Color.red);
                        Xplayer.setForeground(Color.gray);
                    }

                    cells[i][j].setIcon(new ImageIcon(icon));
                    board[i][j] = mark;

                    // Check for win or draw after placing the mark
                    if (isWinningMove(i, j, mark)) {
                        JOptionPane.showMessageDialog(this, mark + " wins!");
                        resetBoard();
                        return;
                    } else if (isBoardFull()) {
                        JOptionPane.showMessageDialog(this, "It's a draw!");
                        resetBoard();
                        return;
                    }

                    isX = !isX;
                }
            }
        }

    }

    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '\0') return false;
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j].setIcon(null);
                board[i][j] = '\0';
            }
        }
        // Reset turn to O (to keep original behavior where O was highlighted initially)
        isX = false;
        Oplayer.setForeground(Color.red);
        Xplayer.setForeground(Color.gray);
    }

    // Check if placing 'mark' at (row, col) creates a WIN_LENGTH in a row in any direction
    private boolean isWinningMove(int row, int col, char mark) {
        return countConsecutive(row, col, 1, 0, mark) + countConsecutive(row, col, -1, 0, mark) - 1 >= WIN_LENGTH // horizontal
                || countConsecutive(row, col, 0, 1, mark) + countConsecutive(row, col, 0, -1, mark) - 1 >= WIN_LENGTH // vertical
                || countConsecutive(row, col, 1, 1, mark) + countConsecutive(row, col, -1, -1, mark) - 1 >= WIN_LENGTH // main diagonal
                || countConsecutive(row, col, 1, -1, mark) + countConsecutive(row, col, -1, 1, mark) - 1 >= WIN_LENGTH; // anti-diagonal
    }

    private int countConsecutive(int row, int col, int dRow, int dCol, char mark) {
        int r = row;
        int c = col;
        int count = 0;
        while (r >= 0 && r < SIZE && c >= 0 && c < SIZE && board[r][c] == mark) {
            count++;
            r += dRow;
            c += dCol;
        }
        return count;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTac window = new TicTac();
            window.setVisible(true);
        });
    }
}
