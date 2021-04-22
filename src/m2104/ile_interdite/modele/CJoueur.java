
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2104.ile_interdite.modele;
import m2104.ile_interdite.util.*;

/**
 *
 * @author capelth
 */
public class CJoueur {
    
    private Aventurier a;
    private TypeCarte typeCarte;
    private String nomCarte;
    
    public CJoueur(Aventurier av, String nomCarte){
        setA(av);
        this.nomCarte = nomCarte;
    }
    public CJoueur(Aventurier av,TypeCarte t, String nomCarte){
        setA(av);
        typeCarte = t;
        this.nomCarte = nomCarte;
    }
    
    /**
     * @return the a
     */
    public Aventurier getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(Aventurier a) {
        this.a = a;
    }
    
    public TypeCarte getTypeCarte(){
        return typeCarte;
    }

    /**
     * @return the nomCarte
     */
    public String getNomCarte() {
        return nomCarte;
    }
    
}
