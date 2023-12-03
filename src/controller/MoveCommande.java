package controller;

import model.Perspective;

import java.awt.*;

public class MoveCommande extends Commande {
    private final Point initialPosition;
    private final Point position;
    private final Perspective perspective;

    // Constructeur qui initialise les attributs de la commande
    public MoveCommande(Point initialPosition, Point newPosition, Perspective perspective) {
        this.initialPosition = initialPosition;
        this.position = newPosition;
        this.perspective = perspective;
    }

    // Méthode pour exécuter la commande (met à jour la position dans la perspective)
    @Override
    public void execute() {
        perspective.setPosition(position);
    }

    // Méthode pour annuler la commande (restaure la position initiale dans la perspective)
    @Override
    public void undo() {
        perspective.setPosition(initialPosition);
    }
}
