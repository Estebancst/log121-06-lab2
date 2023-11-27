package controller;

public class GestionnaireCommandes {
    private static GestionnaireCommandes instance;
    private DoublyLinkedStack<Commande> commandes;

    private GestionnaireCommandes() {
        commandes = new DoublyLinkedStack<>();
    }

    public static GestionnaireCommandes getInstance() {
        if (instance == null) {
            instance = new GestionnaireCommandes();
        }
        return instance;
    }

    public void execute(Commande commande) {
        commande.execute();
        if(commande.canUndo()){
            commandes.push(commande);
        }
    }

    public void undo() {
        Commande commande = commandes.pop();
        if (commande != null) {
            commande.undo();
        }
    }

    public void redo() {
        if (commandes.hasNext()) {
            commandes.next().execute();
        }
    }
}
