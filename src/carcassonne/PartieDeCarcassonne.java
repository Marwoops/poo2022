import java.util.LinkedList;

public class PartieDeCarcassonne extends Partie {

	private static Joueur[] deuxJoueurs(Partie p) {
		Joueur[] j = {new Joueur(p), new Joueur(p)};
		return j;
	}

    public PartieDeCarcassonne() {
        super(new SacDeParcelle(), new Plateau(10,10), deuxJoueurs(null));
		for(Joueur joueur : super.getJoueurs()){
			joueur.setPartie(this);
		}
        super.getPlateau().setTuile(1, 1, (new SacDeParcelle()).t17());
        //super.getPlateau().getTuile(1,1).tournerGauche();
        super.getJoueur(0).pioche(super.getSac().pioche());
        super.getJoueur(0).pioche(super.getSac().pioche());
        super.getJoueur(0).pioche(super.getSac().pioche());
        super.getJoueur(0).pioche(super.getSac().pioche());
        //super.getJoueur(1).pioche(super.getSac().pioche());
        super.getJoueur(1).pioche(super.getSac().pioche());
        super.getJoueur(1).pioche(super.getSac().pioche());
        super.getJoueur(1).pioche(super.getSac().pioche());
        super.getJoueur(1).pioche(super.getSac().pioche());
    }
}
