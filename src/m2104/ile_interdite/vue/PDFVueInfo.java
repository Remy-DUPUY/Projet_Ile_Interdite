/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2104.ile_interdite.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import m2104.ile_interdite.util.Parameters;

/**
 *
 * @author pommatar
 */
public class PDFVueInfo {
    //private final IHM ihm;

    private String urlImgs = "Images/texture.png";
    private JFrame fenetre;
    private JButton fermer;
    //private IHM ihm;

    public PDFVueInfo(){
        initialiserFenetreBouton();
    }

    public void initialiserFenetreBouton() {
        fenetre = new JFrame();
        fenetre.setContentPane(new PanelFond(urlImgs, 30, 30));
        fenetre.setSize(30, 30);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int dimX = 800 + dim.width / 3 - fenetre.getSize().width + 30;

        fenetre.setLocation(dimX, 30);

        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre.setUndecorated(Parameters.UNDECORATED);
        fenetre.setResizable(Parameters.RESIZABLE);
        fenetre.setTitle("Info régles");

        JLabel text = new JLabel("?");
        text.setForeground(Color.BLUE);
        JButton fermer = new JButton();
        fermer.add(text);
        fermer.setOpaque(false);
        fermer.setEnabled(false);

        fermer.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                try {

                    //Runtime.getRuntime().exec("Images/regle.pdf");
                    java.awt.Desktop.getDesktop().open(new File("Images/regle.pdf"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            @Override public void mousePressed(MouseEvent arg0) {}

            @Override public void mouseReleased(MouseEvent arg0) {}

            @Override public void mouseEntered(MouseEvent arg0) {}

            @Override public void mouseExited(MouseEvent arg0) {}
        });
        fermer.setBorderPainted(false);
        fenetre.add(fermer, BorderLayout.CENTER);
        fenetre.setVisible(true);
    }
    
    public void fermer() {
        fenetre.dispose();
    }
    
    public void lockFenetre(){
        fenetre.setEnabled(false);
    }    
}
