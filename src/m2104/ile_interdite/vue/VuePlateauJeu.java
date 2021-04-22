/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2104.ile_interdite.vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import m2104.ile_interdite.modele.*;
import m2104.ile_interdite.util.*;
import java.util.ArrayList;

public class VuePlateauJeu extends JPanel {

    private String urlImgs = "Images/final_Jeu.png";
    private JFrame fenetre;

    private JPanel mainPanel;
    private JPanel centrePanel;

    private ArrayList<Aventurier> aventuriers;
    private ArrayList<Tuile> arrayTuiles = new ArrayList<>();

    private IHM ihm;
    Grille grille;
    
    private JLabel nbAction;

    public VuePlateauJeu(Grille g, IHM ihm, int nbActions) {
        
        this.arrayTuiles = g.getArrayTuiles();
        this.aventuriers = g.getIleInterdite().getAventuriers();
        initialiserFenetreJeu();
        intitialiserPlateauJeu(g, nbActions);
        this.ihm = ihm;
        grille = g;
    }

    public void initialiserFenetreJeu() {
        fenetre = new JFrame();
        fenetre.setContentPane(new PanelFond(urlImgs, 800, 656));
        fenetre.setSize(800, 656);
        fenetre.setTitle("Plateau Jeu");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int dimX = 0 + dim.width / 3 - 152;
        // int dimY = 0 + dim.height / 3 - fenetre.getSize().height / 2 - 161;
        int dimY = 124;
        fenetre.setLocation(dimX, dimY);
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre.setUndecorated(Parameters.UNDECORATED);
        fenetre.setResizable(Parameters.RESIZABLE);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);

        centrePanel = new JPanel(new GridLayout(6, 6));
        centrePanel.setPreferredSize(new Dimension(750, 650));
        centrePanel.setOpaque(false);

        mainPanel.add(centrePanel, BorderLayout.CENTER);
        fenetre.add(mainPanel);
    }

    public void intitialiserPlateauJeu(Grille g, int nbActionsR) {
        centrePanel.removeAll();
        centrePanel.validate();
        JButton btn = new JButton();
        Image imgTuile;
        String nomTuile;

        for (int i = 0; i < 36; i++) {

            if ((i < 2) || (i > 3 && i < 7) || (i == 11) || (i == 29) || (i == 31) || (i > 33)) {
                JLabel label = new JLabel("", SwingConstants.CENTER);
                centrePanel.add(label);
            } else if (i == 30) {
                JPanel pionCourant = dessinerPion(g.getIleInterdite().getJoueurCourant());
                
                centrePanel.add(pionCourant);
            } else if (i == 24){
                JPanel panelNbActions = new JPanel(new BorderLayout());
                nbAction = new JLabel("Nombre d'Actions : " + nbActionsR);
                Font font = new Font("Arial", Font.BOLD, 10);
                nbAction.setFont(font);
                panelNbActions.add(nbAction, BorderLayout.SOUTH);
                panelNbActions.setOpaque(false);
                centrePanel.add(panelNbActions);
            } else {
                // redimentionne la tuile
                nomTuile = arrayTuiles.get(i).getNomTuile();

                
                if (arrayTuiles.get(i).getEtat() == TypeEtat.COULE) {
                    btn = new JButton();
                } else {
                    if (arrayTuiles.get(i).getEtat() == TypeEtat.INNONDE) {
                        imgTuile = Toolkit.getDefaultToolkit().getImage("Images/tuiles/" + nomTuile + "_Inonde.png").getScaledInstance(120, 100, 120);
                        btn = new JButton(new ImageIcon(imgTuile));
                    } else {
                        imgTuile = Toolkit.getDefaultToolkit().getImage("Images/tuiles/" + nomTuile + ".png").getScaledInstance(120, 100, 120);
                        btn = new JButton(new ImageIcon(imgTuile));
                    }
                }

                if (!arrayTuiles.get(i).getAventuriers().isEmpty()) {
                    JPanel centrePionP = dessinerPion(arrayTuiles.get(i).getAventuriers().get(0));
                    btn.add(centrePionP);
                }

                btn.setPreferredSize(new Dimension(85, 85));
                btn.setOpaque(false);
                btn.setContentAreaFilled(false);
                btn.setBorderPainted(false);
                centrePanel.add(btn);
            }
        }
        centrePanel.revalidate();
        centrePanel.updateUI();
    }

    public void actualiserPlateauJeu(ArrayList<Tuile> tDispos, Grille g, int nbActionsR) {
        centrePanel.removeAll();
        centrePanel.validate();
        JButton btn = new JButton();
        Image imgTuile;
        String nomTuile;

        for (int i = 0; i < 36; i++) {
            
            Aventurier joueurCourant = g.getIleInterdite().getJoueurCourant();
            JPanel pionCourant;
            
            
            if ((i < 2) || (i > 3 && i < 7) || (i == 11) || (i == 29) || (i == 31) || (i > 33)) {
                JLabel label = new JLabel("", SwingConstants.CENTER);
                centrePanel.add(label);
            } else if (i == 30) {
                pionCourant = dessinerPion(g.getIleInterdite().getJoueurCourant());
                centrePanel.add(pionCourant);
            
            } else if (i == 24){
                JPanel panelNbActions = new JPanel(new BorderLayout());
                nbAction = new JLabel("Nombre d'Actions : " + nbActionsR);
                Font font = new Font("Arial", Font.BOLD, 10);
                nbAction.setFont(font);
                panelNbActions.add(nbAction, BorderLayout.SOUTH);
                panelNbActions.setOpaque(false);
                centrePanel.add(panelNbActions);
            } else {
                // redimentionne la tuile
                nomTuile = arrayTuiles.get(i).getNomTuile();

                if (arrayTuiles.get(i).getEtat() == TypeEtat.COULE) {
                    btn = new JButton();
                } else {
                    if (arrayTuiles.get(i).getEtat() == TypeEtat.INNONDE) {
                        imgTuile = Toolkit.getDefaultToolkit().getImage("Images/tuiles/" + nomTuile + "_Inonde.png").getScaledInstance(120, 100, 120);
                        btn = new JButton(new ImageIcon(imgTuile));
                    } else {
                        imgTuile = Toolkit.getDefaultToolkit().getImage("Images/tuiles/" + nomTuile + ".png").getScaledInstance(120, 100, 120);
                        btn = new JButton(new ImageIcon(imgTuile));
                    }

                }
                if (tDispos.contains(arrayTuiles.get(i))) {
                    Tuile t = arrayTuiles.get(i);
                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                            Message m = new Message();
                            m.type = TypeAction.BOUGERPION;
                            m.tuile = t;
                            ihm.notifierObservateurs(m);
                        }
                    });

                } else {
                    btn.setEnabled(false);
                }

                if (!arrayTuiles.get(i).getAventuriers().isEmpty()) {
                    JPanel centrePionP = dessinerPion(arrayTuiles.get(i).getAventuriers().get(0));
                    btn.add(centrePionP);
                }
                btn.setPreferredSize(new Dimension(85, 85));
                btn.setOpaque(false);
                btn.setContentAreaFilled(false);
                btn.setBorderPainted(false);
                centrePanel.add(btn);
            }
        }
        centrePanel.revalidate();
        centrePanel.updateUI();
    }
    
      public void actualiserPlateauJeuAssecher(ArrayList<Tuile> tDispos, Grille g, int nbActionsR) {
        centrePanel.removeAll();
        centrePanel.validate();
        JButton btn = new JButton();
        Image imgTuile;
        String nomTuile;

        for (int i = 0; i < 36; i++) {
            
            Aventurier joueurCourant = g.getIleInterdite().getJoueurCourant();
            JPanel pionCourant;
            
            
            if ((i < 2) || (i > 3 && i < 7) || (i == 11) || (i == 29) || (i == 31) || (i > 33)) {
                JLabel label = new JLabel("", SwingConstants.CENTER);
                centrePanel.add(label);
            } else if (i == 30) {
                pionCourant = dessinerPion(g.getIleInterdite().getJoueurCourant());
                centrePanel.add(pionCourant);
            } else if (i == 24){
                JPanel panelNbActions = new JPanel(new BorderLayout());
                nbAction = new JLabel("Nombre d'Actions : " + nbActionsR);
                Font font = new Font("Arial", Font.BOLD, 10);
                nbAction.setFont(font);
                panelNbActions.add(nbAction, BorderLayout.SOUTH);
                panelNbActions.setOpaque(false);
                centrePanel.add(panelNbActions); 
            } else {
                // redimentionne la tuile
                nomTuile = arrayTuiles.get(i).getNomTuile();

                if (arrayTuiles.get(i).getEtat() == TypeEtat.COULE) {
                    btn = new JButton();
                } else {
                    if (arrayTuiles.get(i).getEtat() == TypeEtat.INNONDE) {
                        imgTuile = Toolkit.getDefaultToolkit().getImage("Images/tuiles/" + nomTuile + "_Inonde.png").getScaledInstance(120, 100, 120);
                        btn = new JButton(new ImageIcon(imgTuile));
                    } else {
                        imgTuile = Toolkit.getDefaultToolkit().getImage("Images/tuiles/" + nomTuile + ".png").getScaledInstance(120, 100, 120);
                        btn = new JButton(new ImageIcon(imgTuile));
                    }

                }
                if (tDispos.contains(arrayTuiles.get(i))) {
                    Tuile t = arrayTuiles.get(i);
                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                            Message m = new Message();
                            m.type = TypeAction.ASSECHER_TUILE;
                            m.tuile = t;
                            ihm.notifierObservateurs(m);
                        }
                    });

                } else {
                    btn.setEnabled(false);
                }

                if (!arrayTuiles.get(i).getAventuriers().isEmpty()) {
                    JPanel centrePionP = dessinerPion(arrayTuiles.get(i).getAventuriers().get(0));
                    btn.add(centrePionP);
                }
                btn.setPreferredSize(new Dimension(85, 85));
                btn.setOpaque(false);
                btn.setContentAreaFilled(false);
                btn.setBorderPainted(false);
                centrePanel.add(btn);
            }
        }
        centrePanel.revalidate();
        centrePanel.updateUI();
    }

    public JPanel dessinerPion(Aventurier a) {
        JPanel centrePionsPanel;
        centrePionsPanel = new JPanel(new BorderLayout());
        centrePionsPanel.setOpaque(false);

        JLabel pionLabel = new JLabel();
        Image imgPion;

        TypePion typeP = a.getPion();

        // on va boucler
        String imgURL = "Images/pions/" + a.getPion().name() + ".png";
        imgPion = Toolkit.getDefaultToolkit().getImage(imgURL).getScaledInstance(50, 80, 50);
        pionLabel = new JLabel(new ImageIcon(imgPion));
        pionLabel.setPreferredSize(new Dimension(50, 50));
        pionLabel.setOpaque(false);

        centrePionsPanel.add(pionLabel);
        return centrePionsPanel;
    }

    public void afficheTuilesDispos(ArrayList<Tuile> tDispos) {

    }

    public void afficher() {
        this.fenetre.setVisible(true);
    }
    
    public void fermer(){
        fenetre.dispose();
    }
    
    public void lockFenetre(){
        fenetre.setEnabled(false);
    }
    
}
