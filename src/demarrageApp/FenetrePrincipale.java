package demarrageApp;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import controller.Commande;
import controller.CommandeFactory;
import controller.GestionnaireCommandes;
import model.Perspective;
import model.Thumbnail;
import view.*;

import java.awt.image.BufferedImage;
import java.io.*;

public class FenetrePrincipale extends JFrame {;

  private static final long serialVersionUID = 1L;
  private static final String TITRE_FENETRE = "Laboratoire 2 : LOG121";
  private static final String MENU_FICHIER_TITRE = "Fichier";
  private static final String MENU_EDITION_TITRE = "Édition";
  private static final String MENU_OUVRIR_IMAGE = "Ouvrir une image";
  private static final String MENU_ENREGISTRER_PERSPECTIVE = "Enregistrer perspective";
  private static final String MENU_CHARGER_PERSPECTIVE = "Charger perspective";
  private static final String MENU_FICHIER_QUITTER = "Quitter";
  private static final String MENU_DEFAIRE_PERSPECTIVE = "Défaire";
  private static final String MENU_REFAIRE_PERSPECTIVE = "Refaire";
  private static final Dimension DIMENSION = new Dimension(900, 700);
  private VueThumbnail vueThumbnail;
  private VuePerspective vuePerspectiveGauche;
  private VuePerspective vuePerspectiveDroite;
  private Thumbnail thumbnail;
  private final Perspective perspectiveGauche;
  private final Perspective perspectiveDroite;
    public FenetrePrincipale() {

      // Create a menu bar
      JMenuBar menuBar = new JMenuBar();

      // Add the file menu to the menu bar
      menuBar.add(ajouterMenuFichier());
      menuBar.add(ajouterMenuEdition());

      // Set the menu bar for the frame
      setJMenuBar(menuBar);

      thumbnail = new Thumbnail();
      this.vueThumbnail = new VueThumbnail(thumbnail);
      thumbnail.addObserver(vueThumbnail);

      perspectiveGauche = new Perspective(thumbnail);
      this.vuePerspectiveGauche = new VuePerspective(perspectiveGauche);
      thumbnail.addObserver(vuePerspectiveGauche);
      perspectiveGauche.addObserver(vuePerspectiveGauche);

      perspectiveDroite = new Perspective(thumbnail);
      this.vuePerspectiveDroite = new VuePerspective(perspectiveDroite);
      thumbnail.addObserver(vuePerspectiveDroite);
      perspectiveDroite.addObserver(vuePerspectiveDroite);

      // Définir le layout des panneaux comme BoxLayout pour les aligner verticalement
      vueThumbnail.setLayout(new BoxLayout(vueThumbnail, BoxLayout.Y_AXIS));
      vuePerspectiveGauche.setLayout(new BoxLayout(vuePerspectiveGauche, BoxLayout.Y_AXIS));
      vuePerspectiveDroite.setLayout(new BoxLayout(vuePerspectiveDroite, BoxLayout.Y_AXIS));

      // Utiliser un GridLayout pour les panneaux
      JPanel panelContainer = new JPanel(new GridLayout(1, 3));
      panelContainer.add(vueThumbnail);
      panelContainer.add(vuePerspectiveGauche);
      panelContainer.add(vuePerspectiveDroite);

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

  private JMenu ajouterMenuFichier() {
    JMenu menuFichier = new JMenu(MENU_FICHIER_TITRE);
    JMenuItem menuOuvrirImage = new JMenuItem(MENU_OUVRIR_IMAGE);
    JMenuItem menuEnregistrerPerspective = new JMenuItem(MENU_ENREGISTRER_PERSPECTIVE);
    JMenuItem menuChargerPerspective = new JMenuItem(MENU_CHARGER_PERSPECTIVE);
    JMenuItem menuQuitter = new JMenuItem(MENU_FICHIER_QUITTER);

    // Ouvrir image thumbnail
    menuOuvrirImage.addActionListener((ActionEvent e) -> {
      JFileChooser fileChooser = new JFileChooser();
      int returnValue = fileChooser.showOpenDialog(FenetrePrincipale.this);

      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        loadImage(file);
      }
    });

    // Enregistrer la perspective
    menuEnregistrerPerspective.addActionListener((ActionEvent e) -> {
      JFileChooser fileChooser = new JFileChooser();
      int returnValue = fileChooser.showSaveDialog(FenetrePrincipale.this);

      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        savePerspective(perspectiveGauche, file);
        savePerspective(perspectiveDroite, file);
      }
    });


    // Charger la perspective
    menuChargerPerspective.addActionListener((ActionEvent e) -> {
      JFileChooser fileChooser = new JFileChooser();
      int returnValue = fileChooser.showOpenDialog(FenetrePrincipale.this);

      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        Perspective loadedPerspective = loadPerspective(file);
        if (loadedPerspective != null) {
          // Do something with the loaded perspective, e.g., update the UI
          perspectiveGauche.setPosition(loadedPerspective.getPosition());
          perspectiveGauche.setZoomLevel(loadedPerspective.getZoomLevel());
          perspectiveDroite.setPosition(loadedPerspective.getPosition());
          perspectiveDroite.setZoomLevel(loadedPerspective.getZoomLevel());
        }
      }
    });

    menuQuitter.addActionListener((ActionEvent e) -> {
      System.exit(0);
    });

    menuFichier.add(menuOuvrirImage);
    menuFichier.add(menuEnregistrerPerspective);
    menuFichier.add(menuChargerPerspective);
    menuFichier.addSeparator();
    menuFichier.add(menuQuitter);
    add(menuFichier);
    return menuFichier;
  }

  private JMenu ajouterMenuEdition() {
    JMenu menuEdit = new JMenu(MENU_EDITION_TITRE);
    JMenuItem menuDefaire = new JMenuItem(MENU_DEFAIRE_PERSPECTIVE);
    JMenuItem menuRefaire = new JMenuItem(MENU_REFAIRE_PERSPECTIVE);

    // Undo
    menuDefaire.addActionListener(e -> GestionnaireCommandes.getInstance().undo());
    // Redo
    menuRefaire.addActionListener(e -> GestionnaireCommandes.getInstance().redo());

    menuEdit.add(menuDefaire);
    menuEdit.addSeparator();
    menuEdit.add(menuRefaire);
    add(menuEdit);
    return menuEdit;
  }

  private void loadImage(File file) {
    BufferedImage newImage;
    try {
      newImage = ImageIO.read(file);

      // Get the dimensions of vue1
      int targetWidth = vueThumbnail.getWidth();
      int targetHeight = vueThumbnail.getHeight();

      // Calculate scaling factors
      double scaleX = (double) targetWidth / newImage.getWidth();
      double scaleY = (double) targetHeight / newImage.getHeight();

      // Choose the minimum scaling factor to maintain the image's aspect ratio
      double scale = Math.min(scaleX, scaleY);

      // Calculate the position to center the scaled image
      int x = (int) ((targetWidth - scale * newImage.getWidth()) / 2);
      int y = (int) ((targetHeight - scale * newImage.getHeight()) / 2);

      // Create a new BufferedImage and scale the image
      BufferedImage scaledBufferedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g = scaledBufferedImage.createGraphics();

      // Draw the scaled image at the calculated position
      g.drawImage(newImage.getScaledInstance((int) (newImage.getWidth() * scale), (int) (newImage.getHeight() * scale), Image.SCALE_SMOOTH), x, y, null);
      g.dispose();

      // Update the Thumbnail with the scaled image
      Commande command = CommandeFactory.createLoadImageCommand(thumbnail, scaledBufferedImage);
      GestionnaireCommandes.getInstance().execute(command);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, String.format("Couldn't load image %s", file.getAbsolutePath()));
    }
  }

  private void savePerspective(Perspective perspective, File file) {
    try (PrintWriter writer = new PrintWriter(file)) {
      writer.write(perspective.serialize());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private Perspective loadPerspective(File file) {
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      StringBuilder stringBuilder = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        stringBuilder.append(line);
      }
      return Perspective.deserialize(stringBuilder.toString());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}