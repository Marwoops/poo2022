import java.util.LinkedList;

public class PartieDeDomino extends Partie {

	public PartieDeDomino(int nbJoueurs, int nbIA) {
		super(new SacDeDomino(), new Plateau(10,10), nbJoueurs, nbIA);

		getJoueur(0).pioche();
		getPlateau().setTuile(5, 5, pioche());
	}

	public int calculScore(int x, int y) {
		int score = 0;
		Plateau plateau = getPlateau();
		Domino domino = (Domino) plateau.getTuile(x, y);

		if(plateau.getTuile(x-1,y)!=null)
			score += ((Rangee) domino.getCote(0)).score();

		if(plateau.getTuile(x,y+1)!=null)
			score += ((Rangee) domino.getCote(1)).score();

		if(plateau.getTuile(x+1, y)!=null)
			score += ((Rangee) domino.getCote(2)).score();

		if(plateau.getTuile(x, y-1)!=null)
			score += ((Rangee) domino.getCote(3)).score();

		return score;
	}
}
