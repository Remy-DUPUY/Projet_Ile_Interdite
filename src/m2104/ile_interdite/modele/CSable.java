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
public class CSable extends CJoueur {
    
    private String description;
    private String utilisation;
    private String nomSable; 
    
    public CSable(Aventurier a, String nomSable){
        super(a,TypeCarte.CSABLE, nomSable);
        this.description = "Asséchez n'importe quelle tuile de l'île.";
        this.utilisation = "*A jouer à tout moment *...";
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
}
