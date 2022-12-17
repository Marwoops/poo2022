import java.util.LinkedList;

public class PartieDeCarcassonne extends Partie {

	public static Joueur[] deuxJoueurs(){
		Joueur[] j = {new Joueur(), new Joueur()};
		return j;
	}

	public boolean estFinie(){
		return (super.getSac().estVide() && super.mainJoueursVides());
	}

    public PartieDeCarcassonne() {
		super(new SacDeParcelle(), new Plateau(10,10), deuxJoueurs());
		for(Joueur joueur : super.getJoueurs()){
			joueur.setPartie(this);
		}
        super.getPlateau().setTuile(5, 5, (new SacDeParcelle()).t17());
        super.getJoueur(0).pioche();
    }
}
