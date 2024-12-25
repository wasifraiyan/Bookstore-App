package com.example.bookstoreapp;

public class BookModel {
    private String title;
    private String author;
    private String description;
    private int coverImage; // Drawable resource ID
    private String price;
    private boolean isAvailable;

    // Constructor
    public BookModel(String title, String author, String description, int coverImage, String price, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.coverImage = coverImage;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public int getCoverImage() {
        return coverImage; // This is the method to use
    }

    public String getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
