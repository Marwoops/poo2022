import java.util.LinkedList;
import java.awt.Color;

public class Joueur {

	private Partie partie;
	private Tuile courante;
	private int score;
	private boolean estIA;
	private boolean abandon;
	private Color couleur;

	public Joueur(Partie p, boolean estIA) {
		partie = p;
		this.estIA = estIA;
		score = 0;
	}

	public boolean estIA() {
		return estIA;
	}

	public Tuile getCourante(){
		return courante;
	}

	public boolean getAbandon() {
		return abandon;
	}

	public void setCouleur(Color c) {
		couleur = c;
	}

	public void pioche() {
		Tuile t = partie.pioche();
		courante = t;
		if(t!=null)t.setJoueur(this);
	}

	public int getScore() {
		return score;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void defausser() {
		courante = null;
	}

	public void tournerDroite() {
		if (courante !=  null)
			courante.tournerDroite();
	}

	public void tournerGauche() {
		if (courante !=  null)
			courante.tournerGauche();
	}	

	public void poserTuile(int x, int y) {
		if (partie.estPosable(x, y, courante))
			partie.jouerTour(x, y, courante);
	}

	public int[] peutJouer() {
		int[] pos = {-1,-1};
		int hauteur = partie.getHauteur();
		int largeur = partie.getLargeur();

		for (int i = 0; i < hauteur; i++) {
			for (int j = 0; j < largeur; j++) {
				for (int k = 0; k < 4; k++) {
					if (partie.estPosable(i, j, courante)) {
						pos[0] = i;
						pos[1] = j;
						return pos;
					}
					tournerDroite();
				}
			}
		}
		return pos;
	}

	public boolean peutDefausser() {
		boolean peut = true;

		for (int i = 0; i < partie.getHauteur(); i++) {
			for (int j = 0; j < partie.getLargeur(); j++) {
				for (int k = 0; k < 4; k++) {
					if (partie.estPosable(i, j, courante)) {
						peut = false;
					}
					tournerDroite();
				}
				if (!peut)
					return false;
			}
		}

		return true;
	}

	public void ajouterScore(int x) {
		score += x;
	}

	public void abandonner() {
		abandon = true;
		partie.abandon();
	}
}
