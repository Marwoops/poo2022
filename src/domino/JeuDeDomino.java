import java.util.LinkedList;

public class JeuDeDomino {
    
    private Sac sac;
    private Plateau plat;
    private Joueur joueur1;
    private Joueur joueur2;

    private LinkedList<Tuile> constructionSac() {
        LinkedList<Tuile> s = new LinkedList<Tuile>();

        for (int i = 0; i < 28; i++) {
            int[] haut = {i % 5, (i+1)%5, (i+2)%5};
            int[] droit = {i % 5, (i+1)%5, (i+2)%5};
            int[] bas = {(i+2) % 5, (i+1)%5, (i)%5};
            int[] gauche = {(i+2) % 5, (i+1)%5, (i)%5};
            
            Rangee[] r = {new Rangee(haut), new Rangee(droit), new Rangee(bas), new Rangee(gauche)};
            s.offer(new Domino(r));
        }

        return s;
    }

    public JeuDeDomino() {
        joueur1 = new Joueur();
        joueur2 = new Joueur();

        sac = new Sac(constructionSac());
        plat = new Plateau(10,10);
    }

    public JeuDeDomino(Joueur j1, Joueur j2) {
        joueur1 = j1;
        joueur2 = j2;

        sac = new Sac(constructionSac());
        plat = new Plateau(10,10);
    }

    public Plateau getPlateau() {
        return plat;
    }

}
