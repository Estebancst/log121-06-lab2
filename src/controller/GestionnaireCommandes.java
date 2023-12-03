package controller;

public class GestionnaireCommandes {

    // Instance unique du gestionnaire de commandes (Singleton)
    private static GestionnaireCommandes instance;

    // Pile pour stocker les commandes exécutées
    private DoublyLinkedStack<Commande> commandes;

    // Constructeur privé pour garantir une seule instance du gestionnaire de commandes
    private GestionnaireCommandes() {
        commandes = new DoublyLinkedStack<>();
    }

    // Méthode statique pour obtenir l'instance unique du gestionnaire de commandes
    public static GestionnaireCommandes getInstance() {
        if (instance == null) {
            instance = new GestionnaireCommandes();
        }
        return instance;
    }

    // Méthode pour exécuter une commande
    public void execute(Commande commande) {
        commande.execute();
        if(commande.canUndo()){
            commandes.push(commande);
        }
    }

    // Méthode pour annuler la dernière commande exécutée
    public void undo() {
        Commande commande = commandes.pop();
        if (commande != null) {
            commande.undo();
        }
    }

    // Méthode pour rétablir la dernière commande annulée
    public void redo() {
        if (commandes.hasNext()) {
            commandes.next().execute();
        }
    }
}
