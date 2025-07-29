package com.moodifyx;

public class Song{
    private final String title;
    private final String artist;
    private String mood;
    private final String filePath;

    public Song(String title, String artist, String mood, String filePath){
        this.title = title;
        this.artist = artist;
         this.mood = mood;
        this.filePath = filePath;

    }

    public String getTitle(){
        return title;
    }
    public String getArtist(){
        return artist; 
    }

    public String getMood(){
         return mood; 
    }
    
    public String getFilePath(){
        return filePath;
    }

     public String toString() {
        return title + " by " + artist + " [" + mood + "]";
    }
}

    