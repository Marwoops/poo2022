import java.util.LinkedList;

public class PartieDeDomino extends Partie {

    public PartieDeDomino(int nbJoueurs, int nbIA) {
        super(new SacDeDomino(), new Plateau(10,10), nbJoueurs, nbIA);

		getJoueur(0).pioche();
		getPlateau().setTuile(5, 5, pioche());
    }
}
