public class DominoTextuel {

	private PartieDeDomino partie;

	public DominoTextuel(PartieDeDomino p) {
		partie = p;
	}

	public static void main(String[] args) {
		DominoTextuel jeu = new DominoTextuel(new PartieDeDomino());
	}
}
