public class Plateau {

	private int hauteur;
	private int largeur;
	private Tuile[][] plat;

	public Plateau(int ha, int la) {
		hauteur = ha;
		largeur = la;
		plat = new Tuile[ha][la];
	}

	public int getHauteur() {
		return hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

	public Tuile getTuile(int x, int y) {
		if (horsLimite(x, y))
			return null;
		return plat[x][y];
	}

	public Tuile[][] getPlat() {
		return plat;
	}

	public boolean estSelectionnable(int x, int y) {
		if (horsLimite(x,y) || getTuile(x,y) != null) return false;
		Tuile haut = getTuile(x-1, y);
		Tuile droite = getTuile(x, y+1);
		Tuile bas = getTuile(x+1, y);
		Tuile gauche = getTuile(x, y-1);

		return (haut != null || droite != null || bas != null || gauche != null);
	}

	public boolean estPosable(int x, int y, Tuile t) {
		return (estSelectionnable(x, y)
			&& t.estCompatible(0, getTuile(x-1, y)) && t.estCompatible(1, getTuile(x, y+1))
			&& t.estCompatible(2, getTuile(x+1, y)) && t.estCompatible(3, getTuile(x, y-1)));
	}

	public void setTuile(int x, int y, Tuile t) {
		if (!horsLimite(x, y))
			plat[x][y] = t;
	}

	public boolean horsLimite(int x, int y) {
		return (x >= hauteur || x < 0 || y < 0 || y >= largeur);
	}

	public int calculScore(int x,int y){
		if(horsLimite(x,y)){return 0;}
		int res = 0;
		Domino domino = (Domino) getTuile(x,y);
		return res;
	}
}
