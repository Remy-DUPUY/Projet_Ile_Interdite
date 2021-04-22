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
public class CMonteeEaux extends CJoueur {
    
    private String description = "1. Montez le marqueur d'un cran.\n 2. Mélangez la pile de défausse des cartes Innondations et placez-la sur la pile Innondation.\n"
            + "3. Défaussez cette carte avec les cartes Trésor." ;
    private String nomCMonteeEaux;
    
    public CMonteeEaux( Aventurier a, String nomCMonteeEaux ){
        super(a,TypeCarte.CMONTEEEAUX, nomCMonteeEaux);
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
