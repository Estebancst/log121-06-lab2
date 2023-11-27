package controller;

import model.Perspective;

import java.awt.*;

public class ZoomCommande extends Commande{

    private final Point initialPosition;
    private final float initialZoom;
    private final Point position;
    private final float zoom;
    private final Perspective perspective;

    public ZoomCommande(Point initialPosition, float initialZoom, Point newPosition, float newZoom, Perspective perspective) {
        this.initialPosition = initialPosition;
        this.initialZoom = initialZoom;
        this.position = newPosition;
        this.zoom = newZoom;
        this.perspective = perspective;
    }

    @Override
    public void execute() {
        perspective.setPosition(position);
        perspective.setZoomLevel(zoom);
    }

    @Override
    public void undo() {
        perspective.setPosition(initialPosition);
        perspective.setZoomLevel(initialZoom);
    }
}
