package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Message;
import patterns.observateur.Observable;
import patterns.observateur.Observateur;
import java.util.ArrayList;
import java.util.Collections;
import m2104.ile_interdite.util.*;

/**
 *
 * @author Raphaël Bleuse <raphael.bleuse@iut2.univ-grenoble-alpes.fr>
 */
public class IleInterdite extends Observable<Message> {

    private Grille g;
    private int niveauEau;
    private int etapeEau;
    private ArrayList<Tuile> tuiles;
    private Tresor[] tresors = new Tresor[4];
    private ArrayList<Tresor> tresorsRecup = new ArrayList<>();
    private ArrayList<CJoueur> cartesJoueurPioche = new ArrayList<>();
    private ArrayList<CJoueur> cartesJoueurDefausse = new ArrayList<CJoueur>();
    private ArrayList<CInondation> cartesInondationPioche = new ArrayList<>();
    private ArrayList<CInondation> cartesInondationDefausse = new ArrayList<>();
    private ArrayList<Aventurier> aventuriers = new ArrayList<>();
    private int nbJoueurs;
    private boolean fini = false;
    private boolean gagne = false;
    private Aventurier joueurCourant;
    private int nbactions;

    public IleInterdite(Observateur<Message> observateur, int niv, String[] noms) {
        this.addObservateur(observateur);
        etapeEau = niv;
        setNiveauEau(etapeEau);
        tuiles = new ArrayList<>();

        //création des cartes trésor
        CTresor[] c1 = creationCartesTresorPierre();
        CTresor[] c2 = creationCartesTresorStatue();
        CTresor[] c3 = creationCartesTresorCristal();
        CTresor[] c4 = creationCartesTresorCalice();

        //création trésors
        creationTresors(c1, c2, c3, c4);

        //création carte montée des eaux
        creationsCartesMontee();

        //création carte hélicoptère
        creationCartesHelico();

        //création carte sable
        creationCartesSable();

        //création tuiles
        creationTuiles();

        //mélange des tuiles
        melange(tuiles);

        //création des cartes inondation
        creationCartesInondation();

        //Mélange pioches
        melange(cartesJoueurPioche);
        melange(cartesInondationPioche);

        //création de la grille
        g = new Grille(this, tuiles);

        //Inscription des joueurs
        inscrireJoueurs(noms);

        nbJoueurs = noms.length;

        //distribution carte joueurs
        distribuerCartesJoueur();

        //pioche des cartes inondations
        initialiserInondation();
    }

    public String[] inscrireJoueurs(String[] noms) {
        // TODO: à remplacer par une réelle assignation des types d'aventuriers (methode appelé associeAventurier de base)

        ArrayList<TypeRole> roles = new ArrayList<>();
        for (int j = 0; j < TypeRole.values().length; j++) {
            roles.add(TypeRole.values()[j]);
        }
        Collections.shuffle(roles);

        for (int i = 0; i < noms.length; i++) {
            switch (roles.get(i)) {
                case Explorateur:
                    for (int j = 0; j < tuiles.size(); j++) {
                        if (tuiles.get(j).getNomTuile().equals("LaPorteDeCuivre")) {
                            Aventurier a = new Aventurier(this, noms[i], tuiles.get(j), TypePion.VERT, TypeRole.Explorateur);
                            tuiles.get(j).addAventurier(a);
                            aventuriers.add(a);
                            break;
                        }
                    }
                    break;

                case Navigateur:
                    for (int j = 0; j < tuiles.size(); j++) {
                        if (tuiles.get(j).getNomTuile().equals("LaPortedOr")) {
                            Aventurier a1 = new Aventurier(this, noms[i], tuiles.get(j), TypePion.JAUNE, TypeRole.Navigateur);
                            tuiles.get(j).addAventurier(a1);
                            aventuriers.add(a1);
                        }
                    }
                    break;

                case Plongeur:
                    for (int j = 0; j < tuiles.size(); j++) {
                        if (tuiles.get(j).getNomTuile().equals("LaPorteDeFer")) {
                            Aventurier a2 = new Aventurier(this, noms[i], tuiles.get(j), TypePion.NOIR, TypeRole.Plongeur);
                            tuiles.get(j).addAventurier(a2);
                            aventuriers.add(a2);
                        }
                    }
                    break;

                case Ingenieur:
                    for (int j = 0; j < tuiles.size(); j++) {
                        if (tuiles.get(j).getNomTuile().equals("LaPorteDeBronze")) {
                            Aventurier a3 = new Aventurier(this, noms[i], tuiles.get(j), TypePion.ROUGE, TypeRole.Ingenieur);
                            tuiles.get(j).addAventurier(a3);
                            aventuriers.add(a3);
                        }
                    }
                    break;

                case Messager:
                    for (int j = 0; j < tuiles.size(); j++) {
                        if (tuiles.get(j).getNomTuile().equals("LaPortedArgent")) {
                            Aventurier a4 = new Aventurier(this, noms[i], tuiles.get(j), TypePion.BLANC, TypeRole.Messager);
                            tuiles.get(j).addAventurier(a4);
                            aventuriers.add(a4);
                        }
                    }
                    break;

                case Pilote:
                    for (int j = 0; j < tuiles.size(); j++) {
                        if (tuiles.get(j).getNomTuile().equals("Heliport")) {
                            Aventurier a5 = new Aventurier(this, noms[i], tuiles.get(j), TypePion.BLEU, TypeRole.Pilote);
                            tuiles.get(j).addAventurier(a5);
                            aventuriers.add(a5);
                        }
                    }
                    break;

            }

        }
        joueurCourant = aventuriers.get(0);
        setNbActions(0);
        this.nbJoueurs = noms.length;
        return noms;

    }

    public void tuilesDispos(TypeAction type, Aventurier a, ArrayList<Tuile> tuiles) {
        Message m = new Message();
        m.type = type;
        m.tuiles = tuiles;
        m.aventurier = a;
        notifierObservateurs(m);
    }

    public void aventuriersDispos(TypeAction type, Aventurier a, ArrayList<Aventurier> aventuriers) {
        Message m = new Message();
        m.type = type;
        m.aventuriers = aventuriers;
        m.cjoueurs = a.getCartes();
        m.aventurier = a;
        notifierObservateurs(m);
    }

    public void placerPionInit(Aventurier a) {
        int[] coords = new int[2];
        coords = g.getCoordonnee(a.getTuile());

        Message m = new Message();
        m.type = TypeAction.BOUGER;
        m.pion = a.getPion();
        m.coords = coords;
        notifierObservateurs(m);
    }

    public void recupererTresor(Aventurier a) {
        tresorsRecup.add(a.getTuile().getTresor());
        removeTresor(a.getTuile().getTresor());
        a.removeCarteT(a.getTuile().getTresor());
    }

    public void removeTresor(Tresor t) {
        for (int i = 0; i < tresors.length; i++) {
            if (tresors[i] == t) {
                tresors[i] = null;
            }
        }
    }

    public CTresor[] creationCartesTresorPierre() {
        CTresor[] c = new CTresor[5];
        CTresor c1 = new CTresor(null, "Pierre");
        c[0] = c1;
        cartesJoueurPioche.add(c1);
        CTresor c2 = new CTresor(null, "Pierre");
        c[1] = c2;
        cartesJoueurPioche.add(c2);
        CTresor c3 = new CTresor(null, "Pierre");
        c[2] = c3;
        cartesJoueurPioche.add(c3);
        CTresor c4 = new CTresor(null, "Pierre");
        c[3] = c4;
        cartesJoueurPioche.add(c4);
        CTresor c5 = new CTresor(null, "Pierre");
        c[4] = c5;
        cartesJoueurPioche.add(c5);
        return c;
    }

    public CTresor[] creationCartesTresorStatue() {
        CTresor[] c = new CTresor[5];
        CTresor c1 = new CTresor(null, "Zephyr");
        c[0] = c1;
        cartesJoueurPioche.add(c1);
        CTresor c2 = new CTresor(null, "Zephyr");
        c[1] = c2;
        cartesJoueurPioche.add(c2);
        CTresor c3 = new CTresor(null, "Zephyr");
        c[2] = c3;
        cartesJoueurPioche.add(c3);
        CTresor c4 = new CTresor(null, "Zephyr");
        c[3] = c4;
        cartesJoueurPioche.add(c4);
        CTresor c5 = new CTresor(null, "Zephyr");
        c[4] = c5;
        cartesJoueurPioche.add(c5);
        return c;
    }

    public CTresor[] creationCartesTresorCalice() {
        CTresor[] c = new CTresor[5];
        CTresor c1 = new CTresor(null, "Calice");
        c[0] = c1;
        cartesJoueurPioche.add(c1);
        CTresor c2 = new CTresor(null, "Calice");
        c[1] = c2;
        cartesJoueurPioche.add(c2);
        CTresor c3 = new CTresor(null, "Calice");
        c[2] = c3;
        cartesJoueurPioche.add(c3);
        CTresor c4 = new CTresor(null, "Calice");
        c[3] = c4;
        cartesJoueurPioche.add(c4);
        CTresor c5 = new CTresor(null, "Calice");
        c[4] = c5;
        cartesJoueurPioche.add(c5);
        return c;
    }

    public CTresor[] creationCartesTresorCristal() {
        CTresor[] c = new CTresor[5];
        CTresor c1 = new CTresor(null, "Cristal");
        c[0] = c1;
        cartesJoueurPioche.add(c1);
        CTresor c2 = new CTresor(null, "Cristal");
        c[1] = c2;
        cartesJoueurPioche.add(c2);
        CTresor c3 = new CTresor(null, "Cristal");
        c[2] = c3;
        cartesJoueurPioche.add(c3);
        CTresor c4 = new CTresor(null, "Cristal");
        c[3] = c4;
        cartesJoueurPioche.add(c4);
        CTresor c5 = new CTresor(null, "Cristal");
        c[4] = c5;
        cartesJoueurPioche.add(c5);
        return c;
    }

    public void creationTresors(CTresor[] c1, CTresor[] c2, CTresor[] c3, CTresor[] c4) {
        Tresor tr1 = new Tresor("pierre", c1);
        tresors[0] = tr1;
        Tresor tr2 = new Tresor("zephyr", c2);
        tresors[1] = tr2;
        Tresor tr3 = new Tresor("cristal", c3);
        tresors[2] = tr3;
        Tresor tr4 = new Tresor("calice", c4);
        tresors[3] = tr4;
    }

    public void creationTuiles() {
        //création tuiles avec ou sans trésor
        Tuile t1 = new Tuile("LaCarverneDuBrasier", tresors[2]);
        tresors[2].addTuiles(t1);
        Tuile t2 = new Tuile("Heliport");   
        Tuile t3 = new Tuile("LaCarverneDesOmbres", tresors[2]); 
        tresors[2].addTuiles(t3);
        Tuile t4 = new Tuile("LaForetPourpre"); 
        Tuile t5 = new Tuile("LaPortedArgent"); 
        Tuile t6 = new Tuile("LaPorteDeBronze"); 
        Tuile t7 = new Tuile("LaPorteDeCuivre"); 
        Tuile t8 = new Tuile("LaPorteDeFer"); 
        Tuile t9 = new Tuile("LaPortedOr"); 
        Tuile t10 = new Tuile("LaTourDuGuet");
        Tuile t11 = new Tuile("LeJardinDesHurlements", tresors[1]); 
        tresors[1].addTuiles(t11);
        Tuile t12 = new Tuile("LeJardinDesMurmures", tresors[1]);
        tresors[1].addTuiles(t12);
        Tuile t13 = new Tuile("LeLagonPerdu");
        Tuile t14 = new Tuile("LeMaraisBrumeux");
        Tuile t15 = new Tuile("LePalaisDeCorail", tresors[3]);
        tresors[3].addTuiles(t15);
        Tuile t16 = new Tuile("LePalaisDesMarees", tresors[3]);
        tresors[3].addTuiles(t16);
        Tuile t17 = new Tuile("LePontDesAbimes");
        Tuile t18 = new Tuile("LeRocherFantome");
        Tuile t19 = new Tuile("LesDunesDeLIllusion");
        Tuile t20 = new Tuile("LesFalaisesDeLOubli");
        Tuile t21 = new Tuile("LeTempleDeLaLune", tresors[0]);
        tresors[0].addTuiles(t21);
        Tuile t22 = new Tuile("LeTempleDuSoleil", tresors[0]);
        tresors[0].addTuiles(t22);
        Tuile t23 = new Tuile("LeValDuCrepuscule");
        Tuile t24 = new Tuile("Observatoire");

        //ajout des tuiles à l'array list et création cartes inondation
        tuiles.add(t1);
        tuiles.add(t2);
        tuiles.add(t3);
        tuiles.add(t4);
        tuiles.add(t5);
        tuiles.add(t6);
        tuiles.add(t7);
        tuiles.add(t8);
        tuiles.add(t9);
        tuiles.add(t10);
        tuiles.add(t11);
        tuiles.add(t12);
        tuiles.add(t13);
        tuiles.add(t14);
        tuiles.add(t15);
        tuiles.add(t16);
        tuiles.add(t17);
        tuiles.add(t18);
        tuiles.add(t19);
        tuiles.add(t20);
        tuiles.add(t21);
        tuiles.add(t22);
        tuiles.add(t23);
        tuiles.add(t24);
    }

    public void creationCartesInondation() {
        CInondation ci1 = new CInondation(tuiles.get(0));
        cartesInondationPioche.add(ci1);
        CInondation ci2 = new CInondation(tuiles.get(1));
        cartesInondationPioche.add(ci2);
        CInondation ci3 = new CInondation(tuiles.get(2));
        cartesInondationPioche.add(ci3);
        CInondation ci4 = new CInondation(tuiles.get(3));
        cartesInondationPioche.add(ci4);
        CInondation ci5 = new CInondation(tuiles.get(4));
        cartesInondationPioche.add(ci5);
        CInondation ci6 = new CInondation(tuiles.get(5));
        cartesInondationPioche.add(ci6);
        CInondation ci7 = new CInondation(tuiles.get(6));
        cartesInondationPioche.add(ci7);
        CInondation ci8 = new CInondation(tuiles.get(7));
        cartesInondationPioche.add(ci8);
        CInondation ci9 = new CInondation(tuiles.get(8));
        cartesInondationPioche.add(ci9);
        CInondation ci10 = new CInondation(tuiles.get(9));
        cartesInondationPioche.add(ci10);
        CInondation ci11 = new CInondation(tuiles.get(10));
        cartesInondationPioche.add(ci11);
        CInondation ci12 = new CInondation(tuiles.get(11));
        cartesInondationPioche.add(ci12);
        CInondation ci13 = new CInondation(tuiles.get(12));
        cartesInondationPioche.add(ci13);
        CInondation ci14 = new CInondation(tuiles.get(13));
        cartesInondationPioche.add(ci14);
        CInondation ci15 = new CInondation(tuiles.get(14));
        cartesInondationPioche.add(ci15);
        CInondation ci16 = new CInondation(tuiles.get(15));
        cartesInondationPioche.add(ci16);
        CInondation ci17 = new CInondation(tuiles.get(16));
        cartesInondationPioche.add(ci17);
        CInondation ci18 = new CInondation(tuiles.get(17));
        cartesInondationPioche.add(ci18);
        CInondation ci19 = new CInondation(tuiles.get(18));
        cartesInondationPioche.add(ci19);
        CInondation ci20 = new CInondation(tuiles.get(19));
        cartesInondationPioche.add(ci20);
        CInondation ci21 = new CInondation(tuiles.get(20));
        cartesInondationPioche.add(ci21);
        CInondation ci22 = new CInondation(tuiles.get(21));
        cartesInondationPioche.add(ci22);
        CInondation ci23 = new CInondation(tuiles.get(22));
        cartesInondationPioche.add(ci23);
        CInondation ci24 = new CInondation(tuiles.get(23));
        cartesInondationPioche.add(ci24);
    }

    public void creationsCartesMontee() {
       CMonteeEaux c1 = new CMonteeEaux(null, "MonteeDesEaux");
        cartesJoueurPioche.add(c1);
        CMonteeEaux c2 = new CMonteeEaux(null, "MonteeDesEaux");
        cartesJoueurPioche.add(c2);
        CMonteeEaux c3 = new CMonteeEaux(null, "MonteeDesEaux");
        cartesJoueurPioche.add(c3);
    }

    public void creationCartesHelico() {
        CHelicoptere c1 = new CHelicoptere(null, "Helicoptere");
        cartesJoueurPioche.add(c1);
        CHelicoptere c2 = new CHelicoptere(null, "Helicoptere");
        cartesJoueurPioche.add(c2);
        CHelicoptere c3 = new CHelicoptere(null, "Helicoptere");
        cartesJoueurPioche.add(c3);
    }

    public void creationCartesSable() {
        CSable c1 = new CSable(null, "SacsDeSable");
        cartesJoueurPioche.add(c1);
        CSable c2 = new CSable(null, "SacsDeSable");
        cartesJoueurPioche.add(c2);
    }

    public void melange(ArrayList a) {
        Collections.shuffle(a);
    }

    public void viderDefausseCartesJoueur() {
        for (CJoueur c : cartesJoueurDefausse) {
            cartesJoueurPioche.add(c);
        }
        cartesJoueurDefausse.clear();
    }

    public void distribuerCartesJoueur() {
        int j = cartesJoueurPioche.size() - 1;
        for (Aventurier a : aventuriers) {
            int i = 0;
            while (i < 4) {
                if (cartesJoueurPioche.get(j).getTypeCarte().equals(TypeCarte.CMONTEEEAUX)) {
                    i = i - 1;
                } else {
                    a.getCartes()[i] = cartesJoueurPioche.get(j);
                    cartesJoueurPioche.get(j).setA(a);
                    cartesJoueurPioche.remove(cartesJoueurPioche.get(j));
                }
                j--;
                i++;
            }
        }
    }

    public void initialiserInondation() {
        for (int i = 0; i < 6; i++) {
            inonde(cartesInondationPioche.get(cartesInondationPioche.size() - 1).getTuile());
            cartesInondationDefausse.add(cartesInondationPioche.get(cartesInondationPioche.size() - 1));
            cartesInondationPioche.remove((cartesInondationPioche.size() - 1));
        }
    }

    public void piocherCarteInondation() {

        for (int i = 0; i < this.niveauEau; i++) {
            if (cartesInondationPioche.size() > 0) {
                if (!cartesInondationPioche.get(cartesInondationPioche.size() - 1).getTuile().getEtat().equals(TypeEtat.COULE)) {
                    inonde(cartesInondationPioche.get(cartesInondationPioche.size() - 1).getTuile());
                }
                cartesInondationDefausse.add(cartesInondationPioche.get(cartesInondationPioche.size() - 1));
                cartesInondationPioche.remove((cartesInondationPioche.size() - 1));
            } else {
                viderDefausseCartesInondation();
                if (!cartesInondationPioche.get(cartesInondationPioche.size() - 1).getTuile().getEtat().equals(TypeEtat.COULE)) {
                    inonde(cartesInondationPioche.get(cartesInondationPioche.size() - 1).getTuile());
                }
                cartesInondationDefausse.add(cartesInondationPioche.get(cartesInondationPioche.size() - 1));
                cartesInondationPioche.remove((cartesInondationPioche.size() - 1));
            }
        }
    }

    public void viderDefausseCartesInondation() {
        Collections.shuffle(cartesInondationDefausse);
        for (CInondation c : cartesInondationDefausse) {
            cartesInondationPioche.add(c);
        }
        cartesInondationDefausse.clear();
    }

    public void piocherCarteJoueur(Aventurier a) {
        for (int i = 0; i < 2; i++) {
            if (cartesJoueurPioche.size() <= 0) {
                viderDefausseCartesJoueur();
            }
            if (cartesJoueurPioche.get(cartesJoueurPioche.size() - 1).getTypeCarte() != TypeCarte.CMONTEEEAUX) {
                a.addCarteJoueur(cartesJoueurPioche.get(cartesJoueurPioche.size() - 1));
            } else {
                etapeEau = etapeEau + 1;
                setNiveauEau(etapeEau);
                viderDefausseCartesInondation();
            }
            cartesJoueurDefausse.add(cartesJoueurPioche.get(cartesJoueurPioche.size() - 1));
            cartesJoueurPioche.remove(cartesJoueurPioche.size() - 1);

        }
    }

    public boolean PartieFinie() {
        return fini;
    }

    public void inonde(Tuile t) {
        t.inonde();
        if (t.getEtat().equals(TypeEtat.COULE)){
        cartesInondationDefausse.remove(t.getCinondation());
        cartesInondationPioche.remove(t.getCinondation());
        }
        if (g.getTuile("Heliport").equals(t)) {
            if (t.getEtat().equals(TypeEtat.COULE)) {
                fini = true;
            }
        }
        if (t.getTresor() != null) {
            if (!tresorsRecup.contains(t.getTresor())) {
                if (t.getEtat().equals(TypeEtat.COULE)) {
                    int i = 0;
                    for (Tuile tu : t.getTresor().getTuiles()) {
                        if (tu.getEtat().equals(TypeEtat.COULE)) {
                            i++;
                        }
                    }
                    fini = (i == 2);
                }
            }
        }
        if (!t.getAventuriers().isEmpty()) {
            if (t.getEtat().equals(TypeEtat.COULE)) {

                for (Aventurier a : t.getAventuriers()) {
                    if (g.tuilesDispoDeplacer(t, a).isEmpty()) {
                        fini = true;
                    }
                }
                if (!fini) {
                    for (Aventurier av : aventuriers) {
                        av.seDeplacer();
                    }
                }

            }

        }
        if (this.PartieFinie()) {
                Message m = new Message();
                m.type = TypeAction.FIN_PARTIE;
                m.gagne = this.getGagne();
                notifierObservateurs(m);
        }
    }

    public void seDeplacer() {

        joueurCourant.seDeplacer();
    }

    public void seDeplacer(Tuile t) {
        joueurCourant.seDeplacer(t);
        if (nbactions < 2) {
            nbactions++;

        } else {
            piocherCarteJoueur(joueurCourant);
            if (this.aventuriers.indexOf(joueurCourant) == this.aventuriers.size() - 1) {
                joueurCourant = this.aventuriers.get(0);
            } else {
                joueurCourant = this.aventuriers.get(1 + this.aventuriers.indexOf(joueurCourant));
            }
            setNbActions(0);
            joueurCourant.setPouvoir(false);

            piocherCarteInondation();
        }

        Message m1 = new Message();
        m1.type = TypeAction.ACTUALISER;
        notifierObservateurs(m1);

        
    }

    public void assecher() {
        joueurCourant.assecher();
    }

    public void assecher(Tuile t) {
        joueurCourant.assecher(t);

        if (nbactions < 2) {
            nbactions++;
        } else {
          piocherCarteJoueur(joueurCourant);
            if (this.aventuriers.indexOf(joueurCourant) == this.aventuriers.size() - 1) {
                joueurCourant = this.aventuriers.get(0);
            } else {
                joueurCourant = this.aventuriers.get(1 + this.aventuriers.indexOf(joueurCourant));
            }
            setNbActions(0);
            joueurCourant.setPouvoir(false);
        }
        Message m1 = new Message();
        m1.type = TypeAction.ACTUALISER;
        notifierObservateurs(m1);
    }

    public void donnerCarte() {

        joueurCourant.donnerCarte();
    }

    public void donnerCarte(Aventurier a, CJoueur c) {
        joueurCourant.donnerCarte(a, c);
        if (nbactions < 2) {
            nbactions++;
        } else {
            if (this.aventuriers.indexOf(joueurCourant) == this.aventuriers.size() - 1) {
                joueurCourant = this.aventuriers.get(0);
            } else {
                joueurCourant = this.aventuriers.get(1 + this.aventuriers.indexOf(joueurCourant));
            }
            setNbActions(0);
            joueurCourant.setPouvoir(false);
        }
        Message m1 = new Message();
        m1.type = TypeAction.ACTUALISER;
        notifierObservateurs(m1);

    }

    public void terminerTour() {
        piocherCarteJoueur(joueurCourant);
       
            if (this.aventuriers.indexOf(joueurCourant) == this.aventuriers.size() - 1) {
                joueurCourant = this.aventuriers.get(0);
            } else {
                joueurCourant = this.aventuriers.get(1 + this.aventuriers.indexOf(joueurCourant));
            }
        piocherCarteInondation();
        setNbActions(0);
        joueurCourant.setPouvoir(false);
        Message m = new Message();
        m.type = TypeAction.ACTUALISER;
        notifierObservateurs(m);
    }

    //getters et setters
    public Grille getGrille() {
        return g;
    }

    public void setNiveauEau(int niv) {
        etapeEau = niv;
        if (etapeEau <= 2) {
            niveauEau = 2;
        } else if (etapeEau > 2 && etapeEau <= 5) {
            niveauEau = 3;
        } else if (etapeEau > 5 && etapeEau <= 7) {
            niveauEau = 4;
        } else if (etapeEau > 7 && etapeEau <= 9) {
            niveauEau = 5;
        } else {
            niveauEau = 6;
            fini = true;
        }
        if (this.PartieFinie()) {
                Message m = new Message();
                m.type = TypeAction.FIN_PARTIE;
                m.gagne = this.getGagne();
                notifierObservateurs(m);
        }
    }
    
    public int getNiveauEau() {
        return this.niveauEau;
    }
    
    public int getEtapeEau(){
        return this.etapeEau;
    }

    public int getNombreJoueurs() {
        return nbJoueurs;
    }

    public void setNombreJoueurs(int nb) {
        nbJoueurs = nb;
    }

    public ArrayList<Aventurier> getAventuriers() {
        return aventuriers;
    }

    public Tresor getTresor(int i) {
        return tresors[i];
    }

    public boolean getGagne() {
        return gagne;
    }

    public void setGagne(boolean b) {
        gagne = b;
    }

    public ArrayList<Tresor> getTresorsRecup() {
        return tresorsRecup;
    }

    public int getNbActions() {
        return nbactions;
    }

    public void setNbActions(int n) {
        nbactions = n;
    }

    public Aventurier getJoueurCourant() {
        return joueurCourant;
    }
    
    public void setFini(){
        fini = true;
    }
    
    public void recupTresorDemo() {
        tresorsRecup.add(joueurCourant.getTuile().getTresor());
        removeTresor(joueurCourant.getTuile().getTresor());
        
        if (nbactions < 2) {
            nbactions++;
        } else {
          piocherCarteJoueur(joueurCourant);
            if (this.aventuriers.indexOf(joueurCourant) == this.aventuriers.size() - 1) {
                joueurCourant = this.aventuriers.get(0);
            } else {
                joueurCourant = this.aventuriers.get(1 + this.aventuriers.indexOf(joueurCourant));
            }
            setNbActions(0);
            joueurCourant.setPouvoir(false);
        }
        Message m1 = new Message();
        m1.type = TypeAction.ACTUALISER;
        notifierObservateurs(m1);
    }
    
    public void defausserCJoueur(CJoueur c) {
        c.getA().removeCarteJoueur(c);
        c.setA(null);
        cartesJoueurDefausse.add(c);
        Message m1 = new Message();
        m1.type = TypeAction.ACTUALISER;
        notifierObservateurs(m1);
    }
}
