/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2104.ile_interdite.vue;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.JTextField;
import m2104.ile_interdite.util.Message;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Toolkit;
import m2104.ile_interdite.util.TypeAction;

/**
 *
 * @author Eric
 */
public class VueInscriptionJoueurs {

    private IHM ihm;
    
    private final JFrame fenetre;
    private javax.swing.JLabel titre;

    private JComboBox<Integer> choixNbJoueurs;
    private JLabel[] labelNomJoueurs = new JLabel[4];
    private JTextField[] saisieNomJoueurs = new JTextField[4];
    private String[] nomJoueurs;
    int nbJoueurs = 2; // 2 joueurs par default

    private final javax.swing.JLabel niveauEau;
    private JComboBox<String> choixNivEau;
    String nivEau = "Novice";

    private final JButton btnJouer = new JButton("Jouer");

    public VueInscriptionJoueurs(IHM ihm) {
        this.ihm = ihm;
        
        // Creation Fenetre
        fenetre = new JFrame();
        fenetre.setTitle("Inscription");
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(500, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        fenetre.setLocation(dim.width / 2 - fenetre.getSize().width / 2, dim.height / 2 - fenetre.getSize().height / 2);
        
        
        // Initialisation differente partie fenetre
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        fenetre.add(mainPanel);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Image
        String imgURL = "Images/logojeu.png";
        JLabel logoLabel = new JLabel(new ImageIcon(imgURL));
        topPanel.add(logoLabel, BorderLayout.NORTH);

        // titre
        /*
        titre = new JLabel();
        titre.setText("Bienvenue sur l'île interdite !");
        topPanel.add(titre, BorderLayout.CENTER);
         */
        
        // Inscription joueur
        JPanel CentrePanel = new JPanel(new GridLayout(9, 2));
        mainPanel.add(CentrePanel, BorderLayout.CENTER);

        CentrePanel.add(new JPanel()); // Une case vide
        CentrePanel.add(new JPanel());

        // nombre de joueurs
        choixNbJoueurs = new JComboBox<>(new Integer[]{2, 3, 4});
        CentrePanel.add(new JLabel("Nombre de joueurs :"));
        CentrePanel.add(choixNbJoueurs);

        // Saisie des noms de joueurs
        for (int i = 0; i < saisieNomJoueurs.length; i++) {
            saisieNomJoueurs[i] = new JTextField();
            labelNomJoueurs[i] = new JLabel("Nom du joueur No " + (i + 1) + " :");
            CentrePanel.add(labelNomJoueurs[i]);
            CentrePanel.add(saisieNomJoueurs[i]);
            labelNomJoueurs[i].setEnabled(i < 2);
            saisieNomJoueurs[i].setEnabled(i < 2);
        }

        CentrePanel.add(new JPanel()); // Une case vide
        CentrePanel.add(new JPanel());

        mainPanel.add(CentrePanel, BorderLayout.CENTER);
        fenetre.add(mainPanel);

        // Choix du nombre de joueurs
        choixNbJoueurs.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                nbJoueurs = (Integer) choixNbJoueurs.getSelectedItem();

                for (int i = 0; i < saisieNomJoueurs.length; i++) {
                    labelNomJoueurs[i].setEnabled(i < nbJoueurs);
                    saisieNomJoueurs[i].setEnabled(i < nbJoueurs);
                }
            }
        });

        // Niveau eau
        niveauEau = new javax.swing.JLabel();
        niveauEau.setText("Choisir niveau d'eau :");
        CentrePanel.add(niveauEau);

        String[] tableauNivEau = new String[]{"Novice", "Normal", "Elite", "Légendaire"};
        choixNivEau = new JComboBox<>(tableauNivEau);
        CentrePanel.add(choixNivEau);

        choixNivEau.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                nivEau = (String) choixNivEau.getSelectedItem();
            }
        });

        CentrePanel.add(new JPanel()); // Une case vide
        CentrePanel.add(new JPanel());

        // Bouton jouer 
        JPanel footerPanel = new JPanel(new GridBagLayout());
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        btnJouer.setPreferredSize(new Dimension(75, 30));

        btnJouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Message à envoyer
                // Demarer partie - nb de joueur - nom des joueurs - niveau Eau

                int niv = 0;
                if (nivEau == "Novice") {
                    niv = 1;
                } else if (nivEau == "Normal") {
                    niv = 2;
                } else if (nivEau == "Elite") {
                    niv = 3;
                } else if (nivEau == "Légendaire") {
                    niv = 4;
                } else {
                    System.out.println("Problème niveau Eau par defaut, le niveau sera novice");
                    niv = 1;
                }
                nomJoueurs = new String[nbJoueurs];
                
                for (int i = 0; i < nbJoueurs; i++) {
                    nomJoueurs[i] = saisieNomJoueurs[i].getText();
                }

                Message m = new Message(TypeAction.DEMARRER);
                m.nbJoueurs = nbJoueurs;
                m.nomJoueurs = nomJoueurs;
                m.nivEau = niv;
                ihm.btnJouer(m);

                fermer();
            }
        });
        
        footerPanel.add(btnJouer);
    }

    public void afficher() {
        this.fenetre.setVisible(true);
    }

    public void fermer() {
        this.fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre.dispose();
    }

    public String[] getNomJoueurs() {
        return Arrays.copyOf(this.nomJoueurs, this.nomJoueurs.length);
    }
    
    
}
