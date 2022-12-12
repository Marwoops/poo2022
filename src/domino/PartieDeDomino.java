import java.util.LinkedList;

public class PartieDeDomino extends Partie {
    
    private static LinkedList<Tuile> constructionSac() {
        LinkedList<Tuile> s = new LinkedList<Tuile>();

        for (int i = 0; i < 28; i++) {
            int[] haut = {i % 5, (i+1)%5, (i+2)%5};
            int[] droit = {i % 5, (i+1)%5, (i+2)%5};
            int[] bas = {(i+2) % 5, (i+1)%5, (i)%5};
            int[] gauche = {(i+2) % 5, (i+1)%5, (i)%5};
            
            Rangee[] r = {new Rangee(haut), new Rangee(droit), new Rangee(bas), new Rangee(gauche)};
            s.offer(new Domino(r));
        }

        return s;
    }

    public PartieDeDomino() {

        super(new Sac(constructionSac()), new Plateau(10,10), deuxJoueurs());
		getJoueur(0).setPartie(this);
		getJoueur(1).setPartie(this);
		getJoueur(0).pioche();
		getJoueur(0).pioche();
		getJoueur(0).pioche();
		super.poserTuile(9, 9, constructionSac().get(5));

    }

	private static Joueur[] deuxJoueurs(Partie p) {
		Joueur[] j = {new Joueur(p), new Joueur(p)};
		return j;
	}

}
