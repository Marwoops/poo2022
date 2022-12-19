import java.util.LinkedList;
import java.util.Scanner;

public class DominoTextuel extends PartieDeDomino {

	public static void afficherLigne(Tuile[] t, int taille, int ligne) {
		int espace = 2 * taille + 1;

		String haut = (ligne == -1) ? "    "  : ligne + "   ";
		String bas = "";
		for (Tuile d : t) {
			if (d == null) {
				haut += " ".repeat(4 + 2*taille);
				bas += " ".repeat(4 + 2*taille);
			} else {
				d = (Domino) d;
				haut += "  ";
				bas += "  ";
				Rangee rH = (Rangee) d.getCote(0);
				Rangee rB = (Rangee) d.getCote(2);
				for (int i = 0; i < taille; i++) {
					haut += rH.getPoint(i) + " ";
					bas += rB.getPoint(taille-i-1) + " ";
				}
				haut += "  ";
				bas += "  ";
			}
		}

		String centre = "\n    ";
		for (int i = 0; i < taille; i++) {
			for (Tuile d : t) {
				if (d == null) {
					centre += " ".repeat(espace + 3);
				} else {
					d = (Domino) d;
					centre += ((Rangee)d.getCote(3)).getPoint(taille-1-i) + " ".repeat(espace) + ((Rangee)d.getCote(1)).getPoint(i) + " ";
				}
			}
			centre += "\n    ";
		}

		System.out.println(haut + centre + bas);
	}

	public static void afficherMain(Joueur joueur) {
		System.out.println(joueur.getCourante());
	}

	public void afficherPlateau() {
		Plateau p = getPlateau();
		System.out.println(constructionHaut(3, p.getLargeur()));
		for (int i = 0; i < p.getHauteur(); i++)
			afficherLigne(p.getPlat()[i], 3, i);
	}

	public static String constructionHaut(int taille, int n) {
		String r = "    ";
		for (int i = 0; i < n; i++) {
			r += (char)('A'+i) + " ".repeat(3 + 2*taille);
		}
		return r;
	}

	public DominoTextuel(int nbJoueurs, int nbIA) {
		super(nbJoueurs, nbIA);
	}

	public void demanderAction() {
		System.out.println();
		System.out.println(getJoueurCourant().getCourante());
		Scanner sc = new Scanner(System.in);
		System.out.println("(z) poser la tuile");
		System.out.println("(d) tourner à doite");
		System.out.println("(q) tourner à gauche");
		System.out.println("(c) afficher le plateau");
		System.out.println("(x) défausser");

		String s = sc.next();
		switch(s) {
			case "z" : placerTuile(); break;
			case "d" : getJoueurCourant().tournerDroite(); demanderAction(); break;
			case "q" : getJoueurCourant().tournerGauche(); demanderAction(); break;
			case "c" : afficherPlateau(); demanderAction(); break;
			case "x" : getJoueurCourant().defausser(); prochainTour(); demanderAction(); break;
			default: demanderAction();
		}
	}

	public void placerTuile() {
		Scanner sc = new Scanner(System.in);
		int x;
		int y;
		while(true) {
			try {
				System.out.print("colonne : ");
				y = sc.next().charAt(0) - 'A';
				System.out.print("ligne : ");
				x = sc.nextInt();
				if (estPosable(x, y, getJoueurCourant().getCourante()))
					break;
			} catch (Exception e) {
				System.out.println("impossible\n");
				sc.next();
			}
		}
		getJoueurCourant().poserTuile(x,y);
		afficherPlateau();
	}

	public static void main(String[] args) {
		DominoTextuel jeu = new DominoTextuel(2, 0);
		jeu.afficherPlateau();
		while (!jeu.estFinie()) {
			jeu.afficherMain(jeu.getJoueurCourant());
			jeu.demanderAction();
		}
		System.out.println("c'est FINI BRAVO");
	}
}
