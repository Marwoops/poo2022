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
}

