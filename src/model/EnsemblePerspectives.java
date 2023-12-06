package model;
import java.io.Serializable;

public class EnsemblePerspectives implements Serializable{

    private Perspective perspectiveG;
    private Perspective perspectiveD;

    public EnsemblePerspectives (Perspective perspectiveG, Perspective perspectiveD){
        this.perspectiveG = perspectiveG;
        this.perspectiveD = perspectiveD;

    }

    public Perspective getPerspectiG(){
        return perspectiveG;
    }

    public Perspective getperspectiveD (){
        return perspectiveD;
    }



}
