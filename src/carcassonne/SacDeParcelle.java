import java.util.LinkedList;

public class SacDeParcelle extends Sac{

	
    public static final Terrain champs = new Terrain(0);
    public static final Terrain route = new Terrain(1);
    public static final Terrain ville = new Terrain(2);
    public static final Terrain abbaye = new Terrain(3);

	public SacDeParcelle(){
		super(constructionSac());
	}

	public static Parcelle t1() {
        Terrain[] t = {champs, champs, route, route};
        return new Parcelle(t, 1);
    }

	public static Parcelle t2() {
        Terrain[] t = {ville, route, route, champs};
        return new Parcelle(t, 2);
    }

	public static Parcelle t3() {
        Terrain[] t = {ville, route, route, ville};
        return new Parcelle(t, 3);
    }

	public static Parcelle t4() {
        Terrain[] t = {ville, ville, route, ville};
        return new Parcelle(t, 4);
    }

	public static Parcelle t5() {
        Terrain[] t = {ville, ville, champs, ville};
        return new Parcelle(t, 5);
    }
	
	public static Parcelle t6() {
        Terrain[] t = {ville, route, route, ville};
        return new Parcelle(t, 6);
    }

	public static Parcelle t7() {
        Terrain[] t = {ville, route, route, route};
        return new Parcelle(t, 7);
    }

	public static Parcelle t8() {
        Terrain[] t = {route, champs, route, champs};
        return new Parcelle(t, 8);
    }

	public static Parcelle t9() {
        Terrain[] t = {champs, route, route, route};
        return new Parcelle(t, 9);
    }

	public static Parcelle t10() {
        Terrain[] t = {ville, champs, champs, champs};
        return new Parcelle(t, 10);
    }

	public static Parcelle t11() {
        Terrain[] t = {ville, ville, champs, champs};
        return new Parcelle(t, 11);
    }

	public static Parcelle t12() {
        Terrain[] t = {ville, ville, champs, ville};
        return new Parcelle(t, 12);
    }

	public static Parcelle t13() {
        Terrain[] t = {champs, champs, champs, champs};
        return new Parcelle(t, abbaye, 13);
    }

	public static Parcelle t14() {
        Terrain[] t = {champs, champs, route, champs};
        return new Parcelle(t, abbaye, 14);
    }

	public static Parcelle t15() {
        Terrain[] t = {ville, champs, champs, ville};
        return new Parcelle(t, 15);
    }

	public static Parcelle t16() {
        Terrain[] t = {champs, ville, champs, ville};
        return new Parcelle(t, 16);
    }

	public static Parcelle t17() {
        Terrain[] t = {ville, route, champs, route};
        return new Parcelle(t, 17);
    }

	public static Parcelle t18() {
        Terrain[] t = {ville, champs, route, route};
        return new Parcelle(t, 18);
    }

	public static Parcelle t19() {
        Terrain[] t = {champs, ville, champs, ville};
        return new Parcelle(t, 19);
    }


	public static Parcelle t20() {
        Terrain[] t = {ville, ville, route, ville};
        return new Parcelle(t, 20);
    }

	public static Parcelle t21() {
        Terrain[] t = {ville, ville, ville, ville};
        return new Parcelle(t, 21);
    }

	public static Parcelle t22() {
        Terrain[] t = {route, route, route, route};
        return new Parcelle(t, 22);
    }

	public static Parcelle t23() {
        Terrain[] t = {ville, champs, champs, ville};
        return new Parcelle(t, 23);
    }

	public static Parcelle t24() {
        Terrain[] t = {champs, ville, champs, ville};
        return new Parcelle(t, 24);
    }


	public static LinkedList<Tuile> constructionSac() {
        LinkedList<Tuile> sac = new LinkedList<Tuile>();

        // 9 tuiles T1
        for (int i = 0; i < 9; i++) {
            sac.offer(t1());
        }

        for (int i = 0; i < 6; i++) {
            sac.offer(t2());
        }

		for (int i = 0; i < 2; i++) {
            sac.offer(t3());
        }

		for (int i = 0; i < 1; i++) {
            sac.offer(t4());
        }

		for (int i = 0; i < 1; i++) {
            sac.offer(t5());
        }

		for (int i = 0; i < 3; i++) {
            sac.offer(t6());
        }

		for (int i = 0; i < 3; i++) {
            sac.offer(t7());
        }

		for (int i = 0; i < 8; i++) {
            sac.offer(t8());
        }

		for (int i = 0; i < 4; i++) {
            sac.offer(t9());
        }

		for (int i = 0; i < 5; i++) {
            sac.offer(t10());
        }

		for (int i = 0; i < 2; i++) {
            sac.offer(t11());
        }

		for (int i = 0; i < 3; i++) {
            sac.offer(t12());
        }

		for (int i = 0; i < 4; i++) {
            sac.offer(t13());
        }

		for (int i = 0; i < 2; i++) {
            sac.offer(t14());
        }

		for (int i = 0; i < 3; i++) {
            sac.offer(t15());
        }

		for (int i = 0; i < 2; i++) {
            sac.offer(t16());
        }

		for (int i = 0; i < 3; i++) {
            sac.offer(t17());
        }

		for (int i = 0; i < 3; i++) {
            sac.offer(t18());
        }

		for (int i = 0; i < 1; i++) {
            sac.offer(t19());
        }

		for (int i = 0; i < 2; i++) {
            sac.offer(t20());
        }

		for (int i = 0; i < 1; i++) {
            sac.offer(t21());
        }

		for (int i = 0; i < 1; i++) {
            sac.offer(t22());
        }

		for (int i = 0; i < 2; i++) {
            sac.offer(t23());
        }

		for (int i = 0; i < 3; i++) {
            sac.offer(t24());
        }
        return sac;
    }
}
