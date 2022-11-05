public class Terrain extends Cote {
    
    // 0 : champ
    // 1 : route
    // 2 : ville
    // 3 : abbaye
    //
    private int identifiant;

    public Terrain(int id) {
        identifiant = id;
    }

    public boolean estCompatible(Cote c) {
        Terrain t = (Terrain) c;
        return identifiant == t.identifiant;
    }
}
