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
	private LinkedList<VueMain> vueMains;
	
	public VuePartie(Partie p) {
		setLayout(null);
		
		partie = p;
		courant = null;	

		ControleurSouris controleurSouris = new ControleurSourisCarcassonne();

		tourner_gauche = new JButton("⟲");
		tourner_droite = new JButton("⟳");
		defausse = new JButton("❌");
		tourner_gauche.setBounds(850,20,50,50);
		tourner_droite.setBounds(850,80,50,50);
		defausse.setBounds(850,140,50,50);
		tourner_gauche.setEnabled(false);
		tourner_droite.setEnabled(false);
		defausse.setEnabled(false);

        JPanel plateau = new VuePlateau(800, 800, partie.getPlateau(), controleurSouris);
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


		vueMains = new LinkedList<VueMain>();
        VueMain main1 = new VueMain(partie.getJoueur(0), controleurSouris);
        main1.setBounds(20, 820, 800, 86);
        add(main1);
		vueMains.add(main1);

        VueMain main2 = new VueMain(partie.getJoueur(1), controleurSouris);
        main2.setBounds(20, 900, 800, 86);
        add(main2);
		vueMains.add(main2);

		

		defausse.addActionListener(
			(ActionEvent e) -> {
				partie.getJoueurCourant().defausser();
				courant.setTuile(null);
				courant = null;
				for(VueMain vm : vueMains){
					vm.update_suppr();
				}
				controleurSouris.postDefausse();
		});

	}

	private class ControleurSourisCarcassonne extends ControleurSouris {
		public void postDefausse() {
				partie.getJoueurCourant().pioche();
				pioche();
		}

		public void postPose() {
			pioche();
		}
	}

	private class ControleurSourisDomino extends ControleurSouris {
		public void postDefausse() {

		}

		public void postPose() {

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
			for (VueMain vm : vueMains)
				vm.update_suppr();
			postPose();
		}

		public void pioche(){
			for(VueMain vm : vueMains){
				vm.update_ajout();
				if(vm.getJoueur() == partie.getJoueurCourant()){
					courant = vm.getVues().get(vm.getVues().size()-1);
					selectionnerTuile(courant);
				}
			}
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
			if(partie.estFinie()){
				System.out.println("partie terminée");
				return;
			}
			VueTuile vue = (VueTuile)e.getSource();
			if (courant == null || courant.getTuile() == null) {
				if (!vue.estSelectionnable(partie.getJoueurCourant())) return;
				selectionnerTuile(vue);
				partie.getJoueurCourant().setCourante(courant.getTuile());
			} else if(partie.estPosable(vue.getPosX(),vue.getPosY(),courant.getTuile())) {
				partie.getJoueurCourant().poserTuile(vue.getPosX(),vue.getPosY());
				jouerTuile(vue);
			}
		}

		public abstract void postDefausse();
		public abstract void postPose();

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
