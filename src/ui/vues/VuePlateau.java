import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class VuePlateau extends JPanel {
    
    private Plateau plateau;
    private int hauteur;
    private int largeur;
	private VueTuile[][] vues;

    public VuePlateau(int ha, int la, Plateau p) {
        hauteur = ha;
        largeur = la;
		vues = new VueTuile[ha][la];
        plateau = p;
        
        setLayout(null);

        for (int i = 0; i < p.getHauteur(); i++) {
            for (int j = 0; j < p.getLargeur(); j++) {
                vues[i][j] = new VueTuile(p.getTuile(i+1, j+1));
                vues[i][j].setBounds(i*80, j*80, 80, 80);
                add(vues[i][j]);
            }
        }
    }
}
