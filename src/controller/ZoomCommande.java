package controller;

import model.Perspective;

import java.awt.*;

public class ZoomCommande extends Commande{

    private final Point initialPosition;
    private final float initialZoom;
    private final Point position;
    private final float zoom;
    private final Perspective perspective;

    // Constructeur qui initialise les attributs de la commande
    public ZoomCommande(Point initialPosition, float initialZoom, Point newPosition, float newZoom, Perspective perspective) {
        this.initialPosition = initialPosition;
        this.initialZoom = initialZoom;
        this.position = newPosition;
        this.zoom = newZoom;
        this.perspective = perspective;
    }

    // Méthode pour exécuter la commande (met à jour la position et le niveau de zoom dans la perspective)
    @Override
    public void execute() {
        perspective.setPosition(position);
        perspective.setZoomLevel(zoom);
    }

    // Méthode pour annuler la commande (restaure la position et le niveau de zoom initiaux dans la perspective)
    @Override
    public void undo() {
        perspective.setPosition(initialPosition);
        perspective.setZoomLevel(initialZoom);
    }
}
