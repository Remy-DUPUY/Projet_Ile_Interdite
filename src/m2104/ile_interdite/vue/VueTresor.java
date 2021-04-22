/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2104.ile_interdite.vue;

import java.awt.*;
import javax.swing.*;
import m2104.ile_interdite.modele.*;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author dupuyrem
 */
public class VueTresor extends JPanel {

    // attributs 
    private String urlTresor = "Images/view_tresor_n.png";
    private JFrame window;
    private IHM ihm;
    private JButton btn;
    private JPanel panelCentre;
    private ArrayList<JButton> listTresor = new ArrayList();
    
    // constructeur : 
    public VueTresor(IleInterdite ile) {

        window = new JFrame();

        // parametrage de la fenêtre : 
        window.setContentPane(new PanelFond(urlTresor,152, 160));
        window.setSize(152, 160);
        window.setTitle("Trésor");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int dimX = 800 + dim.width / 3 - window.getSize().width;
        // int dimY = 620 + dim.height / 3 - window.getSize().height / 2;
        int dimY = 620;
        window.setLocation(dimX, dimY);
        window.setUndecorated(true);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        

        // déclaration des panels : 
        JPanel mainPanel = new JPanel(new BorderLayout());
        panelCentre = new JPanel(new GridLayout(3, 3));

        // gestion de la dimension + opacité du panel du centre :
        panelCentre.setPreferredSize(new Dimension(152, 152));
        panelCentre.setOpaque(false);
        mainPanel.setOpaque(false);
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        
        
        // boucle principale pour ajouter les images au GridLayout : 
        int cmpt = 0;
        for (int i = 0; i < 9; i++) {
            if (i == 1 || i == 3 || i == 5 || i == 7) {
                
                // récupération des trésors restants : 
                Tresor nomTresorRestant = ile.getTresor(cmpt);
                cmpt = cmpt +1;
                // parametre de zoom sur les images de fond des boutons : 
                Image imgTuile;
                imgTuile = Toolkit.getDefaultToolkit().getImage("Images/tresors/"+ nomTresorRestant.getNomTresor() + ".png").getScaledInstance(50, 50, 50);//
                // création des boutons 
                btn = new JButton(new ImageIcon(imgTuile));
                btn.setPreferredSize(new Dimension(60, 60)); // taille des boutons 
                // gestion de l'opacité des boutons 
                btn.setOpaque(false);
                btn.setName(nomTresorRestant.getNomTresor());
                btn.setContentAreaFilled(false);
                btn.setBorderPainted(false);
                btn.setEnabled(false);
                panelCentre.add(btn);
                
                listTresor.add(btn);
                

            } else {
                panelCentre.add(new JLabel(""));
            }
        }

        
        window.add(mainPanel);
        window.setVisible(true); //Le faire en dernier !!!!
        
    }
    
    public void recuptresor(String nom){
        for(JButton j : listTresor){
            if(j.getName().equals(nom)){
                j.setEnabled(true);
            }
        }
    }
    
    public void fermer(){
        window.dispose();
    }
    
    public void lockFenetre(){
        window.setEnabled(false);
    }
    
}
