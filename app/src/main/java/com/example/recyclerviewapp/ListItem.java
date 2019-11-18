package com.example.recyclerviewapp;

public class ListItem {
    private String naslov;
    private String opis;
    private String autor;
    private String datum;
    private String url;
    private String urlToImage;


    public ListItem(String naslov, String opis, String autor, String datum, String url, String urlToImage) {
        this.naslov = naslov;
        this.opis = opis;
        this.autor = autor;
        this.datum = datum;
        this.url = url;
        this.urlToImage = urlToImage;
    }

    public String getNaslov() {
        return naslov;
    }

    public String getDatum() {
        return datum;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getOpis() {
        return opis;
    }

    public String getAutor() {
        return autor;
    }
}
