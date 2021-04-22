/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2104.ile_interdite.vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import m2104.ile_interdite.modele.Aventurier;

/**
 *
 * @author capelth
 */
public class VueHeader {

    private JFrame window;
    private JButton b1, b2, b3, b4;
    private Aventurier aventurierChoisi;

    public VueHeader(ArrayList<Aventurier> listA) {

        window = new JFrame();
        JPanel panelprinc = new JPanel(new GridLayout(1, 6));
        panelprinc.setOpaque(false);
        panelprinc.setSize(800, 124);
        window.setTitle("Header");
        window.setSize(800, 124);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int dimX = 0 + dim.width / 3 - 152;
        // int dimY = 0 + dim.height / 3 - 159;
        int dimY = 0;
        window.setLocation(dimX, dimY);

        window.setContentPane(new PanelFond("Images/VueHeader.png"));

        if (listA.size() == 2) {

            Dimension d = new Dimension(98, 116);
            Image imgPerso1;
            imgPerso1 = Toolkit.getDefaultToolkit().getImage("Images/personnages/" + listA.get(0).getRole() + ".png").getScaledInstance(93, 116, 85);

            Image imgPerso2;
            imgPerso2 = Toolkit.getDefaultToolkit().getImage("Images/personnages/" + listA.get(1).getRole() + ".png").getScaledInstance(90, 116, 85);

            b1 = new JButton();
            b2 = new JButton(new ImageIcon(imgPerso1));
            b3 = new JButton(new ImageIcon(imgPerso2));
            b4 = new JButton();

            b1.setPreferredSize(d);
            b2.setPreferredSize(d);
            b3.setPreferredSize(d);
            b4.setPreferredSize(d);

            b1.setOpaque(false);
            b1.setContentAreaFilled(false);
            b1.setBorderPainted(false);

            b2.setOpaque(false);
            b2.setContentAreaFilled(false);
            b2.setBorderPainted(false);

            b3.setOpaque(false);
            b3.setContentAreaFilled(false);
            b3.setBorderPainted(false);

            b4.setOpaque(false);
            b4.setContentAreaFilled(false);
            b4.setBorderPainted(false);

            JPanel panelButton = new JPanel(new BorderLayout());
            panelButton.setOpaque(false);
            b2.add(panelButton);
            JLabel nom = new JLabel(listA.get(0).getNom(), SwingConstants.RIGHT);
            nom.setForeground(Color.WHITE);
            nom.setSize(200, 200);

            panelButton.add(nom, BorderLayout.SOUTH);

            JPanel panelButton3 = new JPanel(new BorderLayout());
            panelButton3.setOpaque(false);
            b3.add(panelButton3);
            JLabel nom3 = new JLabel(listA.get(1).getNom(), SwingConstants.RIGHT);
            nom3.setForeground(Color.WHITE);
            nom3.setSize(200, 200);

            panelButton3.add(nom3, BorderLayout.SOUTH);

            //ActionListeners
            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    aventurierChoisi = listA.get(0);
                }
            });

            b3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    aventurierChoisi = listA.get(1);
                }
            });

            panelprinc.add(b1);
            panelprinc.add(b2);
            panelprinc.add(new JLabel(" "));
            panelprinc.add(new JLabel(" "));
            panelprinc.add(new JLabel(" "));
            panelprinc.add(b3);
            panelprinc.add(b4);
            window.add(panelprinc);

        } else if (listA.size() == 3) {

            Dimension d = new Dimension(98, 116);
            Image imgPerso1;
            imgPerso1 = Toolkit.getDefaultToolkit().getImage("Images/personnages/" + listA.get(0).getRole() + ".png").getScaledInstance(88, 116, 85);

            Image imgPerso2;
            imgPerso2 = Toolkit.getDefaultToolkit().getImage("Images/personnages/" + listA.get(1).getRole() + ".png").getScaledInstance(93, 116, 85);

            Image imgPerso3;
            imgPerso3 = Toolkit.getDefaultToolkit().getImage("Images/personnages/" + listA.get(2).getRole() + ".png").getScaledInstance(85, 116, 85);

            b1 = new JButton(new ImageIcon(imgPerso1));
            b2 = new JButton(new ImageIcon(imgPerso2));
            b3 = new JButton(new ImageIcon(imgPerso3));
            b4 = new JButton();

            b1.setPreferredSize(d);
            b2.setPreferredSize(d);
            b3.setPreferredSize(d);
            b4.setPreferredSize(d);

            b1.setOpaque(false);
            b1.setContentAreaFilled(false);
            b1.setBorderPainted(false);

            b2.setOpaque(false);
            b2.setContentAreaFilled(false);
            b2.setBorderPainted(false);

            b3.setOpaque(false);
            b3.setContentAreaFilled(false);
            b3.setBorderPainted(false);

            b4.setOpaque(false);
            b4.setContentAreaFilled(false);
            b4.setBorderPainted(false);

            JPanel panelButton = new JPanel(new BorderLayout());
            panelButton.setOpaque(false);
            b1.add(panelButton);
            JLabel nom = new JLabel(listA.get(0).getNom(), SwingConstants.RIGHT);
            nom.setForeground(Color.WHITE);
            nom.setSize(200, 200);

            panelButton.add(nom, BorderLayout.SOUTH);

            JPanel panelButton2 = new JPanel(new BorderLayout());
            panelButton2.setOpaque(false);
            b2.add(panelButton2);
            JLabel nom2 = new JLabel(listA.get(1).getNom(), SwingConstants.RIGHT);
            nom2.setForeground(Color.WHITE);
            nom2.setSize(200, 200);

            panelButton2.add(nom2, BorderLayout.SOUTH);

            JPanel panelButton3 = new JPanel(new BorderLayout());
            panelButton3.setOpaque(false);
            b3.add(panelButton3);
            JLabel nom3 = new JLabel(listA.get(2).getNom(), SwingConstants.RIGHT);
            nom3.setForeground(Color.WHITE);
            nom3.setSize(200, 200);

            panelButton3.add(nom3, BorderLayout.SOUTH);

            //ActionListeners
            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    aventurierChoisi = listA.get(0);
                }
            });

            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    aventurierChoisi = listA.get(1);
                }
            });

            b3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    aventurierChoisi = listA.get(2);
                }
            });

            panelprinc.add(b1);
            panelprinc.add(b2);
            panelprinc.add(new JLabel(" "));
            panelprinc.add(new JLabel(" "));
            panelprinc.add(new JLabel(" "));
            panelprinc.add(b3);
            panelprinc.add(b4);
            window.add(panelprinc);

        } else if (listA.size() == 4) {

            Dimension d = new Dimension(98, 116);
            Image imgPerso1;
            imgPerso1 = Toolkit.getDefaultToolkit().getImage("Images/personnages/" + listA.get(0).getRole() + ".png").getScaledInstance(88, 116, 85);

            Image imgPerso2;
            imgPerso2 = Toolkit.getDefaultToolkit().getImage("Images/personnages/" + listA.get(1).getRole() + ".png").getScaledInstance(91, 116, 85);

            Image imgPerso3;
            imgPerso3 = Toolkit.getDefaultToolkit().getImage("Images/personnages/" + listA.get(2).getRole() + ".png").getScaledInstance(83, 116, 85);

            Image imgPerso4;
            imgPerso4 = Toolkit.getDefaultToolkit().getImage("Images/personnages/" + listA.get(3).getRole() + ".png").getScaledInstance(92, 116, 85);

            b1 = new JButton(new ImageIcon(imgPerso1));
            b2 = new JButton(new ImageIcon(imgPerso2));
            b3 = new JButton(new ImageIcon(imgPerso3));
            b4 = new JButton(new ImageIcon(imgPerso4));

            b1.setPreferredSize(d);
            b2.setPreferredSize(d);
            b3.setPreferredSize(d);
            b4.setPreferredSize(d);

            b1.setOpaque(false);
            b1.setContentAreaFilled(false);
            b1.setBorderPainted(false);

            b2.setOpaque(false);
            b2.setContentAreaFilled(false);
            b2.setBorderPainted(false);

            b3.setOpaque(false);
            b3.setContentAreaFilled(false);
            b3.setBorderPainted(false);

            b4.setOpaque(false);
            b4.setContentAreaFilled(false);
            b4.setBorderPainted(false);

            JPanel panelButton = new JPanel(new BorderLayout());
            panelButton.setOpaque(false);
            b1.add(panelButton);
            JLabel nom = new JLabel(listA.get(0).getNom(), SwingConstants.RIGHT);
            nom.setForeground(Color.WHITE);
            nom.setSize(200, 200);

            panelButton.add(nom, BorderLayout.SOUTH);

            JPanel panelButton2 = new JPanel(new BorderLayout());
            panelButton2.setOpaque(false);
            b2.add(panelButton2);
            JLabel nom2 = new JLabel(listA.get(1).getNom(), SwingConstants.RIGHT);
            nom2.setForeground(Color.WHITE);
            nom2.setSize(200, 200);

            panelButton2.add(nom2, BorderLayout.SOUTH);

            JPanel panelButton3 = new JPanel(new BorderLayout());
            panelButton3.setOpaque(false);
            b3.add(panelButton3);
            JLabel nom3 = new JLabel(listA.get(2).getNom(), SwingConstants.RIGHT);
            nom3.setForeground(Color.WHITE);
            nom3.setSize(200, 200);

            panelButton3.add(nom3, BorderLayout.SOUTH);

            JPanel panelButton4 = new JPanel(new BorderLayout());
            panelButton4.setOpaque(false);
            b4.add(panelButton4);
            JLabel nom4 = new JLabel(listA.get(3).getNom(), SwingConstants.RIGHT);
            nom4.setForeground(Color.WHITE);
            nom4.setSize(200, 200);

            panelButton4.add(nom4, BorderLayout.SOUTH);

            //ActionListeners
            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    aventurierChoisi = listA.get(0);
                }
            });

            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    aventurierChoisi = listA.get(1);
                }
            });

            b3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    aventurierChoisi = listA.get(2);
                }
            });

            b3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    aventurierChoisi = listA.get(3);
                }
            });

            panelprinc.add(b1);
            panelprinc.add(b2);
            panelprinc.add(new JLabel(" "));
            panelprinc.add(new JLabel(" "));
            panelprinc.add(new JLabel(" "));
            panelprinc.add(b3);
            panelprinc.add(b4);
            window.add(panelprinc);

        }

        window.setUndecorated(true);
        window.setVisible(true);
    }

    public Aventurier getAventurierChoisi() {
        return aventurierChoisi;
    }
    
    public void fermer(){
        window.dispose();
    }
    
    public void lockFenetre(){
        window.setEnabled(false);
    }
}
