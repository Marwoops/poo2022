public abstract class Terrain extends Cote {

	public abstract boolean estCompatible(Cote c);

	public class Champs {
		public boolean estCompatible(Cote c) {
			return c instanceof Champs;
		}

	public class Route {
		public boolean estCompatible(Cote c) {
			return c instanceof Route;
		}

	public class Ville {
		public boolean estCompatible(Cote c) {
			return c instanceof Ville;
		}

	public class Abbaye {
		public boolean estCompatible(Cote c) {
			return c instanceof Abbaye;
		}
}
