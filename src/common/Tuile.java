public class Tuile {
      
    private int orientation;
    private Cote[] cotes;
	private Joueur joueur;

    public Tuile(Cote[] c) {
        cotes = c;
        orientation = 0;
    }
    
    // GETTERS
    public Cote getCote(int i) {
        return cotes[(i + orientation) % cotes.length];
    }

    public Cote[] getCotes() {
        return cotes;
    }

    public int getOrientation() {
        return orientation;
    }

	public Joueur getJoueur() {
		return joueur;
	}
    
    // SETTERS
	public void setJoueur(Joueur j) {
		joueur = j;
	}

    public void tournerGauche() {
        orientation = (orientation + 1) % cotes.length;
    }

    public void tournerDroite() {
        orientation = (orientation + cotes.length - 1) % cotes.length;
    }

	public boolean estCompatible(int i, Tuile t) {
        return t == null || getCote(i).estCompatible(t.getCote(i+2));
    }
}
