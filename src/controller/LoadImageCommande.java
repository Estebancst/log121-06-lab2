package controller;

import model.Thumbnail;

import java.awt.*;

public class LoadImageCommande extends Commande {
    private final Image previousImage;
    private final Image newImage;
    private final Thumbnail thumbnail;

    public LoadImageCommande(Image previousImage, Image newImage, Thumbnail thumbnail) {
        this.previousImage = previousImage;
        this.newImage = newImage;
        this.thumbnail = thumbnail;
    }

    @Override
    public void execute() {
        thumbnail.setImage(newImage);
    }

    @Override
    public void undo() {
        thumbnail.setImage(previousImage);
    }
}
