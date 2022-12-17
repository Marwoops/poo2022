import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class VueMain extends JPanel {

    private Joueur joueur;
	private LinkedList<VueTuile> vues;
	private MouseListener controleur;

    public VueMain(Joueur j, MouseListener controleur) {
		this.controleur = controleur;
        joueur = j;
        LinkedList<Tuile> main = j.getMain();
		vues = new LinkedList<VueTuile>();

        for (int i = 0; i < main.size(); i++) {
            VueTuile t = new VueParcelle(main.get(i), -1, -1, true, controleur);
			vues.add(t);
            add(t);
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
		LinkedList<VueTuile> a_ajouter = new LinkedList<VueTuile>();
		for(int i = vues.size();i<joueur.getMain().size();i++){
			a_ajouter.add(new VueParcelle(joueur.getMain().get(i), -1, -1, true, controleur));
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
