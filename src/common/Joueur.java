import java.util.LinkedList;

public class Joueur {

	private Partie partie;
	private Tuile courante;
    private int score;
    private LinkedList<Tuile> main;
	private Tuile courante;
	private Partie partie;

    public Joueur() {
		courante = null;
        score = 0;
        main = new LinkedList<Tuile>();
    }

	public void setPartie(Partie p){
		partie = p;
	}

	public LinkedList<Tuile> getMain() {
        return main;
    }

	public void setPartie(Partie p) {
		partie = p;
	}

	public void pioche() {
		Tuile t = partie.pioche();
		main.offer(t);
		t.setJoueur(this);
	}

	// très unsafe et bourrin
	public boolean setCourante(int n) throws Exception {
		courante = main.get(n);
		return true;
	}

	public boolean setCourante(Tuile t) {
		if (main.contains(t)) {
			courante = t;
			return true;
		}
		return false;
	}

	public boolean poserTuile(int x, int y) {
		if (partie.estPosable(x, y, courante)) {
			partie.poserTuile(x, y, courante);
			main.remove(courante);
			courante = null;
			return true;
		}
		return false;
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
		for (int i = 0; i < main.size(); i++) {
			r += ("Tuile : " + i + "\n");
			r += main.get(i);
		}
		return r;
	}
}
