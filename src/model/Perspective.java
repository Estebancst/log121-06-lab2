package model;

import java.awt.*;
import java.io.*;
import java.util.Base64;
import java.util.Observable;

public class Perspective extends Observable implements Serializable {
    private Point position;
    private Thumbnail reference;
    private float zoomLevel;

    public Perspective(Thumbnail reference) {
        this.position = new Point(0, 0);
        this.zoomLevel = 1.0f;
        this.reference = reference;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
        setChanged();
        notifyObservers();
    }

    public float getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(float zoom) {
        this.zoomLevel = zoom;
        setChanged();
        this.notifyObservers();
    }

    public Thumbnail getReference() {
        return reference;
    }

    public String serialize() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            oos.close();
            return Base64.getEncoder().encodeToString(bos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Perspective deserialize(String serializedData) {
        try {
            byte[] data = Base64.getDecoder().decode(serializedData);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            Perspective perspective = (Perspective) ois.readObject();
            ois.close();
            return perspective;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
