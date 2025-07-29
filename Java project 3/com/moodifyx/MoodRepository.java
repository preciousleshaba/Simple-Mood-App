package com.moodifyx;

import java.util.*;

public class MoodRepository {
    private final List<Song> songs = new ArrayList<>();

    public MoodRepository() {
        loadSongs();
    }
   
    private void loadSongs() {
       songs.add(new Song("Simple Happy Life", "Grand_Project", "Happy", "Music\\simple-happy-life-353819.wav"));
       songs.add(new Song("Sad Documentary Piano", "SigmaMusicArt", "Sad", "Music\\sad-documentary-background-music-365827.wav"));
       songs.add(new Song("Cinematic Action Trailer", "SigmaMusicArt", "Angry", "Music\\cinematic-action-trailer-376361.wav"));
       songs.add(new Song("Energetic Upbeat Background", "HitsLab", "Energetic", "Music\\energetic-upbeat-background-music-333747.wav"));
       songs.add(new Song("Soft Background Music", "SigmaMusicArt", "Relaxed", "Music\\soft-background-music-368633.wav"));
       songs.add(new Song("Background Music Upbeat", "DELOSound", "Focused", "Music\\corporate-background-music-346481.wav"));
       songs.add(new Song("Playful Kids Background", "AudioCoffee", "Sleepy", "Music\\playful-kids-135537.wav"));
       songs.add(new Song("white reflection", "Saavane", "Romantic", "Music\\white-reflection-uplifting-background-track-157673.wav"));
       songs.add(new Song("Happy Kids Background", "SigmaMusicArt", "Nostalgic", "Music\\happy-kids-music-background-378309.wav"));
       songs.add(new Song("Uplifting Motivational", "HitsLab", "Confident", "Music\\cinematic-inspirational-background-music-355281.wav"));

    }
      public List<Song> getSongsByMood(String mood) {
        List<Song> result = new ArrayList<>();
        for (Song song : songs) {
            if (song.getMood().equalsIgnoreCase(mood)) {
                result.add(song);  
    }

    }
    return result;
    }

    public Set<String> getAllMoods() {
        Set<String> moods = new HashSet<>();
        for (Song song : songs) {
            moods.add(song.getMood());
        }
        return moods;
    }
    }
  

        
    
            
           
        
    
