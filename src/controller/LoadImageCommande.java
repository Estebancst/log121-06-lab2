package controller;

import model.Thumbnail;

import java.awt.*;

public class LoadImageCommande extends Commande {
    private final Image previousImage;
    private final Image newImage;
    private final Thumbnail thumbnail;

    // Constructeur qui initialise les attributs de la commande
    public LoadImageCommande(Image previousImage, Image newImage, Thumbnail thumbnail) {
        this.previousImage = previousImage;
        this.newImage = newImage;
        this.thumbnail = thumbnail;
    }

    // Méthode pour exécuter la commande (met à jour l'image de la miniature)
    @Override
    public void execute() {
        thumbnail.setImage(newImage);
    }

    // Méthode pour annuler la commande (restaure l'image précédente à la miniature)
    @Override
    public void undo() {
        thumbnail.setImage(previousImage);
    }
}
