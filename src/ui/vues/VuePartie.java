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

	private JButton pion_haut;
	private JButton pion_droite;
	private JButton pion_bas;
	private JButton pion_gauche;
	private JButton pion_centre;


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

        JPanel plateau = new VuePlateau(800, 800, partie.getPlateau(), controleurSouris, estCarcassonne);
        plateau.setBounds(20, 20, 800, 800);
        add(plateau);

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
        VueTuile main1 = (estCarcassonne) ? new VueParcelle(partie.getJoueur(0).getCourante(), -1, -1, true, controleurSouris) :new VueDomino(partie.getJoueur(0).getCourante(), -1, -1, true, controleurSouris);
		courant = main1;
		controleurSouris.selectionnerTuile(courant);
        main1.setBounds(200, 820, 80, 86);
        add(main1);
		vueMains.add(main1);

        VueTuile main2 = (estCarcassonne) ? new VueParcelle(partie.getJoueur(1).getCourante(), -1, -1, true, controleurSouris) :new VueDomino(partie.getJoueur(1).getCourante(), -1, -1, true, controleurSouris);
        main2.setBounds(600, 820, 80, 86);
        add(main2);
		vueMains.add(main2);

		defausse.addActionListener(
			(ActionEvent e) -> {
				if(partie.getJoueurCourant().defausser()){
					courant.setTuile(null);
					courant = null;
					controleurSouris.postDefausse();
				}
		});

		Color couleur = Color.BLUE;

		pion_haut.addActionListener(
			(ActionEvent e) ->{
				setPionButtonEnabled(false);
				((VueParcelle)courant).ajouterPion(0, false, couleur);
			});
		
		pion_droite.addActionListener(
			(ActionEvent e) ->{			
				setPionButtonEnabled(false);
				((VueParcelle)courant).ajouterPion(3, false, couleur);
				repaint();
			});

		pion_bas.addActionListener(
			(ActionEvent e) ->{			
				setPionButtonEnabled(false);
				((VueParcelle)courant).ajouterPion(2, false, couleur);
				repaint();
			});

		pion_gauche.addActionListener(
			(ActionEvent e) ->{			
				setPionButtonEnabled(false);
				((VueParcelle)courant).ajouterPion(1, false, couleur);
				repaint();
			});

		pion_centre.addActionListener(
			(ActionEvent e) ->{
				setPionButtonEnabled(false);
				((VueParcelle)courant).ajouterPion(4, true, couleur);
				repaint();
			});


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

		public void prePose(VueTuile v){
			((VueParcelle)courant).deplacerPion(v);	
		}
	}

	private class ControleurSourisDomino extends ControleurSouris {
		public void postDefausse() {
			partie.prochainTour();
			super.postPose();
		}

		public void prePose(VueTuile v){}
	}

	private abstract class ControleurSouris implements MouseListener {
		private VueTuile precedent;

		public void jouerTuile(VueTuile vue) {
			vue.setTuile(courant.getTuile());
			prePose(vue);
			precedent.setTuile(null);
			courant.setTuile(null);
			courant = null;
			tourner_gauche.setEnabled(false);
			tourner_droite.setEnabled(false);
			defausse.setEnabled(false);
            precedent = null;
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
			precedent = vue;
			setPionButtonEnabled(true);
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

		public void postPose() {
			if(partie.estFinie()){
				System.out.println("partie terminée");
				return;
			}
			selectionnerTuile(vueMains.get(partie.getIndiceJoueur()));
			courant.setTuile(partie.getJoueurCourant().getCourante());
		}

		public abstract void prePose(VueTuile v);

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
