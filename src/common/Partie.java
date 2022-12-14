import java.awt.Color;

public class Partie {

	private static Color[] couleurs = {new Color(33, 112, 145), new Color(145, 33, 50), new Color(16, 235, 75), new Color(224, 217, 2)};

	private Joueur[] joueurs;
	private Joueur joueurCourant;
	private int indiceJoueur;
	private int nbJoueursRestants;

	private Sac sac;
	private Plateau plateau;

	public Partie(Sac s, Plateau p, int nbJoueurs, int nbIA) {
		sac = s;
		plateau = p;

		joueurs = new Joueur[nbJoueurs];
		for (int i = 0; i < nbJoueurs - nbIA; i++) {
			joueurs[i] = new Joueur(this, false);
		}
		for (int i = nbJoueurs - nbIA; i < nbJoueurs; i++) {
			joueurs[i] = new Joueur(this, true);
		}

		indiceJoueur = 0;
		joueurCourant = joueurs[0];
		nbJoueursRestants = joueurs.length;

		for (int i = 0; i < joueurs.length; i++)
			joueurs[i].setCouleur(couleurs[i]);
	}

	public boolean estFinie() {
		return sac.estVide() || nbJoueursRestants == 0;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public int getLargeur() {
		return plateau.getLargeur();
	}

	public int getHauteur() {
		return plateau.getHauteur();
	}

	public Joueur getJoueur(int i) {
		return joueurs[i];
	}

	public int getIndiceJoueur() {
		return indiceJoueur;
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public int getNbJoueurs() {
		return joueurs.length;
	}

	public int getNbJoueursRestants() {
		return nbJoueursRestants;
	}

	public Tuile pioche() {
		if(sac.estVide()){return null;}
		return sac.pioche();
	}

	public void prochainTour() {
		for (int i = indiceJoueur + 1; i < indiceJoueur + 1 + joueurs.length; i++) {
			if (!joueurs[i % joueurs.length].getAbandon()) {
				indiceJoueur = i % joueurs.length;
				break;
			}
		}
		joueurCourant = joueurs[indiceJoueur];
		joueurCourant.pioche();
	}

	public void abandon() {
		nbJoueursRestants--;
		prochainTour();
	}

	public boolean estPosable(int x, int y, Tuile t) {
		return plateau.estPosable(x, y, t);
	}

	public boolean estSelectionnable(int x, int y) {
		return plateau.estSelectionnable(x, y);
	}

	public void jouerTour(int x, int y, Tuile t) {
		plateau.setTuile(x, y, t);
		joueurCourant.ajouterScore(calculScore(x, y));
		prochainTour();
	}

	public int calculScore(int x,int y) {
		return 0;
	}
}
