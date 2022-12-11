import java.util.LinkedList;

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

	public static void afficherMain(LinkedList<Tuile> t) {
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

	public static void main(String[] args) {
		DominoTextuel jeu = new DominoTextuel();
		jeu.afficherPlateau();
	}
}
