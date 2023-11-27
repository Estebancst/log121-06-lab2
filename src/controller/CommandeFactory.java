package controller;

import model.Perspective;
import model.Thumbnail;

import java.awt.*;

public class CommandeFactory {
    public static Commande createLoadImageCommand(Thumbnail thumbnail, Image image) {
        return new LoadImageCommande(thumbnail.getImage(), image, thumbnail);
    }
    public static Commande createMoveCommand(Point initialPosition, Point newPosition, Perspective perspective) {
        return new MoveCommande(initialPosition, newPosition, perspective);
    }
    public static Commande createZoomCommand(Point position, float zoom, Perspective perspective) {
        return new ZoomCommande(position, zoom, perspective.getPosition(), perspective.getZoomLevel(), perspective);
    }
}