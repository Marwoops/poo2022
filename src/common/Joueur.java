import java.util.LinkedList;

public class Joueur {

	private Partie partie;
	private Tuile courante;
	private int score;
	private boolean estIA;

	public Joueur(Partie p, boolean estIA) {
		partie = p;
		this.estIA = estIA;
		courante = null;
		score = 0;
	}

	public boolean estIA() {
		return estIA;
	}

	public Tuile getCourante(){
		return courante;
	}

	public void pioche() {
		Tuile t = partie.pioche();
		courante = t;
		if(t!=null)t.setJoueur(this);
	}

	public int getScore() {
		return score;
	}

	public boolean defausser() {
		courante = null;
		return true;
	}

	public void tournerDroite() {
		if (courante !=  null)
			courante.tournerDroite();
	}

	public void tournerGauche() {
		if (courante !=  null)
			courante.tournerGauche();
	}	

	public String toString() {
		String r = ""; 
		r += ("Score : " + score + "\n");
		r += courante.toString();
		return r;
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
				}
				tournerDroite();
			}
		}
		return pos;
	}

	public void ajouterScore(int x) {
		score += x;
	}
}
