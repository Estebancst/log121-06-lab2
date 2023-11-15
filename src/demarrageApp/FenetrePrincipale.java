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

    public FenetrePrincipale() {
      this.vue1 = new VueThumbnail();
      this.vue2 = new VuePerspective1();
      this.vue3 = new VuePerspective2();
      MenuFenetre menuFenetre = new MenuFenetre();
      setJMenuBar(menuFenetre);


      // Définir le layout des panneaux comme BoxLayout pour les aligner verticalement
      vue1.setLayout(new BoxLayout(vue1, BoxLayout.Y_AXIS));
      vue2.setLayout(new BoxLayout(vue2, BoxLayout.Y_AXIS));
      vue3.setLayout(new BoxLayout(vue3, BoxLayout.Y_AXIS));

      // Utiliser un GridLayout pour les panneaux
      JPanel panelContainer = new JPanel(new GridLayout(1, 3));
      panelContainer.add(vue1);
      panelContainer.add(vue2);
      panelContainer.add(vue3);

      // Ajouter les panneaux au conteneur principal
      getContentPane().setLayout(new BorderLayout());
      getContentPane().add(panelContainer, BorderLayout.CENTER);

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
          new FenetrePrincipale();
        }
      });
    }
}