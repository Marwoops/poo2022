import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class VuePlateau extends JPanel {
    
    private Plateau plateau;
    private int hauteur;
    private int largeur;
	private VueTuile[][] vues;

    public VuePlateau(int ha, int la, Plateau p, MouseListener controleur, boolean estCarcassonne) {
        hauteur = ha;
        largeur = la;
        plateau = p;
		vues = new VueTuile[ha][la];

        setLayout(new GridLayout(p.getHauteur(), p.getLargeur()));

        for (int i = 0; i < p.getHauteur(); i++) {
            for (int j = 0; j < p.getLargeur(); j++) {
				VueTuile vue = (estCarcassonne) ? new VueParcelle(p.getTuile(i, j), i, j, controleur) : new VueDomino(p.getTuile(i,j), i, j, controleur);
				vues[i][j] = vue;
                add(vue);
            }
        }
    }

	public void setTuile(int x, int y, Tuile t) {
		vues[x][y].setTuile(t);
	}
}
