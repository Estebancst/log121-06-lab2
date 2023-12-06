package model;

import java.awt.*;
import java.io.*;
import java.util.Base64;
import java.util.Observable;

public class Perspective extends Observable implements Serializable {
    private Point position;
    private Thumbnail reference;
    private float zoomLevel;

    // Constructeur prenant une miniature de référence en paramètre
    public Perspective(Thumbnail reference) {
        this.position = new Point(0, 0);
        this.zoomLevel = 1.0f;
        this.reference = reference;
    }

    // Méthode pour obtenir la position actuelle de la perspective
    public Point getPosition() {
        return position;
    }

    // Méthode pour définir la position de la perspective et notifier les observateurs
    public void setPosition(Point position) {
        this.position = position;
        setChanged();
        notifyObservers();
    }

    // Méthode pour obtenir le niveau de zoom actuel de la perspective
    public float getZoomLevel() {
        return zoomLevel;
    }

    // Méthode pour définir le niveau de zoom de la perspective et notifier les observateurs
    public void setZoomLevel(float zoom) {
        this.zoomLevel = zoom;
        setChanged();
        this.notifyObservers();
    }

    // Méthode pour obtenir la miniature de référence associée à la perspective
    public Thumbnail getReference() {
        return reference;
    }
}
