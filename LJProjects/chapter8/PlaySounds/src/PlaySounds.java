import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class PlaySounds extends JFrame {
    private final JFileChooser soundChooser = new JFileChooser();
    private Clip currentClip;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PlaySounds().setVisible(true);
        });
    }

    public PlaySounds(){
        setTitle("Play Sounds");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        soundChooser.setDialogTitle("Select a sound file");
        soundChooser.addChoosableFileFilter(new FileNameExtensionFilter("Sound Files (*.wav, *.au, *.aiff, *.mp3)", "wav", "mp3", "au", "aiff"));
        soundChooser.setAcceptAllFileFilterUsed(true);
        c.gridx = 0;
        c.gridy = 0;
        getContentPane().add(soundChooser, c);
        soundChooser.addActionListener(this::soundChooserActionPerformed);

        pack();
        setLocationRelativeTo(null);
    }

    private void soundChooserActionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
            File selected = soundChooser.getSelectedFile();
            if (selected != null) {
                playSelectedFile(selected);
            }
        }else{
            System.exit(0);
        }
    }

    private void playSelectedFile(File file) {
        String name = file.getName().toLowerCase();

        // javax.sound.sampled supports WAV/AU/AIFF by default. MP3 requires extra libraries.
        if (name.endsWith(".mp3")) {
            JOptionPane.showMessageDialog(this,
                    "MP3 is not supported by the standard Java sound API.\nUse WAV/AU/AIFF, or add an MP3 plugin/library.",
                    "Unsupported format",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            stopCurrentClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            currentClip = AudioSystem.getClip();
            currentClip.open(ais);
            currentClip.start();
        } catch (UnsupportedAudioFileException ex) {
            JOptionPane.showMessageDialog(this,
                    "Unsupported audio file: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (LineUnavailableException ex) {
            JOptionPane.showMessageDialog(this,
                    "Audio line unavailable: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "I/O error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void stopCurrentClip() {
        if (currentClip != null) {
            try {
                if (currentClip.isRunning()) currentClip.stop();
                currentClip.close();
            } finally {
                currentClip = null;
            }
        }
    }
}
