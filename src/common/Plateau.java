public class Plateau {
    
    private int hauteur;
    private int largeur;
    private Tuile[][] plat;

    public Plateau(int ha, int la) {
        hauteur = ha;
        largeur = la;
        plat = new Tuile[ha+2][la+2];
    }

    public boolean horsLimite(int x, int y) {
        return (x > hauteur || x < 1 || y < 1 || y > largeur);
    }

    public Tuile getTuile(int x, int y) {
        if (horsLimite(x, y))
            return null;
        return plat[x][y];
    }

    public void setTuile(int x, int y, Tuile t) {
        if (!horsLimite(x, y))
            plat[x][y] = t;
    }
}
