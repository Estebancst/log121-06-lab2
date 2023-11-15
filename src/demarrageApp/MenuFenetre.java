package demarrageApp;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class MenuFenetre extends JMenuBar {

    private static final long serialVersionUID = 1L;
	private static final String MENU_FICHIER_TITRE = "Fichier";
    private static final String MENU_OUVRIR_IMAGE = "Ouvrir une image";
    private static final String MENU_ENREGISTRER_PERSPECTIVE = "Enregistrer perspective";
    private static final String MENU_CHARGER_PERSPECTIVE = "Charger perspective";
	private static final String MENU_FICHIER_QUITTER = "Quitter";

	public MenuFenetre() {
		ajouterMenuFichier();
	}

    private void ajouterMenuFichier() {
		JMenu menuFichier = new JMenu(MENU_FICHIER_TITRE);
        JMenuItem menuOuvrirImage = new JMenuItem(MENU_OUVRIR_IMAGE);
        JMenuItem menuEnregistrerPerspective = new JMenuItem(MENU_ENREGISTRER_PERSPECTIVE);
        JMenuItem menuChargerPerspective = new JMenuItem(MENU_CHARGER_PERSPECTIVE);
		JMenuItem menuQuitter = new JMenuItem(MENU_FICHIER_QUITTER);

        menuOuvrirImage.addActionListener((ActionEvent e) -> {});
		
		menuQuitter.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});

        menuFichier.add(menuOuvrirImage);
        menuFichier.add(menuEnregistrerPerspective);
        menuFichier.add(menuChargerPerspective);
		menuFichier.add(menuQuitter);

		add(menuFichier);
	}

}