public class DominoTextuel {

	private PartieDeDomino partie;

	public DominoTextuel(PartieDeDomino p) {
		partie = p;
	}

	public void afficherMain() {
		System.out.println(partie.getJoueurCourant());
	}

	public static void main(String[] args) {
		DominoTextuel jeu = new DominoTextuel(new PartieDeDomino());
		//jeu.afficherMain();
		jeu.partie.afficher(jeu.partie.getJoueurCourant().getMain());
	}
}
