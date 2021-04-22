/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2104.ile_interdite.modele;

/**
 *
 * @author capelth
 */
public class CInondation {
    
    private Tuile tuile;
    
    public CInondation(Tuile t){
        tuile = t;
    }

    /**
     * @return the tuile
     */
    public Tuile getTuile() {
        return tuile;
    }

    /**
     * @param tuile the tuile to set
     */
    public void setTuile(Tuile tuile) {
        this.tuile = tuile;
    }
    
}
