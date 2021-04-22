package m2104.ile_interdite.modele;

import java.util.ArrayList;
import m2104.ile_interdite.util.*;
/**
 *
 * @author IUT2-Dept Info
 */
public class Aventurier {
    private IleInterdite ii;
    private String nomJoueur;
    private Tuile tuile;
    private CJoueur[] cartesJoueur;
    private TypePion pion;
    private TypeRole role;
    private boolean pouvoirUtilise = false;
    
    public Aventurier(IleInterdite i,String nom, Tuile t, TypePion p, TypeRole r) {
        ii = i;
        nomJoueur = nom;
        tuile = t;
        cartesJoueur = new CJoueur[6];
        pion = p;
        role = r;
    }
    
    
    public boolean addCarteJoueur(CJoueur cJoueur) {
        boolean res = false;
        for (int i=0; i<cartesJoueur.length; i++) {
            if (cartesJoueur[i] == null) {
                cartesJoueur[i] = cJoueur;
                res =  true;
                break;
            }
        }
        return res;
    }
    
    
    public boolean removeCarteJoueur(CJoueur cJoueur) {
        boolean res = false;
        for (int i=0; i<cartesJoueur.length; i++) {
            if (cartesJoueur[i] == cJoueur) {
                cartesJoueur[i] = null;
                res = true;
            }
        }
        return res;
    }
    
    
    public boolean removeCarteT(Tresor tr) {
        boolean res = false;
        int cmpt = 0;
        for (int i=0; i<cartesJoueur.length; i++) {
            for (int j=0; i<tr.getCartes().length; j++) {
                if (cartesJoueur[i].equals(tr.getCartes()[j])) {
                    cmpt = cmpt+1;
                }
            }
        }
        if (cmpt>=4) {
            for (int i=0; i<cartesJoueur.length; i++) {
                for (int j=0; i<tr.getCartes().length; j++) {
                    if (cartesJoueur[i].equals(tr.getCartes()[j])) {
                        cartesJoueur[i] = null;
                    }
                }
            }
            res = true;
        }
        return res;
    }
    
    
    public void seDeplacer() {
        ArrayList<Tuile> tDispos = new ArrayList<>();
        tDispos = ii.getGrille().tuilesDispoDeplacer(getTuile(), this);
        
        ii.tuilesDispos(TypeAction.CHOISIR_TUILE,this, tDispos);
    }
    
    public void seDeplacer(Tuile t){
        if (this.getRole().equals(TypeRole.Pilote)){
            if (!ii.getGrille().tuileAutour(this.getTuile()).contains(t)){
                this.pouvoirUtilise = true;
            }
        }
        this.tuile.getAventuriers().remove(this);
        this.setTuile(t);
        t.addAventurier(this);
        if (t.getAventuriers().size() == ii.getNombreJoueurs() && ii.getTresorsRecup().size() == 4){
            for (Aventurier a : t.getAventuriers()){
                for (CJoueur c : a.getCartes()){
                    if (c.getTypeCarte().equals(TypeCarte.CHELICOPTERE)){
                        ii.setGagne(true);
                        ii.setFini();
                    }
                }
               
            }
        }
            if (ii.PartieFinie()) {
                Message m = new Message();
                m.type = TypeAction.FIN_PARTIE;
                m.gagne = ii.getGagne();
                ii.notifierObservateurs(m);
        }
        
    }
    
    
    public void assecher() {
        ArrayList<Tuile> tDispos = new ArrayList<>();
        tDispos = ii.getGrille().tuilesDisposAssecher(getTuile(), this);
        
        ii.tuilesDispos(TypeAction.CHOISIR_TUILE_ASSECHER,this, tDispos); 
    }
    
    public void assecher(Tuile t) {
        t.assecher();
    }
    
    public void donnerCarte(){
        ArrayList<Aventurier> aventurierDispos = new ArrayList<>();
        for (Aventurier a : ii.getAventuriers()){
            if (a.getCartes()[4] == null) {
                aventurierDispos.add(a);
            }
        }
        aventurierDispos.remove(this);
        ii.aventuriersDispos(TypeAction.CHOISIR_CJOUEUR, this, aventurierDispos);
    }
    
    public void donnerCarte(Aventurier a , CJoueur c){
        a.addCarteJoueur(c);
        this.removeCarteJoueur(c);
        c.setA(a);
    }
    
    public void poserPionInit() {
        ii.placerPionInit(this);
    }

    
    
    //getters et setters
    
    public void setNom(String nom) {
        nomJoueur = nom;
    }
    
    public Tuile getTuile() {
        return tuile;
    }
    
    public void setTuile(Tuile t) {
        tuile = t;
    }
    
    public TypePion getPion() {
        return pion;
    }
    
    public CJoueur[] getCartes() {
        return cartesJoueur;
    }
    
    public TypeRole getRole() {
        return role;
    }
    
    public boolean getPouvoirUtilise(){
        return pouvoirUtilise;
    }
    
    public void setPouvoir(boolean b){
        this.pouvoirUtilise= b;
    }
    
    public String getNom(){
        return nomJoueur;
    }
    
    public String setNom(){
        return nomJoueur;
    }
    
}
