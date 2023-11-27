package view;

import controller.ImageEventManager;
import model.Perspective;

import java.awt.*;
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class VuePerspectiveDroite extends JPanel implements Observer {
    //Panneau du milieu qui contient l'image et les boutons de commandes
    private ImageEventManager eventManager;
    private Perspective perspective;

    public VuePerspectiveDroite(Perspective perspective) {
        this.perspective = perspective;
        this.eventManager = new ImageEventManager(perspective);
        this.addMouseListener(eventManager);
        this.addMouseWheelListener(eventManager);
        this.addMouseMotionListener(eventManager);
    }
    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.scale(perspective.getZoomLevel(), perspective.getZoomLevel());
        g2d.drawImage(perspective.getReference().getImage(), perspective.getPosition().x, perspective.getPosition().y, this);
    }
}
