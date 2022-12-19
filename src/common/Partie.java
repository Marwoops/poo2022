public class Partie {
    
    private Joueur[] joueurs;
	private Joueur joueurCourant;
	private int indiceJoueur;
    
    private Sac sac;
    private Plateau plateau;
    
	public Partie(Sac s, Plateau p, int nbJoueurs, int nbIA) {
        sac = s;
        plateau = p;

		joueurs = new Joueur[nbJoueurs];
		for (int i = 0; i < nbJoueurs - nbIA; i++) {
			joueurs[i] = new Joueur(this, false);
		}
		for (int i = nbIA; i < nbJoueurs; i++) {
			joueurs[i] = new Joueur(this, true);
		}

		indiceJoueur = 0;
		joueurCourant = joueurs[0];
    }

	public boolean estFinie() {
		return sac.estVide();
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

	public Joueur[] getJoueurs() {
		return joueurs;
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public Tuile pioche() {
		if(sac.estVide()){return null;}
		return sac.pioche();
	}

	public void prochainTour() {
		indiceJoueur = (indiceJoueur+1) % joueurs.length;
		joueurCourant = joueurs[indiceJoueur];
		joueurCourant.pioche();
	}

	public boolean estPosable(int x, int y, Tuile t) {
		return plateau.estPosable(x, y, t);
	}

	public void jouerTour(int x, int y, Tuile t) {
		plateau.setTuile(x, y, t);
		prochainTour();
	}

	public boolean estDefaussable(Tuile t){
		for(int x = 0; x < plateau.getLargeur(); x++){
			for(int y = 0;y < plateau.getHauteur(); y++){
				if(estPosable(x,y,t)){
					return false;
				}
			}
		}
		return true;
	}
}

