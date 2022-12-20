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
		
        JPanel plateau = new VuePlateau(800, 800, partie.getPlateau(), controleurSouris, estCarcassonne);
        plateau.setBounds(20, 20, 800, 800);
        add(plateau);

		vueMains = new LinkedList<VueTuile>();

        VueTuile main1 = (estCarcassonne) ? new VueParcelle(partie.getJoueur(0).getCourante(), -1, -1, true, controleurSouris) :new VueDomino(partie.getJoueur(0).getCourante(), -1, -1, true, controleurSouris);
		main1.setBounds(200, 820, 80, 86);		        
        VueTuile main2 = (estCarcassonne) ? new VueParcelle(partie.getJoueur(1).getCourante(), -1, -1, true, controleurSouris) :new VueDomino(partie.getJoueur(1).getCourante(), -1, -1, true, controleurSouris);
        main2.setBounds(600, 820, 80, 86);

		add(main1);
		add(main2);
		vueMains.add(main1);
		vueMains.add(main2);

		courant = main1;
		controleurSouris.selectionnerTuile(courant);

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
				if(partie.getJoueurCourant().defausser()){
					courant.setTuile(null);
					courant = null;
					controleurSouris.postDefausse();
				}
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


	}

	private void ajoutePion(int pos){				
		if(pions_restant[partie.getIndiceJoueur()]>=1){
			pions_restant[partie.getIndiceJoueur()]--;
			((VueParcelle)VuePion).ajouterPion(pos, couleurs_pions[partie.getIndiceJoueur()]);
			repaint();
		}		
	}

	private void initBoutons(boolean estCarcassonne){
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
		
		if(estCarcassonne){	

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

	private void setPionButtonEnabled(boolean b){
			pion_haut.setEnabled(b);
			pion_droite.setEnabled(b);
			pion_bas.setEnabled(b);
			pion_gauche.setEnabled(b);
			pion_centre.setEnabled(b);

	}

	private class ControleurSourisCarcassonne extends ControleurSouris {
		public void postDefausse() {
				partie.getJoueurCourant().pioche();
				pioche();
		}

		public void postPose(VueTuile v) {
			if(partie.estFinie()){
				System.out.println("partie terminée");
				return;
			}
			VuePion = v;
			setPionButtonEnabled(true);
			
			selectionnerTuile(vueMains.get(partie.getIndiceJoueur()));
			courant.setTuile(partie.getJoueurCourant().getCourante());
		}
	
		
	}

	private class ControleurSourisDomino extends ControleurSouris {
		public void postDefausse() {
			partie.prochainTour();
			postPose(courant);
		}

		public void postPose(VueTuile v) {
			if(partie.estFinie()){
				System.out.println("partie terminée");
				return;
			}
			selectionnerTuile(vueMains.get(partie.getIndiceJoueur()));
			courant.setTuile(partie.getJoueurCourant().getCourante());
		}



	}

	private abstract class ControleurSouris implements MouseListener {
		private VueTuile precedent;

		public void jouerTuile(VueTuile vue) {
			vue.setTuile(courant.getTuile());
			precedent.setTuile(null);
			courant.setTuile(null);
			courant = null;
			tourner_gauche.setEnabled(false);
			tourner_droite.setEnabled(false);
			defausse.setEnabled(false);
            precedent = null;
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
			precedent = vue;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			VueTuile vue = (VueTuile)e.getSource();
			if (courant == null || courant.getTuile() == null) {
				if (!vue.estSelectionnable(partie.getJoueurCourant())) return;
				selectionnerTuile(vue);
			} else if(partie.estPosable(vue.getPosX(),vue.getPosY(),courant.getTuile())) {
				partie.getJoueurCourant().poserTuile(vue.getPosX(),vue.getPosY());
				jouerTuile(vue);
			}
		}



		public abstract void postPose(VueTuile v);

		public abstract void postDefausse();

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
