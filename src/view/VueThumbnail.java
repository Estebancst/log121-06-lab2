package view;

import model.Thumbnail;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Observable;
import java.util.Observer;

public class VueThumbnail extends JPanel implements Observer {
    private Thumbnail thumbnail;

    public VueThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(thumbnail.getImage(), 0, 0, this);
    }
}
