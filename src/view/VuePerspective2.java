package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class VuePerspective2 extends JPanel {
    //Panneau de droite qui contient l'image et les boutons de commandes
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
