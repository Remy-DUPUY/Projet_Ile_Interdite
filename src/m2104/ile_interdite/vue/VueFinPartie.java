/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2104.ile_interdite.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import m2104.ile_interdite.controleur.Controleur;
import m2104.ile_interdite.util.Parameters;

/**
 *
 * @author gambiezj
 */
public class VueFinPartie {

    private JFrame fenetre;
    private JPanel mainPanel;
    private JPanel hautPanel;
    private JPanel milieu;
    private JPanel basPanel;
    private JLabel img;
    private Image imgTuile;
    private IHM ihm;

    public VueFinPartie(IHM ihm, boolean gagne) {
        initialiserFenetreFin(ihm, gagne);
    }

    public void initialiserFenetreFin(IHM ihm, boolean isGagne) {
        fenetre = new JFrame();
        fenetre.setSize(300, 150);

        // Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // int dimX = 0 + dim.width / 3 - 152;
        // int dimY = 0 + dim.height / 3 - fenetre.getSize().height / 2 - 161;
        // int dimY = 124;
        // fenetre.setLocation(dimX, dimY);
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // fenetre.setUndecorated(Parameters.UNDECORATED);
        fenetre.setTitle("Fin");
        fenetre.setResizable(Parameters.RESIZABLE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        fenetre.setLocation(dim.width / 2 - fenetre.getSize().width / 2, dim.height / 2 - fenetre.getSize().height / 2);
        fenetre.setAlwaysOnTop(true);

        mainPanel = new JPanel(new GridLayout(3, 1));
        hautPanel = new JPanel(new GridLayout(1, 2));
        milieu = new JPanel();
        basPanel = new JPanel(new GridLayout(1, 3));

        mainPanel.setOpaque(false);

        JLabel gagne = new JLabel();
        if (isGagne) {
            gagne.setText("Vous avez gagné !");
            imgTuile = Toolkit.getDefaultToolkit().getImage("Images/tresor.png").getScaledInstance(50, 50, 50);
            img = new JLabel(new ImageIcon(imgTuile));
        } else {
            gagne.setText("Vous avez perdu !");
            imgTuile = Toolkit.getDefaultToolkit().getImage("Images/noyade.jpg").getScaledInstance(50, 50, 50);
            img = new JLabel(new ImageIcon(imgTuile));
        }

        JLabel rejoue = new JLabel("Voulez-vous rejouer ?");

        JButton oui = new JButton("Oui");
        JButton non = new JButton("Non");

        oui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ihm.FermertousVue();
                fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
                fenetre.dispose();
                new Controleur();
            }
        });

        non.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

        hautPanel.add(gagne);
        hautPanel.add(img);
        milieu.add(rejoue);
        basPanel.add(oui);
        basPanel.add(new JLabel(""));
        basPanel.add(non);

        mainPanel.add(hautPanel, BorderLayout.NORTH);
        mainPanel.add(milieu, BorderLayout.CENTER);
        mainPanel.add(basPanel, BorderLayout.SOUTH);

        fenetre.add(mainPanel);
        fenetre.setVisible(true);
    }
}
