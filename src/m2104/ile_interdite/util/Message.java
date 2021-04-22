package m2104.ile_interdite.util;

import java.io.Serializable;
import java.util.ArrayList;
import m2104.ile_interdite.modele.*;

/**
 *
 * @author IUT2-Dept Info
 */
public class Message implements Serializable {

    public static long serialVersionUID = 1L;
    public Integer idAventurier, idCarte, idTuile, nbJoueurs;
    public Utils.Tresor tresor;
    public Tuile tuile;
    public ArrayList<Tuile> tuiles;
    public int[] coords = new int[2];
    public int nivEau, nombreJoueurs;
    public TypePion pion;
    public String[] nomJoueurs;
    public TypeAction type;
    public Aventurier aventurier;
    public ArrayList<Aventurier> aventuriers;
    public CJoueur[] cjoueurs;
    public boolean gagne;
    public CJoueur carte;
    

    public Message() {
        aventurier = null;
        idCarte = null;
        tresor = null;
        idTuile = null;
        nbJoueurs = null;
        tuiles = new ArrayList();
        
    }
    
    private Message(TypeAction type, Aventurier aventurier, Integer idCarte, Utils.Tresor tresor, Integer idTuile, Integer nbJoueurs) {
        this.type = type;
        this.aventurier = aventurier;
        this.idCarte = idCarte;
        this.tresor = tresor;
        this.idTuile = idTuile;
        this.nbJoueurs = nbJoueurs;
        tuiles = new ArrayList();
    }
    
    public Message(TypeAction typeAction) {
        this.type = typeAction;
    }

    /**
     *
     * @param idAventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#BOUGER}
     */
    public static Message bouger(Aventurier aventurier) {
        return new Message(TypeAction.BOUGER, aventurier, null, null, null, null);
    }

    /**
     *
     * @param idAventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#ASSECHER}
     */
    public static Message assecher(Aventurier aventurier) {
        return new Message(TypeAction.ASSECHER, aventurier, null, null, null, null);
    }

    /**
     *
     * @param idAventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#DONNER}
     */
    public static Message donner(Aventurier aventurier) {
        return new Message(TypeAction.DONNER, aventurier, null, null, null, null);
    }

    /**
     *
     * @param aventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#RECUPERER_TRESOR}
     */
    public static Message recupererTresor(Aventurier aventurier) {
        return new Message(TypeAction.RECUPERER_TRESOR, aventurier, null, null, null, null);
    }

    /**
     *
     * @param idAventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#TERMINER}
     */
    public static Message terminer(Aventurier aventurier) {
        return new Message(TypeAction.TERMINER, aventurier, null, null, null, null);
    }

    /**
     *
     * @param idAventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#RECEVOIR}
     */
    public static Message recevoir(Aventurier aventurier) {
        return new Message(TypeAction.RECEVOIR, aventurier, null, null, null, null);
    }

    /**
     *
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#CHOISIR_CARTE}
     */
    public static Message choisirCarte() {
        return new Message(TypeAction.CHOISIR_CARTE, null, null, null, null, null);
    }

    /**
     *
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#CHOISIR_TUILE}
     */
    public static Message choisirTuile() {
        return new Message(TypeAction.CHOISIR_TUILE, null, null, null, null, null);
    }

    /**
     *
     * @param idAventurier
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#DEPLACER}
     */
    public static Message deplacer(Aventurier aventurier) {
        return new Message(TypeAction.DEPLACER, aventurier, null, null, null, null);
    }

    /**
     *
     * @return un nouveau {@link #Message} pour la commande {@link m2104.ile_interdite.util.Utils.Commandes#VOIR_DEFAUSSE}
     */
    public static Message voirDefausse() {
        return new Message(TypeAction.DEFAUSSER, null, null, null, null, null);
    }

    /**
     * @return the commande
     */
    public Boolean hasCommande() {
        return type != null;
    }
    public TypeAction getCommande() {
        return type;
    }

    /**
     * @return the idAventurier`
     */
    public Boolean hasIdAventurier() {
        return idAventurier != null;
    }
    public Integer getIdAventurier() {
        return idAventurier;
    }

    /**
     * @return the idCarte
     */
    public Boolean hasIdCarte() {
        return idCarte != null;
    }
    public Integer getIdCarte() {
        return idCarte;
    }

    /**
     * @return the tresor
     */
    public Boolean hasTresor() {
        return tresor != null;
    }
    public Utils.Tresor getTresor() {
        return tresor;
    }

    /**
     *
     * @return the nbJoueurs
     */
    public Boolean hasNbJoueurs() {
        return nbJoueurs != null;
    }
    public Integer getNbJoueurs() {
        return nbJoueurs;
    }

    /**
     * @return the idTuile
     */
    public Boolean hasIdTuile() {
        return idTuile != null;
    }
    public Integer getIdTuile() {
        return idTuile;
    }

    @Override
    public String toString() {
        String txt = "";
        txt += type.toString() + " ";
        if (hasIdAventurier()) {
            txt += " aventurier=" + idAventurier;
        }
        if (hasIdCarte()) {
            txt += " carte=" + idCarte;
        }
        if (hasIdTuile()) {
            txt += " tuile=" + idTuile;
        }
        if (hasTresor()) {
            txt += " tresor=" + tresor.toString();
        }
        return txt;
    }    
    
}
