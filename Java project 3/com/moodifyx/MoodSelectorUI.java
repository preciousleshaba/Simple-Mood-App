package com.moodifyx;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class MoodSelectorUI {

    private static JFrame frame;
    private static JLabel imageLabel;
    private static JLabel songInfoLabel;

    private static JButton btnChangeMood;
    private static JButton btnRecommendMood;
    private static JButton btnStop;

    private static MoodRepository repo;
    private static SongPlayer player;

    private static String currentMood;
    private static List<Song> currentSongs;
    private static int currentSongIndex = 0;

    public static void main(String[] args) {
        repo = new MoodRepository();
        player = new SongPlayer();

        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Mood Music App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        btnChangeMood = new JButton(" Moods");
        btnRecommendMood = new JButton("Mood Recommendation");
        btnStop = new JButton("Stop");

        topPanel.add(btnChangeMood);
        topPanel.add(btnRecommendMood);
        topPanel.add(btnStop);

        frame.add(topPanel, BorderLayout.NORTH);

        imageLabel = new JLabel("", SwingConstants.CENTER);
        frame.add(imageLabel, BorderLayout.CENTER);

        songInfoLabel = new JLabel("Select a mood to start", SwingConstants.CENTER);
        songInfoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(songInfoLabel, BorderLayout.SOUTH);

        btnChangeMood.addActionListener(_ -> showMoodSelectionDialog());
        btnRecommendMood.addActionListener(_ -> showMoodRecommendation());
        btnStop.addActionListener(_ -> {
            player.stop();
            songInfoLabel.setText("Playback stopped.");
            System.out.println("Playback stopped.");
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void showMoodSelectionDialog() {
        List<String> moods = new ArrayList<>(repo.getAllMoods());
        String selectedMood = (String) JOptionPane.showInputDialog(
            frame,
            "Select a mood:",
            "Mood Selector",
            JOptionPane.PLAIN_MESSAGE,
            null,
            moods.toArray(),
            currentMood != null ? currentMood : moods.get(0)
        );

        if (selectedMood != null && !selectedMood.equals(currentMood)) {
            currentMood = selectedMood;
            currentSongs = repo.getSongsByMood(currentMood);
            currentSongIndex = 0;
            updateMoodAndSong();
        }
    }

    private static void showMoodRecommendation() {
        List<String> moods = new ArrayList<>(repo.getAllMoods());
        moods.remove(currentMood);
        if (moods.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No other moods to recommend.");
            return;
        }
        int randIndex = (int) (Math.random() * moods.size());
        currentMood = moods.get(randIndex);
        currentSongs = repo.getSongsByMood(currentMood);
        currentSongIndex = 0;
        updateMoodAndSong();
    }

    private static void updateMoodAndSong() {
        if (currentSongs == null || currentSongs.isEmpty()) {
            songInfoLabel.setText("No songs found for mood: " + currentMood);
            imageLabel.setIcon(null);
            imageLabel.setText("No image");
            player.stop();
            System.out.println("No songs found for mood: " + currentMood);
            return;
        }

        String filename = "Images/" + currentMood.toLowerCase() + ".png";
        File imgFile = new File(filename);
        if (imgFile.exists()) {
            ImageIcon icon = new ImageIcon(filename);
            Image scaled = icon.getImage().getScaledInstance(600, 350, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaled));
            imageLabel.setText(null);
        } else {
            imageLabel.setIcon(null);
            imageLabel.setText("No image found for mood: " + currentMood);
            System.out.println("No image found for mood: " + currentMood);
        }

        updateSongInfoAndPlay();
    }

    private static void updateSongInfoAndPlay() {
        Song currentSong = currentSongs.get(currentSongIndex);
        String infoHtml = "<html><center>Now Playing:<br>" +
            "Song: " + currentSong.getTitle() + "<br>" +
            "Artist: " + currentSong.getArtist() + "<br>" +
            "Mood: " + currentSong.getMood() + "</center></html>";

        songInfoLabel.setText(infoHtml);

        System.out.println("Now Playing:");
        System.out.println("Song: " + currentSong.getTitle());
        System.out.println("Artist: " + currentSong.getArtist());
        System.out.println("Mood: " + currentSong.getMood());

        player.stop();
        player.play(currentSong);
    }
}
