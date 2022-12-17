import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class VueMain extends JPanel {

    private Joueur joueur;
	private LinkedList<VueTuile> vues;
	private MouseListener controleur;
	private boolean estCarcassonne;

    public VueMain(Joueur j, MouseListener controleur, boolean estCarcassonne) {
		this.controleur = controleur;
        joueur = j;
        LinkedList<Tuile> main = j.getMain();
		vues = new LinkedList<VueTuile>();

        for (int i = 0; i < main.size(); i++) {
			VueTuile vue = (estCarcassonne) ? new VueParcelle(main.get(i), -1, -1, true, controleur) : new VueDomino(main.get(i), -1, -1, true, controleur);
			vues.add(vue);
            add(vue);
        }
    }

	public void update_suppr() {
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

	public void update_ajout(){
		LinkedList<Tuile> main = joueur.getMain();
		LinkedList<VueTuile> a_ajouter = new LinkedList<VueTuile>();

		for(int i = vues.size(); i<main.size(); i++){
			a_ajouter.add((estCarcassonne) ? new VueParcelle(main.get(i), -1, -1, true, controleur) : new VueDomino(main.get(i), -1, -1, true, controleur));
		}
		for(VueTuile v : a_ajouter){
			vues.add(v);
			add(v);
		}
		repaint();
	}

	public Joueur getJoueur(){
		return joueur;
	}

	public LinkedList<VueTuile> getVues(){
		return vues;
	}
}
