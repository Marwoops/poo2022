public class Parcelle extends Tuile {
    
	private Terrain centre;
	private int id; // pour render

	public Parcelle (Terrain[] t, Terrain c, int i) {
		super(t);
		centre = c;
		id = i;
	}

	public Parcelle(Terrain[] t, int i) {
		this(t, null, i);
	}

	public int getId() {
		return id;
	}
}
