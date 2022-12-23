import java.util.LinkedList;

public class PartieDeCarcassonne extends Partie {

	public PartieDeCarcassonne(int nbJoueurs, int nbIA) {
		super(new SacDeParcelle(), new Plateau(10,10), nbJoueurs, nbIA);

		super.getPlateau().setTuile(5, 5, (new SacDeParcelle()).t17());
		super.getJoueur(0).pioche();
	}
}
