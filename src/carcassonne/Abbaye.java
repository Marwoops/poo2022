public class Abbaye extends Terrain {

	public boolean estCompatible(Cote c) {
		return c instanceof Abbaye;
	}
}
