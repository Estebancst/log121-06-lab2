package demarrageApp;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import view.*;

import java.io.*;

public class FenetrePrincipale extends JFrame {

  private static final long serialVersionUID = 1L;
  private static final String TITRE_FENETRE = "Laboratoire 2 : LOG121";
  private static final Dimension DIMENSION = new Dimension(700, 700);
  private VueThumbnail vue1;
  private VuePerspective1 vue2;
  private VuePerspective2 vue3;

    public FenetrePrincipale(String titre) {
      this.vue1 = new VueThumbnail();
      this.vue2 = new VuePerspective1();
      this.vue3 = new VuePerspective2();
      MenuFenetre menuFenetre = new MenuFenetre();

      add(vue1);
      add(vue2);
      add(vue3);
      add(menuFenetre, BorderLayout.NORTH);

      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setTitle(TITRE_FENETRE);
      setSize(DIMENSION);
      setVisible(true);
      setLocationRelativeTo(null);
      setResizable(false);
    }


    public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          new FenetrePrincipale("LAB2");
        }
      });
    }
}
