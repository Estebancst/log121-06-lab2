package demarrageApp;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import controller.Commande;
import controller.CommandeFactory;
import controller.GestionnaireCommandes;
import model.EnsemblePerspectives;
import model.Perspective;
import model.Thumbnail;
import view.*;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

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

      thumbnail = new Thumbnail();
      vueThumbnail = new VueThumbnail(thumbnail);
      thumbnail.addObserver(vueThumbnail);

      perspectiveGauche = new Perspective(thumbnail);
      vuePerspectiveGauche = new VuePerspective(perspectiveGauche);
      thumbnail.addObserver(vuePerspectiveGauche);
      perspectiveGauche.addObserver(vuePerspectiveGauche);

      perspectiveDroite = new Perspective(thumbnail);
      vuePerspectiveDroite = new VuePerspective(perspectiveDroite);
      thumbnail.addObserver(vuePerspectiveDroite);
      perspectiveDroite.addObserver(vuePerspectiveDroite);

      initFenetrePrincipale(vueThumbnail, vuePerspectiveGauche, vuePerspectiveDroite);

    }

    /**
     * Permet d'initialiser les paramètres de la fenêtre principale
     * 
     * @param vueThumbnail
     * @param vuePerspectiveG
     * @param vuePerspectiveD
     */
  public void initFenetrePrincipale(JPanel vueThumbnail, JPanel vuePerspectiveG, JPanel vuePerspectiveD ){

    // Create a menu bar
      JMenuBar menuBar = new JMenuBar();

      // Add the file menu to the menu bar
      menuBar.add(ajouterMenuFichier());
      menuBar.add(ajouterMenuEdition());

      // Set the menu bar for the frame
      setJMenuBar(menuBar);

      // Utiliser un GridLayout pour les panneaux
      JPanel panelContainer = new JPanel(new GridLayout(1, 3));
      panelContainer.add(vueThumbnail);
      panelContainer.add(vuePerspectiveG);
      panelContainer.add(vuePerspectiveD);

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

  /**
   * permet d'initialiser les éléments du menu déroulant fichier. Ce menu déroulant contient 
   * les options ouvrirImage, enregistrerPerspective et chargerPerspective
   * 
   * @return JMenu menuFichier
   */
  private JMenu ajouterMenuFichier() {

    JMenu menuFichier = new JMenu(MENU_FICHIER_TITRE);
    JMenuItem menuOuvrirImage = new JMenuItem(MENU_OUVRIR_IMAGE);
    JMenuItem menuEnregistrerPerspective = new JMenuItem(MENU_ENREGISTRER_PERSPECTIVE);
    JMenuItem menuChargerPerspective = new JMenuItem(MENU_CHARGER_PERSPECTIVE);
    JMenuItem menuQuitter = new JMenuItem(MENU_FICHIER_QUITTER);

    menuFichier.add(menuOuvrirImage);
    ouvrirImageThumbnail (menuOuvrirImage);

    menuFichier.add(menuEnregistrerPerspective);
    enregistrerPerspective (menuEnregistrerPerspective);

    menuFichier.add(menuChargerPerspective);
    chargerPerspective (menuChargerPerspective);

    menuFichier.addSeparator();
    menuFichier.add(menuQuitter);
    add(menuFichier);

    menuQuitter.addActionListener((ActionEvent e) -> {
      System.exit(0);
    });

    return menuFichier;
  }

  /**
   * methode qui permet d'ouvrir l'image thumbnail
   * 
   * @param menuOuvrirImage item du menu Fichier
   */

  public void ouvrirImageThumbnail (JMenuItem menuOuvrirImage){

    // Ouvrir image thumbnail
    menuOuvrirImage.addActionListener((ActionEvent e) -> {
      JFileChooser fileChooser = new JFileChooser();
      int returnValue = fileChooser.showOpenDialog(FenetrePrincipale.this);

      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        loadImage(file);
      }
    });
  }

  /**
   * methode qui permet d'enregistrer la perspective
   * 
   * @param menuEnregistrerPerspective item du menu Fichier
   */
  public void enregistrerPerspective (JMenuItem menuEnregistrerPerspective){

    // Enregistrer la perspective
    menuEnregistrerPerspective.addActionListener((ActionEvent e) -> {
      JFileChooser fileChooser = new JFileChooser();
      int returnValue = fileChooser.showSaveDialog(FenetrePrincipale.this);
      if (returnValue == JFileChooser.APPROVE_OPTION) {

        File file = fileChooser.getSelectedFile();

        EnsemblePerspectives perspectives = new EnsemblePerspectives(perspectiveGauche, perspectiveDroite);
        
        try {

          savePerspective(perspectives, file);
          
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    });
  }

    /**
     * methode qui permet de charger la perspective
     * 
     * @param menuChargerPerspective item du menu Fichier
     */
  private void chargerPerspective(JMenuItem menuChargerPerspective) {

     // Charger la perspective
    menuChargerPerspective.addActionListener((ActionEvent e) -> {
      JFileChooser fileChooser = new JFileChooser();
      int returnValue = fileChooser.showOpenDialog(FenetrePrincipale.this);

      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();

        EnsemblePerspectives loadedPerspectives;

        try {
          loadedPerspectives = loadPerspective(file);

          if (loadedPerspectives != null) {
          //charger les diffferentes perspectives selon leur position dans la arraylist
          perspectiveGauche.setPosition(loadedPerspectives.getPerspectiG().getPosition());
          perspectiveGauche.setZoomLevel(loadedPerspectives.getPerspectiG().getZoomLevel());
          perspectiveDroite.setPosition(loadedPerspectives.getperspectiveD().getPosition());
          perspectiveDroite.setZoomLevel(loadedPerspectives.getperspectiveD().getZoomLevel());
        }

        } catch (ClassNotFoundException e1) {
          e1.printStackTrace();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
        
      }
    });
  }

/**
 * permet d'initialiser les éléments du menu déroulant Edition
 * 
 * @return JMenu menu Edition
 */

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

  /**
   * permet de charger une image
   * 
   * @param file
   */

  private void loadImage(File file) {
    BufferedImage newImage;
    try {
      // Lire l'image à partir du fichier
      newImage = ImageIO.read(file);

      // Obtenir les dimensions de vueThumbnail
      int targetWidth = vueThumbnail.getWidth();
      int targetHeight = vueThumbnail.getHeight();

      // Calculer les facteurs d'échelle
      double scaleX = (double) targetWidth / newImage.getWidth();
      double scaleY = (double) targetHeight / newImage.getHeight();

      // Choisir le facteur d'échelle minimal pour maintenir le ratio d'aspect de l'image
      double scale = Math.min(scaleX, scaleY);

      // Calculer la position pour centrer l'image redimensionnée
      int x = (int) ((targetWidth - scale * newImage.getWidth()) / 2);
      int y = (int) ((targetHeight - scale * newImage.getHeight()) / 2);

      // Créer une nouvelle BufferedImage et redimensionner l'image
      BufferedImage scaledBufferedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g = scaledBufferedImage.createGraphics();

      // Dessiner l'image redimensionnée à la position calculée
      g.drawImage(newImage.getScaledInstance((int) (newImage.getWidth() * scale), (int) (newImage.getHeight() * scale), Image.SCALE_SMOOTH), x, y, null);
      g.dispose();

      // Mettre à jour la miniature avec l'image redimensionnée
      Commande command = CommandeFactory.createLoadImageCommand(thumbnail, scaledBufferedImage);
      GestionnaireCommandes.getInstance().execute(command);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, String.format("Couldn't load image %s", file.getAbsolutePath()));
    }
  }

  /**
   * Permet de sauvegarder la perspective
   * 
   * @param perspectives ensemble des deux perspectives
   * @param file
   * @throws IOException
   */

  private void savePerspective(EnsemblePerspectives perspectives, File file) throws IOException {

    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
    oos.writeObject(perspectives);

  }
  
  /**
   * permet de charger les perspectives
   * 
   * @param file
   * @return ensemble des deux perspectives
   * 
   * @throws ClassNotFoundException
   * @throws IOException
   */
  private EnsemblePerspectives loadPerspective (File file) throws ClassNotFoundException, IOException {
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
    return (EnsemblePerspectives) ois.readObject();
  }

}