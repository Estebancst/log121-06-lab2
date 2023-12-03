package controller;

import model.Perspective;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;

public class ImageEventManager extends MouseAdapter implements MouseMotionListener {
    private final int THROTTLE_DELAY_IN_MS = 250;
    private Perspective perspective;
    private boolean isDragging;
    private Timer zoomTimer;
    private Point initialPosition;
    private float initialZoom;
    private Point distanceFromCorner;

    // Constructeur prenant une perspective en paramètre
    public ImageEventManager(Perspective perspective) {
        this.perspective = perspective;
        this.isDragging = false;
        this.zoomTimer = new Timer(THROTTLE_DELAY_IN_MS, e -> saveZoom());
    }

    // Événement déclenché lorsqu'un bouton de la souris est pressé
    @Override
    public void mousePressed(MouseEvent e) {
        isDragging = true;
        distanceFromCorner = new Point(Math.round(e.getX() / perspective.getZoomLevel() - perspective.getPosition().x), Math.round(e.getY() / perspective.getZoomLevel() - perspective.getPosition().y));
        initialPosition = perspective.getPosition();
    }

    // Événement déclenché lorsqu'un bouton de la souris est relâché
    @Override
    public void mouseReleased(MouseEvent e) {
        if (isDragging) {
            Commande moveCommand = CommandeFactory.createMoveCommand(initialPosition, getTranslatedPosition(e.getPoint()), perspective);
            GestionnaireCommandes.getInstance().execute(moveCommand);
        }
        isDragging = false;
    }


    // Événement déclenché lors du mouvement de la molette de la souris
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
            if (!zoomTimer.isRunning()) {
                initialZoom = perspective.getZoomLevel();
                initialPosition = perspective.getPosition();
            }
            float initialZoom = perspective.getZoomLevel();
            double addedZoom = Math.log10(initialZoom);
            if (addedZoom < 0.1f) addedZoom = 0.1f;
            float newZoom = initialZoom - (float) addedZoom * e.getWheelRotation();

            if (newZoom < 0.1f) newZoom = 0.1f;

            perspective.setPosition(getNewZoomPosition(e.getPoint(), initialZoom, newZoom));
            perspective.setZoomLevel(newZoom);
            zoomTimer.restart();
        }
    }

    // Événement déclenché lors du déplacement de la souris (drag-and-drop)
    @Override
    public void mouseDragged(MouseEvent e) {
        if (isDragging) {
            perspective.setPosition(getTranslatedPosition(e.getPoint()));
        }
    }

    // Méthode pour enregistrer le zoom après le throttling
    private void saveZoom() {
        zoomTimer.stop();
        Commande zoomCommand = CommandeFactory.createZoomCommand(initialPosition, initialZoom, perspective);
        GestionnaireCommandes.getInstance().execute(zoomCommand);
    }

    // Méthode pour obtenir la position traduite en fonction du zoom
    private Point getTranslatedPosition(Point mousePosition) {
        return new Point(Math.round((mousePosition.x / perspective.getZoomLevel() - distanceFromCorner.x)), Math.round((mousePosition.y / perspective.getZoomLevel() - distanceFromCorner.y)));
    }

    // Méthode pour obtenir la nouvelle position en fonction du nouveau zoom
    private Point getNewZoomPosition(Point mousePosition, float initialZoom, float newZoom) {
        float initialMousePositionX = (float) perspective.getPosition().x + (float) mousePosition.x / newZoom;
        float initialMousePositionY = (float) perspective.getPosition().y + (float) mousePosition.y / newZoom;
        float newPositionX = initialMousePositionX - (float) mousePosition.x / initialZoom;
        float newPositionY = initialMousePositionY - (float) mousePosition.y / initialZoom;

        return new Point(Math.round(newPositionX), Math.round(newPositionY));
    }
}
