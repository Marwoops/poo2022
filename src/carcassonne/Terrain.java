public class Terrain extends Cote {
    
    private int identifiant;

    public Terrain(int id) {
        identifiant = id;
    }

    public boolean estCompatible(Cote c) {
        Terrain t = (Terrain) c;
        return identifiant == t.identifiant;
    }
}
