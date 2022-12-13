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
		LinkedList<Tuile> t = joueur.getMain();
		System.out.println(constructionHaut(3, t.size()));
		afficherLigne(t.toArray(new Domino[0]), 3, -1);
		System.out.println();
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

	public void demanderActionJoueur() {
		Scanner sc = new Scanner(System.in);
		System.out.println("(s) sélectioner une tuile");
		System.out.println("(p) piocher");
		String s = sc.next();
		switch(s) {
			case "s" : selectionTuile(); break;
			case "p" :
						getJoueurCourant().pioche();
						System.out.println(getJoueurCourant().getCourante());
						demanderActionTuile();
						break;
			default: demanderActionJoueur();
		}
	}
	public void selectionTuile() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Sélectionnez une tuile: ");
		while(true) {
			try {
				getJoueurCourant().setCourante(sc.next().charAt(0)- 'A');
				break;
			} catch(Exception e) {
				System.out.println("impossible");
			}
		}
		demanderActionTuile();
	}

	public void demanderActionTuile() {
		System.out.println(getJoueurCourant().getCourante());
		Scanner sc = new Scanner(System.in);
		System.out.println("(p) poser la tuile");
		System.out.println("(d) tourner à doite");
		System.out.println("(g) tourner à gauche");
		System.out.println("(c) afficher le plateau");

		String s = sc.next();
		switch(s) {
			case "p" : placerTuile(); break;
			case "d" : getJoueurCourant().tournerDroite(); demanderActionTuile(); break;
			case "g" : getJoueurCourant().tournerGauche(); demanderActionTuile(); break;
			case "c" : afficherPlateau(); demanderActionTuile(); break;
			default: demanderActionTuile();
		}
	}

	public void placerTuile() {
		Scanner sc = new Scanner(System.in);
		int x;
		int y;
		while(true) {
			try {
				System.out.print("ligne: ");
				x = sc.next().charAt(0) - 'A';
				System.out.print("colonne: ");
				y = sc.nextInt();
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
		DominoTextuel jeu = new DominoTextuel();
		jeu.afficherPlateau();
		while (!jeu.estFinie()) {
			jeu.afficherMain(jeu.getJoueurCourant());
			jeu.demanderActionJoueur();
		}
		System.out.println("c'est FINI BRAVO");
	}
}
