public class Route extends Terrain {

	public boolean estCompatible(Cote c) {
		return c instanceof Route;
	}
}
