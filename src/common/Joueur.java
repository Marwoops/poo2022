import java.util.LinkedList;

public class Joueur {
    
    private int score;
    private LinkedList<Tuile> main;
	private Tuile courante;
	private Partie partie;

    public Joueur(Partie p) {
		partie = p;
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

    public void pioche(Tuile t) {
		courante = t;
        main.offer(t);
		t.setJoueur(this);
    }

	public void poserTuile(int x, int y, Tuile t){
		partie.getPlateau().setTuile(x, y, t);
	}


}
