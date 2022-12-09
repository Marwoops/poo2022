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
		super.pioche(super.getJoueur(0));
		super.pioche(super.getJoueur(0));
		super.pioche(super.getJoueur(0));
		super.pioche(super.getJoueur(0));
    }

	private static Joueur[] deuxJoueurs() {
		Joueur[] j = {new Joueur(), new Joueur()};
		return j;
	}

	public void afficherLigne(Tuile[] t) {
		int n = ((Rangee)t[0].getCote(0)).taille();
		int espace = 2*n+1;

		String haut = "";
		String bas = "";
		for (Tuile d : t) {
			d = (Domino) d;
			haut += "  ";
			bas += "  ";
			Rangee rH = (Rangee) d.getCote(0);
			Rangee rB = (Rangee) d.getCote(2);
			for (int i = 0; i < rH.taille(); i++) {
				haut += rH.getPoint(i) + " ";
				bas += rB.getPoint(n-i-1) + " ";
			}
			haut += "  ";
			bas += "  ";
		}
		
		String centre = "\n";
		for (int i = 0; i < n; i++) {
			for (Tuile d : t) {
				d = (Domino) d;
				centre += ((Rangee)d.getCote(3)).getPoint(n-1-i) + " ".repeat(espace) + ((Rangee)d.getCote(1)).getPoint(i) + " ";
			}
			centre += "\n";
		}

		System.out.println(haut + centre + bas);
	}

	public void afficher(LinkedList<Tuile> t) {
		afficherLigne(t.toArray(new Domino[0]));
	}

	public void afficherPlateau() {
		for (Tuile[] t : super.getPlateau().getPlat())
			afficherLigne(t);
	}
}
