package controller;

public abstract class Commande {
    public abstract void execute();

    public abstract void undo();

    public boolean canUndo(){
        return true;
    }
}
