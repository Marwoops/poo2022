import java.util.LinkedList;

public class Joueur {
    
    private int score;
    private LinkedList<Tuile> main;

    public Joueur() {
        score = 0;
        main = new LinkedList<Tuile>();
    }
    
    public LinkedList<Tuile> getMain() {
        return main;
    }

    public void pioche(Tuile t) {
        main.offer(t);
		t.setJoueur(this);
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
