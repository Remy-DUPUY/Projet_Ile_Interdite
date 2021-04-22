/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2104.ile_interdite.vue;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import m2104.ile_interdite.util.Parameters;
import m2104.ile_interdite.util.*;

/**
 *
 * @author gambiezj
 */
public class VueBoutons {

    private final IHM ihm;
    private JPanel mainPanel;
    private JPanel centrePanel;
    private String urlImgs = "Images/texture.png";
    private JFrame fenetre;
    private JButton btnAller;
    private JButton btnAssecher;
    private JButton btnDonner;
    private JButton btnPrendre;
    private JButton btnDeplacer;
    private JButton btnTerminer;

    public VueBoutons(IHM ihm) {
        initialiserFenetreBouton();
        initialiserBouton();
        this.ihm = ihm;
    }

    public void initialiserFenetreBouton() {
        fenetre = new JFrame();
        fenetre.setContentPane(new PanelFond(urlImgs, 152, 159));
        fenetre.setSize(152, 159);
        fenetre.setTitle("Boutons");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int dimX = 800 + dim.width / 3 - fenetre.getSize().width;
        // int dimY = 0 + dim.height / 3 - fenetre.getSize().height / 2 - 161;
        int dimY = 0;
        fenetre.setLocation(dimX, dimY);
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre.setUndecorated(Parameters.UNDECORATED);
        fenetre.setResizable(Parameters.RESIZABLE);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(152, 159));
        mainPanel.setOpaque(false);

        centrePanel = new JPanel(new GridLayout(3, 2));
        centrePanel.setOpaque(false);

        mainPanel.add(centrePanel, BorderLayout.CENTER);
        fenetre.add(mainPanel, BorderLayout.CENTER);
    }

    public void initialiserBouton() {
        this.btnAller = creerBouton(1, "Déplacer", Utils.Commandes.BOUGER);
        btnAller.setBorderPainted(false);
        centrePanel.add(btnAller);

        this.btnAssecher = creerBouton(2, "Assécher", Utils.Commandes.ASSECHER);
        btnAssecher.setBorderPainted(false);
        centrePanel.add(btnAssecher);

        this.btnDonner = creerBouton(3, "Donner", Utils.Commandes.DONNER);
        btnDonner.setBorderPainted(false);
        centrePanel.add(btnDonner);

        this.btnPrendre = creerBouton(4, "Prendre", Utils.Commandes.RECUPERER_TRESOR);
        btnPrendre.setBorderPainted(false);
        centrePanel.add(btnPrendre);
        
        this.btnDeplacer = creerBouton(5, "Défausse", Utils.Commandes.DEPLACER);
        btnDeplacer.setBorderPainted(false);
        centrePanel.add(btnDeplacer);

        this.btnTerminer = creerBouton(6, "Terminer", Utils.Commandes.TERMINER);
        btnTerminer.setBorderPainted(false);
        centrePanel.add(btnTerminer);
        
    }

    private JButton creerBouton(Integer numBouton, String libelle, Utils.Commandes commande) {

        JLabel action = new JLabel("  " +libelle);
        action.setForeground(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 11);
        action.setFont(font);
        JButton bouton = new JButton();
        bouton.add(action);
        bouton.setOpaque(false);
        bouton.setEnabled(false);
        bouton.setBorder(new MatteBorder(0, 0, (numBouton <= 3 ? 1 : 0), (numBouton % 3 != 0 ? 1 : 0), Color.BLACK));

        bouton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JButton btnClique = (JButton) e.getSource();
                btnAller.setForeground(Color.BLACK);
                btnAssecher.setForeground(Color.BLACK);
                btnDonner.setForeground(Color.BLACK);
                btnPrendre.setForeground(Color.BLACK);

                if (btnClique == btnAller || btnClique == btnAssecher || btnClique == btnDonner || btnClique == btnPrendre) {
                }
                switch (commande) {
                    case BOUGER:
                        Message m1 = new Message();
                        m1.type = TypeAction.BOUGER;
                        ihm.notifierObservateurs(m1);
                        break;
                    case ASSECHER:
                        Message m2 = new Message();
                        m2.type = TypeAction.ASSECHER;
                        ihm.notifierObservateurs(m2);
                        break;
                    case DONNER:
                        ihm.donnerCarte();
                        break;
                    case RECUPERER_TRESOR:
                        Message m4 = new Message();
                        m4.type = TypeAction.RECUPERER_TRESOR;
                        ihm.notifierObservateurs(m4);
                        break;
                    case DEPLACER:
                        ihm.defausserCarte();
                        break;
                    case TERMINER:
                        Message m6 = new Message();
                        m6.type = TypeAction.TERMINER;
                        ihm.notifierObservateurs(m6);
                        break;
                }
            }

            @Override public void mousePressed(MouseEvent e) {}

            @Override public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton btn = (JButton) e.getSource();
                btn.setFont(btn.getFont().deriveFont(Font.BOLD));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton btn = (JButton) e.getSource();
                btn.setFont(btn.getFont().deriveFont(Font.PLAIN));
            }
        });
        return bouton;
    }

    public void afficher() {
        this.fenetre.setVisible(true);
    }

    public void fermer() {
        fenetre.dispose();
    }
    
    public void lockFenetre(){
        fenetre.setEnabled(false);
    }

}
