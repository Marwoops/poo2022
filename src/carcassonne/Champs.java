public class Champs extends Terrain {

	public boolean estCompatible(Cote c) {
		return c instanceof Champs;
	}
}
