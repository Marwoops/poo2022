import java.util.LinkedList;

public class Joueur {
    
    private int score;
    private LinkedList<Tuile> main;

    public Joueur() {
        score = 0;
        main = new LinkedList<Tuile>();
    }
}
