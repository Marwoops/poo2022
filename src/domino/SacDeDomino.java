import java.util.LinkedList;

public class SacDeDomino extends Sac {

	// construit le sac par analogie avec celui de Parcelle
	public static final Rangee r1 = new Rangee(1,1,1);
	public static final Rangee r2 = new Rangee(1,3,1);
	public static final Rangee r3 = new Rangee(2,4,2);
	public static final Rangee r4 = new Rangee(2,3,1);
	public static final Rangee r5 = new Rangee(3,3,3);
	public static final Rangee r6 = new Rangee(2,3,2);
	public static final Rangee r7 = new Rangee(2,1,2);
	public static final Rangee r8 = new Rangee(1,3,2);
	public static final Rangee r9 = new Rangee(2,4,4);
	public static final Rangee r10 = new Rangee(4,4,2);

	public SacDeDomino() {
		super(constructionSac());
	}

	public static Domino t1() {
		Rangee[] t = {r1, r1, r2, r2};
		return new Domino(t);
	}

	public static Domino t2() {
		Rangee[] t = {r3, r4, r2, r4};
		return new Domino(t);
	}

	public static Domino t3() {
		Rangee[] t = {r8, r4, r4, r9};
		return new Domino(t);
	}

	public static Domino t4() {
		Rangee[] t = {r4, r9, r6, r9};
		return new Domino(t);
	}

	public static Domino t5() {
		Rangee[] t = {r4, r9, r7, r9};
		return new Domino(t);
	}

	public static Domino t6() {
		Rangee[] t = {r9, r4, r4, r9};
		return new Domino(t);
	}

	public static Domino t7() {
		Rangee[] t = {r3, r4, r2, r4};
		return new Domino(t);
	}

	public static Domino t8() {
		Rangee[] t = {r2, r1, r2, r1};
		return new Domino(t);
	}

	public static Domino t9() {
		Rangee[] t = {r1, r2, r2, r2};
		return new Domino(t);
	}

	public static Domino t10() {
		Rangee[] t = {r3, r7, r3, r7};
		return new Domino(t);
	}

	public static Domino t11() {
		Rangee[] t = {r3, r3, r7, r7};
		return new Domino(t);
	}

	public static Domino t12() {
		Rangee[] t = {r10, r9, r7, r9};
		return new Domino(t);
	}

	public static Domino t13() {
		Rangee[] t = {r1, r1, r1, r1};
		return new Domino(t);
	}

	public static Domino t14() {
		Rangee[] t = {r1, r1, r2, r1};
		return new Domino(t);
	}

	public static Domino t15() {
		Rangee[] t = {r9, r7, r7, r9};
		return new Domino(t);
	}

	public static Domino t16() {
		Rangee[] t = {r7, r3, r7, r3};
		return new Domino(t);
	}

	public static Domino t17() {
		Rangee[] t = {r3, r4, r1, r4};
		return new Domino(t);
	}

	public static Domino t18() {
		Rangee[] t = {r3, r7, r2, r4};
		return new Domino(t);
	}

	public static Domino t19() {
		Rangee[] t = {r7, r3, r7, r3};
		return new Domino(t);
	}

	public static Domino t20() {
		Rangee[] t = {r4, r9, r6, r9};
		return new Domino(t);
	}

	public static Domino t21() {
		Rangee[] t = {r10, r10, r10, r10};
		return new Domino(t);
	}

	public static Domino t22() {
		Rangee[] t = {r2, r2, r2, r2};
		return new Domino(t);
	}

	public static Domino t23() {
		Rangee[] t = {r9, r7, r7, r9};
		return new Domino(t);
	}

	public static Domino t24() {
		Rangee[] t = {r7, r3, r7, r3};
		return new Domino(t);
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
