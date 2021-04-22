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
public class CTresor extends CJoueur{
    
    private String nomTresor;
    private Tresor tresor;
  
    
    
    public CTresor(Aventurier aventurier, String nomTresor){
        super(aventurier,TypeCarte.CTRESOR, nomTresor);
        // this.nomTresor = nt;
        tresor = null;
        
    }

    /**
     * @return the nomTresor
     */
    public String getNomTresor() {
        return nomTresor;
    }

    /**
     * @param nomTresor the nomTresor to set
     */
    public void setNomTresor(String nomTresor) {
        this.nomTresor = nomTresor;
    }
    
    public void setTresor(Tresor tr) {
        tresor = tr;
    }
    
}
