package com.example.demo.entity;

public class Song {
    private String name;
    private String filename;

    public Song(String name, String name1) {
        this.name = name;
        this.filename = name1;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
