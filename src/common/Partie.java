public class Partie {
    
    private Joueur[] joueurs;
	private Joueur joueurCourant;
	private int indiceJoueur;
    
    private Sac sac;
    private Plateau plateau;

    public Partie(Sac s, Plateau p, Joueur[] j) {
        sac = s;
        plateau = p;
		joueurs = j;
		indiceJoueur = 0;
		joueurCourant = j[0];
    }

    public Sac getSac() {
        return sac;
    }

    public Plateau getPlateau() {
        return plateau;
    }

	public Joueur getJoueur(int i) {
		return joueurs[i];
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public void prochainTour() {
		indiceJoueur = (indiceJoueur+1) % joueurs.length;
		joueurCourant = joueurs[indiceJoueur];
	}

	public boolean estPosable(int x, int y, Tuile t) {
		return plateau.estPosable(x, y, t);
	}

	public void poserTuile(int x, int y, Tuile t) {
		plateau.setTuile(x, y, t);
	}

	public boolean jouerTour(int x, int y, Tuile t) {
		if (!estPosable(x, y, t) || !(joueurCourant.getMain().contains(t))) return false;
		poserTuile(x, y, t);
		joueurCourant.getMain().remove(t);
		prochainTour();
		return true;
	}
}

