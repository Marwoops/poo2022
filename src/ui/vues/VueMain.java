import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class VueMain extends JPanel {

    private Joueur joueur;
	private LinkedList<VueTuile> vues;

    public VueMain(Joueur j, MouseListener controleur) {
        joueur = j;
        LinkedList<Tuile> main = j.getMain();
		vues = new LinkedList<VueTuile>();

        for (int i = 0; i < main.size(); i++) {
            VueTuile t = new VueTuile(main.get(i), -1, -1, true, controleur);
			vues.add(t);
            add(t);
        }
    }

	public void update() {
		LinkedList<VueTuile> aSuppr = new LinkedList<VueTuile>();

		for (VueTuile vue : vues) {
			if (vue.estVide()) {
				aSuppr.add(vue);
				this.remove(vue);
			}
		}

		for (VueTuile vue : aSuppr) {
			vues.remove(vue);
		}
		repaint();
	}

	public Joueur getJoueur(){
		return joueur;
	}
}
