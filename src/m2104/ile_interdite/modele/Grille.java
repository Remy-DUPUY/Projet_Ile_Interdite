/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2104.ile_interdite.modele;

import java.util.ArrayList;
import m2104.ile_interdite.util.*;

/**
 *
 * @author capelth
 */
public class Grille {

    private Tuile[][] tuiles;
    private ArrayList<Tuile> arrayTuiles = new ArrayList<>();
    private IleInterdite ileInterdite;

    public Grille(IleInterdite i, ArrayList<Tuile> tuiles) {
        this.tuiles = new Tuile[6][6];

        this.tuiles[0][2] = tuiles.get(0);
        tuiles.get(0).setPosition(2);
        this.tuiles[0][3] = tuiles.get(1);
        tuiles.get(1).setPosition(3);
        this.tuiles[1][1] = tuiles.get(2);
        tuiles.get(2).setPosition(7);
        this.tuiles[1][2] = tuiles.get(3);
        tuiles.get(3).setPosition(8);
        this.tuiles[1][3] = tuiles.get(4);
        tuiles.get(4).setPosition(9);
        this.tuiles[1][4] = tuiles.get(5);
        tuiles.get(5).setPosition(10);
        this.tuiles[2][0] = tuiles.get(6);
        tuiles.get(6).setPosition(12);
        this.tuiles[2][1] = tuiles.get(7);
        tuiles.get(7).setPosition(13);
        this.tuiles[2][2] = tuiles.get(8);
        tuiles.get(8).setPosition(14);
        this.tuiles[2][3] = tuiles.get(9);
        tuiles.get(9).setPosition(15);
        this.tuiles[2][4] = tuiles.get(10);
        tuiles.get(10).setPosition(16);
        this.tuiles[2][5] = tuiles.get(11);
        tuiles.get(11).setPosition(17);
        this.tuiles[3][0] = tuiles.get(12);
        tuiles.get(12).setPosition(18);
        this.tuiles[3][1] = tuiles.get(13);
        tuiles.get(13).setPosition(19);
        this.tuiles[3][2] = tuiles.get(14);
        tuiles.get(14).setPosition(20);
        this.tuiles[3][3] = tuiles.get(15);
        tuiles.get(15).setPosition(21);
        this.tuiles[3][4] = tuiles.get(16);
        tuiles.get(16).setPosition(22);
        this.tuiles[3][5] = tuiles.get(17);
        tuiles.get(17).setPosition(23);
        this.tuiles[4][1] = tuiles.get(18);
        tuiles.get(18).setPosition(25);
        this.tuiles[4][2] = tuiles.get(19);
        tuiles.get(19).setPosition(26);
        this.tuiles[4][3] = tuiles.get(20);
        tuiles.get(20).setPosition(27);
        this.tuiles[4][4] = tuiles.get(21);
        tuiles.get(21).setPosition(28);
        this.tuiles[5][2] = tuiles.get(22);
        tuiles.get(22).setPosition(32);
        this.tuiles[5][3] = tuiles.get(23);
        tuiles.get(23).setPosition(33);

        ileInterdite = i;

        initialiseArrayTuiles();
    }

    public ArrayList<Tuile> tuileAutour(Tuile t) {
        ArrayList<Tuile> tuilesAutour = new ArrayList<>();
        int[] c = new int[2];
        c = this.getCoordonnee(t);
        int i = c[0];
        int j = c[1];

        if (i != 0 && tuiles[i - 1][j] != null) {
            tuilesAutour.add(tuiles[i - 1][j]);
        }
        if (i != 5 && tuiles[i + 1][j] != null) {
            tuilesAutour.add(tuiles[i + 1][j]);
        }
        if (j != 0 && tuiles[i][j - 1] != null) {
            tuilesAutour.add(tuiles[i][j - 1]);
        }
        if (j != 5 && tuiles[i][j + 1] != null) {
            tuilesAutour.add(tuiles[i][j + 1]);
        }
        return tuilesAutour;
    }

    public ArrayList<Tuile> tuilesDispoDeplacer(Tuile t, Aventurier a) {
        ArrayList<Tuile> tuilesDispo = tuileAutour(t);
        int[] c = new int[2];
        c = this.getCoordonnee(t);
        int i = c[0];
        int j = c[1];
        if (a.getRole() == TypeRole.Explorateur) {
            if (i != 0) {
                if (j != 0 && tuiles[i - 1][j - 1] != null) {
                    tuilesDispo.add(tuiles[i - 1][j - 1]);
                }
                if (j != 5 && tuiles[i - 1][j + 1] != null) {
                    tuilesDispo.add(tuiles[i - 1][j + 1]);
                }
            }
            if (i != 5) {
                if (j != 0 && tuiles[i + 1][j - 1] != null) {
                    tuilesDispo.add(tuiles[i + 1][j - 1]);
                }
                if (j != 5 && tuiles[i + 1][j + 1] != null) {
                    tuilesDispo.add(tuiles[i + 1][j + 1]);
                }
            }
        }
        if (a.getRole() == TypeRole.Pilote && a.getPouvoirUtilise() == false) {
            tuilesDispo.clear();
            for (int x = 0; x < tuiles.length; x++) {
                for (int y = 0; y < tuiles.length; y++) {
                    if (tuiles[x][y] != null) {
                        tuilesDispo.add(tuiles[x][y]);
                    }
                }
            }
           tuilesDispo.remove(a.getTuile());
        }

       ArrayList<Tuile> TuileAsupr = new ArrayList<>();
        for (Tuile tu : tuilesDispo) {
            if (tu.getEtat().equals(TypeEtat.COULE)) {
                TuileAsupr.add(tu);
            }
        }
        
        tuilesDispo.removeAll(TuileAsupr);

        return tuilesDispo;
    }

    public int[] getCoordonnee(Tuile t) {
        int[] c = new int[2];
        int i = 0;
        int j = 0;

        while (i < 6) {

            while (j < 6) {
                if (tuiles[i][j] != null && tuiles[i][j].equals(t)) {
                    c[0] = i;
                    c[1] = j;
                    break;
                } else {
                    j++;
                }
            }
            i++;
            j = 0;
        }
        return c;
    }

    public ArrayList<Tuile> tuilesDisposAssecher(Tuile t, Aventurier a) {
        ArrayList<Tuile> tuilesDispo = tuileAutour(t);
        tuilesDispo.add(a.getTuile());
        int[] c = new int[2];
        c = this.getCoordonnee(t);
        int i = c[0];
        int j = c[1];
        if (a.getRole() == TypeRole.Explorateur) {
            if (i != 0) {
                if (j != 0 && tuiles[i - 1][j - 1] != null) {
                    tuilesDispo.add(tuiles[i - 1][j - 1]);
                }
                if (j != 5 && tuiles[i - 1][j + 1] != null) {
                    tuilesDispo.add(tuiles[i - 1][j + 1]);
                }
            }
            if (i != 5) {
                if (j != 0 && tuiles[i + 1][j - 1] != null) {
                    tuilesDispo.add(tuiles[i + 1][j - 1]);
                }
                if (j != 5 && tuiles[i + 1][j + 1] != null) {
                    tuilesDispo.add(tuiles[i + 1][j + 1]);
                }
            }
        }
        ArrayList<Tuile> TuileAsupr = new ArrayList<>();
        for (Tuile tu : tuilesDispo) {
            if (!tu.getEtat().equals(TypeEtat.INNONDE)) {
                TuileAsupr.add(tu);
            }
        }
        
        tuilesDispo.removeAll(TuileAsupr);

        return tuilesDispo;
    }

    public void assecher(Tuile t) {
        t.assecher();
    }

    public void innonde(CInondation ci) {
        ci.getTuile().inonde();
    }

    /**
     * @return the tuiles
     */
    public Tuile[][] getTuiles() {
        return tuiles;
    }

    /**
     * @param tuiles the tuiles to set
     */
    public void setTuiles(Tuile[][] tuiles) {
        this.tuiles = tuiles;
    }

    public void initialiseArrayTuiles() {
        ArrayList<Tuile> tuiles = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (this.tuiles[i][j] == null) {
                    tuiles.add(new Tuile(""));
                } else {
                    tuiles.add(this.tuiles[i][j]);
                }
            }
        }
        this.arrayTuiles = tuiles;
    }

    public ArrayList<Tuile> getArrayTuiles() {
        return arrayTuiles;
    }

    public IleInterdite getIleInterdite() {
        return ileInterdite;
    }
    
    public Tuile getTuile(String nomTuile){
        Tuile tu = new Tuile();
        this.initialiseArrayTuiles();
        for (Tuile t : this.getArrayTuiles()){
            if (nomTuile == t.getNomTuile()){
                tu = t;
            }
        }
        return tu;
    }

    public void positionTuile() {

    }
}
