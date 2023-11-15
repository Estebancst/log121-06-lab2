package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class VueThumbnail extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
