public class Tuile<E> {
    
    private E[] cotes;
    private int orientation;

    public Tuile(E[] c) {
        cotes = c;
        orientation = 0;
    }
    
    // GETTERS
    public E getCote(int i) {
        return cotes[(i + orientation) % cotes.length];
    }
    
    // SETTERS
    public void tournerGauche() {
        orientation = (orientation + 1) % cotes.length;
    }

    public void tournerDroite() {
        orientation = (orientation + cotes.length - 1) % cotes.length;
    }

    public boolean estCompatible(int i, Tuile t) {
        return getCote(i).equals(t.getCote((i-cotes.length)% 4));
    }
}
