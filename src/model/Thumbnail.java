package model;

import java.awt.*;
import java.io.Serializable;
import java.util.Observable;

public class Thumbnail extends Observable implements Serializable {
    private transient Image image;
    public Thumbnail() {

    }
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        this.setChanged();
        this.notifyObservers();
    }
}
