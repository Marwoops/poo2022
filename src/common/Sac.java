import java.util.LinkedList;
import java.util.Random;

public class Sac {
    
    private LinkedList<Tuile> contenu;
	Random r = new Random();
    
    public Sac(LinkedList<Tuile> c) {
        contenu = c;
    }

	public Sac() {
		contenu = new LinkedList<Tuile>();
	}
    
    public boolean estVide() {
        return contenu.isEmpty();
    }

    public Tuile pioche() {
        if (contenu.isEmpty())
            return null;
        return contenu.remove(r.nextInt(contenu.size()));
    }
}
