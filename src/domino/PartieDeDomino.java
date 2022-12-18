import java.util.LinkedList;

public class PartieDeDomino extends Partie {

    public PartieDeDomino() {

        super(new SacDeDomino(), new Plateau(10,10), deuxJoueurs());
		for(Joueur joueur : super.getJoueurs()){
			joueur.setPartie(this);
		}
		getJoueur(0).pioche();
		getPlateau().setTuile(5, 5, pioche());
    }

	private static Joueur[] deuxJoueurs() {
		Joueur[] j = {new Joueur(), new Joueur()};
		return j;
	}
}
