import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public abstract class VuePartie extends JComponent {

	public Partie partie;
	public ControleurSouris controleurSouris;

	public VueTuile courant;
	public VueTuile VuePion;
	public VuePlateau vuePlateau;
	public LinkedList<VueTuile> vueMains;

	private JButton tourner_gauche;
	private JButton tourner_droite;
	private JButton defausse;

	public VuePartie(Partie p) {
		setLayout(null);		
		partie = p;
		courant = null;	

		initBoutons();

		vueMains = new LinkedList<VueTuile>();
	}

	public void afficherMains() {
		vueMains.get(0).setBounds(20, 820, 80, 86);
		add(vueMains.get(0));

		vueMains.get(1).setBounds(260, 820, 80, 86);
		add(vueMains.get(1));

		if (vueMains.size() > 2) {
			vueMains.get(2).setBounds(500, 820, 80, 86);
			add(vueMains.get(2));
		}

		if (vueMains.size() > 3) {
			vueMains.get(3).setBounds(740, 820, 80, 86);
			add(vueMains.get(3));
		}
	}

	public void afficherPlateau() {
		vuePlateau.setBounds(20, 20, 800, 800);
		add(vuePlateau);
	}

	public abstract void initMains();
	public abstract void initPlateau();

	public void debuterPartie() {
		initMains();
		afficherMains();
		initPlateau();
		afficherPlateau();
		controleurSouris.preTour(courant);
	}

	private void initBoutons() {
		tourner_gauche = new JButton("⟲");
		tourner_droite = new JButton("⟳");
		defausse = new JButton("❌");
		tourner_gauche.setBounds(900,50,50,50);
		tourner_droite.setBounds(900,110,50,50);
		defausse.setBounds(900,170,50,50);
		tourner_gauche.setEnabled(false);
		tourner_droite.setEnabled(false);
		defausse.setEnabled(false);

		tourner_gauche.addActionListener(
			(ActionEvent e) -> {
				partie.getJoueurCourant().tournerGauche();
				repaint();
		});

		tourner_droite.addActionListener(
			(ActionEvent e) -> {
				partie.getJoueurCourant().tournerDroite();
				repaint();
		});

		defausse.addActionListener(
			(ActionEvent e) -> {
				partie.getJoueurCourant().defausser();
				controleurSouris.postDefausse();
		});

		add(tourner_gauche);
		add(tourner_droite);
		add(defausse);
	}

	public abstract class ControleurSouris implements MouseListener {

		public void preTour(VueTuile vue) {
			pioche();

			if (!partie.getJoueurCourant().estIA()) return;

			int[] pos = partie.getJoueurCourant().peutJouer();
			if (pos[0] == -1) {
				partie.getJoueurCourant().defausser();
				postDefausse();
			} else {
				vuePlateau.setTuile(pos[0], pos[1], partie.getJoueurCourant().getCourante());
				partie.getJoueurCourant().poserTuile(pos[0], pos[1]);
				jouerTuile(vue);
			}
		}
		
		public void jouerTuile(VueTuile vue) {
			courant.setTuile(null);
			courant = null;
			tourner_gauche.setEnabled(false);
			tourner_droite.setEnabled(false);
			defausse.setEnabled(false);
			postPose(vue);
		}

		public void pioche(){
			selectionnerTuile(vueMains.get(partie.getIndiceJoueur()));
			courant.setTuile(partie.getJoueurCourant().getCourante());
		}

		public void selectionnerTuile(VueTuile vue) {
			vue.setSelectionnee(true);
			courant = vue;
			tourner_gauche.setEnabled(true);
			tourner_droite.setEnabled(true);
			defausse.setEnabled(true);
		}

		public void postDefausse() {
			courant.setTuile(null);
			courant = null;
		}

		public abstract void postPose(VueTuile v);

		@Override
		public void mouseClicked(MouseEvent e) {
			VueTuile vue = (VueTuile) e.getSource();

			if(partie.estPosable(vue.getPosX(),vue.getPosY(),courant.getTuile())) {
				partie.getJoueurCourant().poserTuile(vue.getPosX(),vue.getPosY());
				vue.setTuile(courant.getTuile());
				jouerTuile(vue);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			VueTuile vue = (VueTuile)e.getSource();
			vue.setBrillance(true);
			vue.repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			VueTuile vue = (VueTuile)e.getSource();
			vue.setBrillance(false);
			vue.repaint();
		}
		@Override
		public void mouseReleased(MouseEvent e) { }
		@Override
		public void mousePressed(MouseEvent e) { }
	};
}
