import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class VuePartie extends JComponent {
	private Partie partie;	
	private ControleurSouris controleurSouris;

	private VueTuile courant;
	private VueTuile VuePion;
	private VuePlateau vuePlateau;
	private LinkedList<VueTuile> vueMains;

	private static JButton tourner_gauche;
	private static JButton tourner_droite;
	private static JButton defausse;
	private static JButton pion_haut;
	private static JButton pion_droite;
	private static JButton pion_bas;
	private static JButton pion_gauche;
	private static JButton pion_centre;

	private static Color[] couleurs_pions = {Color.BLUE,Color.RED,Color.GREEN,Color.YELLOW};
	private static int[] pions_restant = {8,8,8,8};

	public VuePartie(Partie p) {
		setLayout(null);		
		partie = p;
		boolean estCarcassonne = (partie instanceof PartieDeCarcassonne);
		courant = null;	
		ControleurSouris controleurSouris = (estCarcassonne) ? new ControleurSourisCarcassonne() : new ControleurSourisDomino();

		initBoutons(estCarcassonne); 

		vuePlateau = new VuePlateau(800, 800, partie.getPlateau(), controleurSouris, estCarcassonne);
		vuePlateau.setBounds(20, 20, 800, 800);
		add(vuePlateau);


		vueMains = new LinkedList<VueTuile>();

		VueTuile main1 = (estCarcassonne) ?
			new VueParcelle(partie.getJoueur(0).getCourante(), -1, -1, controleurSouris)
			: new VueDomino(partie.getJoueur(0).getCourante(), -1, -1, controleurSouris);
		main1.setBounds(20, 820, 80, 86);
		add(main1);
		vueMains.add(main1);

		VueTuile main2 = (estCarcassonne) ?
			new VueParcelle(partie.getJoueur(1).getCourante(), -1, -1, controleurSouris)
			: new VueDomino(partie.getJoueur(1).getCourante(), -1, -1, controleurSouris);
		main2.setBounds(260, 820, 80, 86);
		add(main2);
		vueMains.add(main2);

		if (partie.getNbJoueurs() > 2) {
			VueTuile main3 = (estCarcassonne) ?
				new VueParcelle(partie.getJoueur(2).getCourante(), -1, -1, controleurSouris)
				: new VueDomino(partie.getJoueur(2).getCourante(), -1, -1, controleurSouris);
			main3.setBounds(500, 820, 80, 86);
			add(main3);
			vueMains.add(main3);
		}

		if (partie.getNbJoueurs() > 3) {
			VueTuile main4 = (estCarcassonne) ?
				new VueParcelle(partie.getJoueur(3).getCourante(), -1, -1, controleurSouris)
				: new VueDomino(partie.getJoueur(3).getCourante(), -1, -1, controleurSouris);
			main4.setBounds(740, 820, 80, 86);
			add(main4);
			vueMains.add(main4);
		}

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


		pion_haut.addActionListener(
			(ActionEvent e) ->{
				ajoutePion(0);
			});
		
		pion_droite.addActionListener(
			(ActionEvent e) ->{			
				ajoutePion(3);
				});

		pion_bas.addActionListener(
			(ActionEvent e) ->{			
				ajoutePion(2);
				});

		pion_gauche.addActionListener(
			(ActionEvent e) ->{			
				ajoutePion(1);
				});

		pion_centre.addActionListener(
			(ActionEvent e) ->{
				ajoutePion(4);
				});


		controleurSouris.preTour(courant);
	}

	private void ajoutePion(int pos) {
		if(pions_restant[partie.getIndiceJoueur()]>=1) {
			pions_restant[partie.getIndiceJoueur()]--;
			((VueParcelle)VuePion).ajouterPion(pos, couleurs_pions[partie.getIndiceJoueur()]);
			repaint();
		}
	}

	private void initBoutons(boolean estCarcassonne) {
		tourner_gauche = new JButton("⟲");
		tourner_droite = new JButton("⟳");
		defausse = new JButton("❌");
		tourner_gauche.setBounds(900,50,50,50);
		tourner_droite.setBounds(900,110,50,50);
		defausse.setBounds(900,170,50,50);
		tourner_gauche.setEnabled(false);
		tourner_droite.setEnabled(false);
		defausse.setEnabled(false);


		pion_haut = new JButton("↑");
		pion_droite = new JButton("→");
		pion_centre = new JButton("▪");
		pion_gauche = new JButton("←");
		pion_bas = new JButton("↓");

		add(tourner_gauche);
		add(tourner_droite);
		add(defausse);

		if(estCarcassonne) {
			pion_haut.setBounds(900,400,50,50);
			pion_droite.setBounds(950,450,50,50);
			pion_bas.setBounds(900,500,50,50);
			pion_gauche.setBounds(850,450,50,50);
			pion_centre.setBounds(900,450,50,50);

			setPionButtonEnabled(false);

			add(pion_haut);
			add(pion_droite);
			add(pion_bas);
			add(pion_gauche);
			add(pion_centre);
		}

	}

	private void setPionButtonEnabled(boolean b) {
			pion_haut.setEnabled(b);
			pion_droite.setEnabled(b);
			pion_bas.setEnabled(b);
			pion_gauche.setEnabled(b);
			pion_centre.setEnabled(b);
	}

	private class ControleurSourisCarcassonne extends ControleurSouris {

		public void postDefausse() {
			super.postDefausse();
			partie.getJoueurCourant().pioche();
			pioche();
		}

		public void postPose(VueTuile v) {
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

			VuePion = v;
			setPionButtonEnabled(true);

			selectionnerTuile(vueMains.get(partie.getIndiceJoueur()));
			courant.setTuile(partie.getJoueurCourant().getCourante());
			preTour(v);
		}
	}

	private class ControleurSourisDomino extends ControleurSouris {

		public void postDefausse() {
			super.postDefausse();
			partie.prochainTour();
			postPose(courant);
		}

		public void postPose(VueTuile v) {
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
			selectionnerTuile(vueMains.get(partie.getIndiceJoueur()));
			courant.setTuile(partie.getJoueurCourant().getCourante());
			preTour(v);
		}
	}

	private abstract class ControleurSouris implements MouseListener {

		public abstract void postPose(VueTuile v);


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
