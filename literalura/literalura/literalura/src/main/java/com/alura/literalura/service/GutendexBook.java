package com.alura.literalura.service;

import java.util.List;

public class GutendexBook {
    private int id;
    private String title;
    private List<GutendexAuthor> authors;
    private List<String> languages;
    private int downloadCount;

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<GutendexAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<GutendexAuthor> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }
}
