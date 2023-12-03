package model;

import java.awt.*;
import java.io.Serializable;
import java.util.Observable;

public class Thumbnail extends Observable implements Serializable {
    private transient Image image;
    public Thumbnail() {

    }

    // Méthode pour obtenir l'image associée au Thumbnail
    public Image getImage() {
        return image;
    }

    // Méthode pour définir l'image du Thumbnail et notifier les observateurs
    public void setImage(Image image) {
        this.image = image;
        this.setChanged();
        this.notifyObservers();
    }
}
