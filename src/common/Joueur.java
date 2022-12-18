import java.util.LinkedList;

public class Joueur {

	private Partie partie;
	private Tuile courante;
    private int score;

    public Joueur() {
		courante = null;
        score = 0;
    }

	public Tuile getCourante(){
		return courante;
	}

	public void setPartie(Partie p) {
		partie = p;
	}

	public void pioche() {
		Tuile t = partie.pioche();
		courante = t;
		if(t!=null)t.setJoueur(this);
	}

	public boolean defausser(){
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

	public boolean poserTuile(int x, int y) {
		if (partie.estPosable(x, y, courante)) {
			partie.jouerTour(x, y, courante);
			return true;
		}
		return false;
	}
}
