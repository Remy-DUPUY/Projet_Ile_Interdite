/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2104.ile_interdite.vue;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import m2104.ile_interdite.util.TypeRole;

/**
 *
 * @author capelth
 */
public class PanelFond extends JPanel {

    private Image image;

    public PanelFond(TypeRole r) {
        if (r == TypeRole.Explorateur) {
            image = Toolkit.getDefaultToolkit().getImage("Images/personnages/explorateur.png");
        } else if (r == TypeRole.Ingenieur) {
            image = Toolkit.getDefaultToolkit().getImage("Images/personnages/ingenieur.png");
        } else if (r == TypeRole.Messager) {
            image = Toolkit.getDefaultToolkit().getImage("Images/personnages/messager.png");
        } else if (r == TypeRole.Navigateur) {
            image = Toolkit.getDefaultToolkit().getImage("Images/personnages/navigateur.png");
        } else if (r == TypeRole.Pilote) {
            image = Toolkit.getDefaultToolkit().getImage("Images/personnages/pilote.png");
        } else if (r == TypeRole.Plongeur) {
            image = Toolkit.getDefaultToolkit().getImage("Images/personnages/plongeur.png");
        }
    }

    public PanelFond(String urlImage) {
        image = Toolkit.getDefaultToolkit().getImage(urlImage);
    }

    public PanelFond(String urlImage, int w, int h) {
        image = Toolkit.getDefaultToolkit().getImage(urlImage).getScaledInstance(w, h, w);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, 0, 0, null);
        repaint();
    }
}
