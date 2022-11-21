import java.util.LinkedList;

public class PartieDeCarcassonne extends Partie {

    public static final Terrain champs = new Terrain(0);
    public static final Terrain route = new Terrain(1);
    public static final Terrain ville = new Terrain(2);
    public static final Terrain abbaye = new Terrain(3);

    public static Parcelle t1() {
        Terrain[] t = {champs, champs, route, route};
        return new Parcelle(t, 1);
    }

    public static Parcelle t2() {
        Terrain[] t = {ville, route, route, champs};
        return new Parcelle(t, 2);
    }

    public static LinkedList<Tuile> constructionSac() {
        LinkedList<Tuile> sac = new LinkedList<Tuile>();
        
        // 9 tuiles T1
        for (int i = 0; i < 9; i++) {
            sac.offer(t1());
        }

        for (int i = 0; i < 6; i++) {
            sac.offer(t2());
        }

        return sac;
    }

	public static Joueur[] deuxJoueurs() {
		Joueur[] j = {new Joueur(), new Joueur()};
		return j;
	}

    public PartieDeCarcassonne() {
        super(new Sac(constructionSac()), new Plateau(10,10), deuxJoueurs());
        super.getPlateau().setTuile(1, 1, super.getSac().pioche());
        //super.getPlateau().getTuile(1,1).tournerGauche();
        super.getJoueur(0).pioche(super.getSac().pioche());
        super.getJoueur(1).pioche(super.getSac().pioche());

    }
}
