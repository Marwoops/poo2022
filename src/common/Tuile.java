public class Tuile {
      
    private int orientation;
    private Cote[] cotes;

    public Tuile(Cote[] c) {
        cotes = c;
        orientation = 0;
    }
    
    // GETTERS
    public Cote getCote(int i) {
        return cotes[(i + orientation) % cotes.length];
    }

    public Cote[] getCotes() {
        return cotes;
    }

    public int getOrientation() {
        return orientation;
    }
    
    // SETTERS
    public void tournerGauche() {
        orientation = (orientation + 1) % cotes.length;
    }

    public void tournerDroite() {
        orientation = (orientation + cotes.length - 1) % cotes.length;
    }

    public boolean estCompatible(int i, Tuile t) {
        return getCote(i).estCompatible(t.getCote((i-cotes.length)% 4));
    }
}
