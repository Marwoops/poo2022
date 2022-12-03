import java.util.LinkedList;

public class PartieDeCarcassonne extends Partie {
	private static Joueur[] deuxJoueurs() {
		Joueur[] j = {new Joueur(), new Joueur()};
		return j;
	}

    public PartieDeCarcassonne() {
        super(new SacDeParcelle(), new Plateau(10,10), deuxJoueurs());
        super.getPlateau().setTuile(1, 1, super.getSac().pioche());
        //super.getPlateau().getTuile(1,1).tournerGauche();
        super.getJoueur(0).pioche(super.getSac().pioche());
        super.getJoueur(0).pioche(super.getSac().pioche());
        super.getJoueur(0).pioche(super.getSac().pioche());
        super.getJoueur(0).pioche(super.getSac().pioche());
        super.getJoueur(1).pioche(super.getSac().pioche());

    }
}
