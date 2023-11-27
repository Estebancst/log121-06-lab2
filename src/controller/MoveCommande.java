package controller;

import model.Perspective;

import java.awt.*;

public class MoveCommande extends Commande {
    private final Point initialPosition;
    private final Point position;
    private final Perspective perspective;

    public MoveCommande(Point initialPosition, Point newPosition, Perspective perspective) {
        this.initialPosition = initialPosition;
        this.position = newPosition;
        this.perspective = perspective;
    }
    @Override
    public void execute() {
        perspective.setPosition(position);
    }

    @Override
    public void undo() {
        perspective.setPosition(initialPosition);
    }
}
