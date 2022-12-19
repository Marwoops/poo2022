import java.util.LinkedList;

public class PartieDeDomino extends Partie {

    public PartieDeDomino() {
        super(new SacDeDomino(), new Plateau(10,10), 2, 0);

		getJoueur(0).pioche();
		getPlateau().setTuile(5, 5, pioche());
    }
}
