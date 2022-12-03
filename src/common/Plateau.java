public class Plateau {
    
    private int hauteur;
    private int largeur;
    private Tuile[][] plat;

    public Plateau(int ha, int la) {
        hauteur = ha;
        largeur = la;
        plat = new Tuile[ha+2][la+2];
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

	public boolean estPosable(int x, int y, Tuile t) {
		return !horsLimite(x, y) && t.estCompatible(0, getTuile(x-1, y)) && t.estCompatible(1, getTuile(x, y+1))
			&& t.estCompatible(2, getTuile(x+1, y)) && t.estCompatible(3, getTuile(x, y-1));
	}

    public void setTuile(int x, int y, Tuile t) {
        if (!horsLimite(x, y))
            plat[x][y] = t;
    }

    public boolean horsLimite(int x, int y) {
        return (x > hauteur || x < 1 || y < 1 || y > largeur);
    }


}
