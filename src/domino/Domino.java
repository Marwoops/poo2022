public class Domino extends Tuile {

    public Domino(Rangee[] r) {
        super(r);
    }

    public String toString() {
        // affichage d'un domino carre (4 cotes), mais adaptable à d'autres types de domino
        String haut = "  ";
        String bas = "  ";
        String centre = "\n";

        Rangee rH = (Rangee) getCote(0);
        Rangee rD = (Rangee) getCote(1);
        Rangee rB = (Rangee) getCote(2);
        Rangee rG = (Rangee) getCote(3);
        
        int n = rH.taille();
        int espace = 3*n;

        for (int i = 0; i < n; i++) {
            haut += rH.getPoint(i) + "  ";
            centre += rG.getPoint(n-1-i) + " ".repeat(espace) + rD.getPoint(i) + "\n";
            bas += rB.getPoint(n-1-i) + "  ";
        }
    
        return haut + centre + bas;
        
    }


}
