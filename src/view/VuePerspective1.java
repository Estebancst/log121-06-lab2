package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class VuePerspective1 extends JPanel {
    //Panneau du milieu qui contient l'image et les boutons de commandes

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
