public class Partie {
    
    private Joueur joueur1;
    private Joueur joueur2;
    
    private Sac sac;
    private Plateau plateau;

    public Partie(Joueur j1, Joueur j2, Sac s, Plateau p) {
        joueur1 = j1;
        joueur2 = j2;
        sac = s;
        plateau = p;
    }

    public Sac getSac() {
        return sac;
    }

    public Plateau getPlateau() {
        return plateau;
    }
}

