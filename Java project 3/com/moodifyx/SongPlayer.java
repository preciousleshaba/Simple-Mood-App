package com.moodifyx;

import javax.sound.sampled.*;
import java.io.File;

public class SongPlayer {
    private Clip clip;

    public void play(Song song) {
        try {
            if (clip != null && clip.isRunning()) clip.stop();
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(song.getFilePath()));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}

