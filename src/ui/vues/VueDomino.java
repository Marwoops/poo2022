import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class VueDomino extends VueTuile {

	private static Color couleur_arriere = new Color(145, 230, 180);
	private static Color couleur_avant = new Color(213, 242, 225);
	private VueCase[][] grille;

	public VueDomino(Tuile t, int x, int y, MouseListener controleur) {
		super(t, x, y, controleur);
		Domino d = (Domino) t;
		int longueur =  5; // 3 valeurs + 2 espace

		grille = new VueCase[longueur][longueur];
		setLayout(new GridLayout(longueur, longueur));

		for (int i = 0; i < longueur; i++) {
			for (int j = 0; j < longueur; j++) {
				VueCase vue = new VueCase(-1);
				grille[i][j] = vue;
				add(vue);
			}
		}
	}

	public void updateVue() {
		Domino d = (Domino) getTuile();
		if (d == null) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					grille[i][j].setValeur(-1);
					grille[i][j].updateBackground();
				}
			}
			return;
		}

		setOrientation(d.getOrientation());
		Rangee rH = (Rangee) d.getCote(0);
		Rangee rD = (Rangee) d.getCote(1);
		Rangee rB = (Rangee) d.getCote(2);
		Rangee rG = (Rangee) d.getCote(3);

		for (int i = 0; i < 3; i++) {
			grille[0][1+i].setValeur(rH.getPoint(i));
			grille[1+i][0].setValeur(rG.getPoint(2-i));
			grille[4][1+i].setValeur(rB.getPoint(2-i));
			grille[1+i][4].setValeur(rD.getPoint(i));
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				grille[i][j].updateBackground();
			}
		}
	}

	private class VueCase extends JLabel {
		private int valeur;

		public VueCase(int val) {
			valeur = val;
			updateText();
		}

		public void setValeur(int val) {
			valeur = val;
			updateText();
		}

		public void updateText() {
			setText((valeur == -1) ? "" : valeur + "");
		}

		public void updateBackground() {
			if (estVide()) {
				setOpaque(false);
				return;
			}

			setOpaque(true);
			if (valeur == -1)
				setBackground(couleur_arriere);
			else
				setBackground(couleur_avant);
		}
	}
}
