public class Ville extends Terrain {

	public boolean estCompatible(Cote c) {
		return c instanceof Ville;
	}
}
