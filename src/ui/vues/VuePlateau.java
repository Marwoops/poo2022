import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class VuePlateau extends JPanel {
    
    private Plateau plateau;
    private int hauteur;
    private int largeur;

    public VuePlateau(int ha, int la, Plateau p) {
        hauteur = ha;
        largeur = la;

        plateau = p;
        
        setLayout(null);

        for (int i = 0; i < p.getHauteur(); i++) {
            for (int j = 0; j < p.getLargeur(); j++) {
                JLabel t = new VueTuile(p.getTuile(i+1, j+1));
                t.setBounds(i*80, j*80, 80, 80);
                add(t);
            }
        }
    }
}
