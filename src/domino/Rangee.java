public class Rangee extends Cote {

    private int[] points;

    public Rangee(int[] p) {
        points = p;
    }

    // constructeur rangee aleatoire ?
    
    public boolean estCompatible(Cote c) {
        Rangee r = (Rangee) c;
        if (r.points.length != points.length)
            return false;
        
        for (int i = 0; i < points.length; i++) {
            if (points[i] != r.points[points.length - i-1])
                return false;
        }
        
        return true;
    }

    public int score() {
        int s = 0;
        for (int p : points)
            s += p;
        return s;
    }
}
