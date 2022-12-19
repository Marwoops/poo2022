import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class VuePartie extends JComponent {
	private Partie partie;
	private VueTuile courant;
    private JButton tourner_gauche;
	private JButton tourner_droite;
	private JButton defausse;
	private ControleurSouris controleurSouris;


	private VuePlateau vuePlateau;
	private LinkedList<VueTuile> vueMains;
	
	public VuePartie(Partie p) {
		setLayout(null);
		
		partie = p;
		boolean estCarcassonne = (partie instanceof PartieDeCarcassonne);

		courant = null;	

		ControleurSouris controleurSouris = (estCarcassonne) ? new ControleurSourisCarcassonne() : new ControleurSourisDomino();

		tourner_gauche = new JButton("⟲");
		tourner_droite = new JButton("⟳");
		defausse = new JButton("❌");
		tourner_gauche.setBounds(850,20,50,50);
		tourner_droite.setBounds(850,80,50,50);
		defausse.setBounds(850,140,50,50);
		tourner_gauche.setEnabled(false);
		tourner_droite.setEnabled(false);
		defausse.setEnabled(false);

        vuePlateau = new VuePlateau(800, 800, partie.getPlateau(), controleurSouris, estCarcassonne);
        vuePlateau.setBounds(20, 20, 800, 800);
        add(vuePlateau);

		add(tourner_gauche);
		add(tourner_droite);
		add(defausse);

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


		vueMains = new LinkedList<VueTuile>();

		VueTuile main1 = (estCarcassonne) ?
			new VueParcelle(partie.getJoueur(0).getCourante(), -1, -1, true, controleurSouris)
			: new VueDomino(partie.getJoueur(0).getCourante(), -1, -1, true, controleurSouris);
        main1.setBounds(20, 820, 80, 86);
        add(main1);
		vueMains.add(main1);

        VueTuile main2 = (estCarcassonne) ?
			new VueParcelle(partie.getJoueur(1).getCourante(), -1, -1, true, controleurSouris)
			: new VueDomino(partie.getJoueur(1).getCourante(), -1, -1, true, controleurSouris);
        main2.setBounds(260, 820, 80, 86);
        add(main2);
		vueMains.add(main2);

		if (partie.getNbJoueurs() > 2) {
			VueTuile main3 = (estCarcassonne) ?
				new VueParcelle(partie.getJoueur(2).getCourante(), -1, -1, true, controleurSouris)
				: new VueDomino(partie.getJoueur(2).getCourante(), -1, -1, true, controleurSouris);
			main3.setBounds(500, 820, 80, 86);
			add(main3);
			vueMains.add(main3);
		}

		if (partie.getNbJoueurs() > 3) {
			VueTuile main4 = (estCarcassonne) ?
				new VueParcelle(partie.getJoueur(3).getCourante(), -1, -1, true, controleurSouris)
				: new VueDomino(partie.getJoueur(3).getCourante(), -1, -1, true, controleurSouris);
			main4.setBounds(740, 820, 80, 86);
			add(main4);
			vueMains.add(main4);
		}

		defausse.addActionListener(
			(ActionEvent e) -> {
				partie.getJoueurCourant().defausser();
				controleurSouris.postDefausse();
		});

		controleurSouris.preTour();
	}

	private class ControleurSourisCarcassonne extends ControleurSouris {
		public void postDefausse() {
			super.postDefausse();
			partie.getJoueurCourant().pioche();
			pioche();
		}
	}

	private class ControleurSourisDomino extends ControleurSouris {
		public void postDefausse() {
			super.postDefausse();
			partie.prochainTour();
			super.postPose();
		}
	}

	private class ControleurSouris implements MouseListener {

		public void jouerTuile() {
			courant.setTuile(null);
			courant = null;
			tourner_gauche.setEnabled(false);
			tourner_droite.setEnabled(false);
			defausse.setEnabled(false);
			postPose();
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

		@Override
		public void mouseClicked(MouseEvent e) {
			VueTuile vue = (VueTuile) e.getSource();

			if(partie.estPosable(vue.getPosX(),vue.getPosY(),courant.getTuile())) {
				partie.getJoueurCourant().poserTuile(vue.getPosX(),vue.getPosY());
				vue.setTuile(courant.getTuile());
				jouerTuile();
			}
		}

		public void preTour() {
			pioche();

			if (!partie.getJoueurCourant().estIA()) return;

			int[] pos = partie.getJoueurCourant().peutJouer();
			if (pos[0] == -1) {
				partie.getJoueurCourant().defausser();
				postDefausse();
			} else {
				vuePlateau.setTuile(pos[0], pos[1], partie.getJoueurCourant().getCourante());
				partie.getJoueurCourant().poserTuile(pos[0], pos[1]);
				jouerTuile();
			}
		}

		public void postPose() {
			if(partie.estFinie()){
				removeAll();
				setLayout(new GridBagLayout());
				JButton ok = new JButton("ok");
				ok.addActionListener((ActionEvent e) -> {
					System.exit(0);
				});
				add(new JLabel("La partie est terminée  "));
				add(ok);
				validate();
				repaint();
				return;
			}
			preTour();
		}

		public void postDefausse() {
			courant.setTuile(null);
			courant = null;
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
