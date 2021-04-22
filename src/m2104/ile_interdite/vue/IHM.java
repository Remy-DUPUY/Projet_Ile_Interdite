package m2104.ile_interdite.vue;

import java.util.ArrayList;
import m2104.ile_interdite.modele.*;
import m2104.ile_interdite.util.*;
import patterns.observateur.Observable;
import patterns.observateur.Observateur;

/**
 *
 * @author Raphaël Bleuse <raphael.bleuse@iut2.univ-grenoble-alpes.fr>
 */
public class IHM extends Observable<Message> {

    private final VueInscriptionJoueurs vueInscription;
    private VuePlateauJeu vuePlateauJeu;
    private VueTresor vuetresor;
    private VueHeader vueHeader;
    private VueBoutons vueBoutons;
    private VueNiveau vueNiveau;
    private VueMainJoueur vueMainJoueur;
    private Observateur controleur;
    private VueFermeture vueFermeture;
    private VueFinPartie vueFinPartie;
    private PDFVueInfo vueinfo;

    public IHM(Observateur<Message> observateur) {
        this.addObservateur(observateur);
        this.vueInscription = new VueInscriptionJoueurs(this);
        this.afficheVueInscription();
        this.controleur = observateur;
    }

    public void afficheVueInscription() {
        this.vueInscription.afficher();
    }

    public void btnJouer(Message m) {
        notifierObservateurs(m);
    }

    public void creeVueFermeture() {
        this.vueFermeture = new VueFermeture(this);
    }

    public void creePlateau(Grille g, int nbActions) {
        this.vuePlateauJeu = new VuePlateauJeu(g, this, nbActions);
        vuePlateauJeu.afficher();

    }

    public void creeHeader(ArrayList<Aventurier> listA) {
        vueHeader = new VueHeader(listA);
    }

    public void creeVueTresor(IleInterdite ile) {
        vuetresor = new VueTresor(ile);
    }
    
    public void recupTresor(Aventurier a) {
        vuetresor.recuptresor(a.getTuile().getTresor().getNomTresor());
    }

    public void creeVueBoutons() {
        vueBoutons = new VueBoutons(this);
        vueBoutons.afficher();
    }

    public void creeVueNiveau(int i) {
        vueNiveau = new VueNiveau(i);
    }

    public void creeVueMainJoueur(Grille g) {
        vueMainJoueur = new VueMainJoueur(g, this);
        vueMainJoueur.afficher();
    }

    public void creeVueInfos() {
        vueinfo = new PDFVueInfo();
    }

    public void afficheTuilesDispos(ArrayList<Tuile> tDispos, Grille g, int nbActionsR) {
        vuePlateauJeu.actualiserPlateauJeu(tDispos, g, nbActionsR);
    }

    public void afficheTuilesDisposAssecher(ArrayList<Tuile> tDispos, Grille g, int nbActionsR) {
        vuePlateauJeu.actualiserPlateauJeuAssecher(tDispos, g, nbActionsR);
    }

    public void actualiserPlateau(Grille g, int nbActionsR) {
        vuePlateauJeu.intitialiserPlateauJeu(g, nbActionsR);
    }
    
    public void actualiserNiveau(int i){
        vueNiveau.setNiveau(i);
    }

    public void afficheCarteMain(Aventurier a) {
        vueMainJoueur.actualiserMain(a);
    }

    public void donnerCarte() {
        CJoueur carte = choisirCarte();
        Aventurier a = choisirAventurier();

        System.out.println("Carte : " + carte.getNomCarte());
        System.out.println("Aventurier : " + a.getRole());

        Message m = new Message();
        m.type = TypeAction.DONNER;
        m.carte = carte;
        m.aventurier = a;
        notifierObservateurs(m);
    }

    public CJoueur choisirCarte() {
        return vueMainJoueur.getCarteChoisie();
    }

    public Aventurier choisirAventurier() {
        return vueHeader.getAventurierChoisi();
    }
    
    public void defausserCarte() {
        CJoueur carte = choisirCarte();
        
        Message m = new Message();
        m.type = TypeAction.DEFAUSSER;
        m.carte = carte;
        
        notifierObservateurs(m);
    }

    public void FermertousVue() {

        vuePlateauJeu.fermer();
        vuetresor.fermer();
        vueHeader.fermer();
        vueBoutons.fermer();
        vueNiveau.fermer();
        vueMainJoueur.fermer();
        vueFermeture.fermer();
        vueinfo.fermer();
    }

    public void finPartie(boolean gagne) {
        System.out.println("finPartie IHM");
        vueFinPartie = new VueFinPartie(this, gagne);
        lockVue();
        
    }

    public void lockVue() {
        vuePlateauJeu.lockFenetre();
        vuetresor.lockFenetre();
        vueHeader.lockFenetre();
        vueBoutons.lockFenetre();
        vueNiveau.lockFenetre();
        vueMainJoueur.lockFenetre();
        vueFermeture.lockFenetre();
        vueinfo.lockFenetre();
    }
}
