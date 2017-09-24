package br.fxd.com.fxd.model;

/**
 * Created by matheuscatossi on 24/09/17.
 */

public class EWallet {

    int id;
    int image;
    String title;
    String description;
    String date;

    public EWallet(int id, int image, String title, String description, String date) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public EWallet(int image, String title, String description, String date) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
