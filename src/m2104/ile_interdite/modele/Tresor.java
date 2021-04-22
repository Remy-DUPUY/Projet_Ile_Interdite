/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2104.ile_interdite.modele;
import java.util.ArrayList;

/**
 *
 * @author capelth
 */
public class Tresor {
    
    private String nomTresor;
    private CTresor[] cartesT = new CTresor[5];
    private ArrayList<Tuile> tuiles = new ArrayList<>();
    
    public Tresor(String nom, CTresor[] cartes) {
        nomTresor = nom;
        cartesT = cartes;
    }

    
    //getters et setters
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
    
    
    public CTresor[] getCartes() {
        return cartesT;
    }
    
    public ArrayList<Tuile> getTuiles(){
        return tuiles;
    }
    
    public void addTuiles(Tuile t){
        tuiles.add(t);
    }
    
}
