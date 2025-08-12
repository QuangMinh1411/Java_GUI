import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

public class FileTime extends JFrame {
    private final JButton chooseDirButton = new JButton("Choose Directory...");
    private final JTextArea outputArea = new JTextArea();

    public FileTime() {
        super("File Time Analyzer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));
        outputArea.setEditable(false);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(chooseDirButton, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        setPreferredSize(new Dimension(900, 600));

        chooseDirButton.addActionListener(this::onChooseDirectory);

        pack();
        setLocationRelativeTo(null);
    }

    private void onChooseDirectory(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Select a directory to analyze");
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File dir = chooser.getSelectedFile();
            analyzeDirectory(dir);
        }
    }

    private void analyzeDirectory(File dir) {
        if (dir == null || !dir.isDirectory()) {
            outputArea.setText("Please select a valid directory.\n");
            return;
        }
        File[] files = dir.listFiles();
        if (files == null) {
            outputArea.setText("Unable to list files in: " + dir.getAbsolutePath() + "\n");
            return;
        }
        // Filter to regular files only (exclude sub-directories)
        File[] regularFiles = Arrays.stream(files)
                .filter(File::isFile)
                .toArray(File[]::new);

        StringBuilder sb = new StringBuilder();
        DateTimeFormatter fileTimeFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        DateTimeFormatter nowFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.getDefault());

        sb.append("Directory: ").append(dir.getAbsolutePath()).append('\n');
        sb.append("Analyzed at: ").append(ZonedDateTime.now().format(nowFmt)).append('\n');
        sb.append("Total files: ").append(regularFiles.length).append("\n\n");

        if (regularFiles.length == 0) {
            sb.append("No files found to analyze.\n");
            outputArea.setText(sb.toString());
            return;
        }

        // Sort files by name for consistent display
        Arrays.sort(regularFiles, Comparator.comparing(File::getName, String.CASE_INSENSITIVE_ORDER));

        int[] hourCounts = new int[24];

        sb.append("Files and last modified times:\n");
        for (File f : regularFiles) {
            long lm = f.lastModified();
            ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(lm), ZoneId.systemDefault());
            int hour = zdt.getHour();
            hourCounts[hour]++;
            sb.append(String.format(Locale.getDefault(), "%-50s  %s\n", f.getName(), zdt.format(fileTimeFmt)));
        }

        // Determine most popular hour(s)
        int max = 0;
        for (int c : hourCounts) max = Math.max(max, c);
        sb.append("\n");
        if (max <= 0) {
            sb.append("Could not determine popular hours.\n");
        } else {
            sb.append("Most popular modification hour(s): ");
            boolean first = true;
            for (int h = 0; h < 24; h++) {
                if (hourCounts[h] == max) {
                    if (!first) sb.append(", ");
                    sb.append(String.format("%02d:00", h));
                    first = false;
                }
            }
            sb.append(" (" + max + " file" + (max == 1 ? "" : "s") + ")\n\n");
        }

        // Print histogram
        sb.append("Hourly distribution (0-23):\n");
        for (int h = 0; h < 24; h++) {
            sb.append(String.format("%02d: %3d ", h, hourCounts[h]));
            sb.append(bar(hourCounts[h])).append('\n');
        }

        outputArea.setText(sb.toString());
        outputArea.setCaretPosition(0);
    }

    private String bar(int count) {
        if (count <= 0) return "";
        int maxBar = 60; // cap bar length for readability
        int len = Math.min(count, maxBar);
        char[] chars = new char[len];
        Arrays.fill(chars, '#');
        return new String(chars) + (count > maxBar ? "..." : "");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FileTime window = new FileTime();
            window.setVisible(true);
        });
    }
}
