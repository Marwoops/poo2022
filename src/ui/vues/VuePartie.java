import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class VuePartie extends JComponent {
	private Partie partie;
	private VueTuile courant;
    private JButton tourner_gauche;
	private JButton tourner_droite;


	private VuePlateau vuePlateau;
	private LinkedList<VueMain> vueMains;
	
	public VuePartie(Partie p) {
		setLayout(null);
		
		partie = p;
		courant = null;	

		MouseListener controleurSouris = new ControleurSouris();

		tourner_gauche = new JButton("⟲");
		tourner_droite = new JButton("⟳");
		tourner_gauche.setBounds(850,20,50,50);
		tourner_droite.setBounds(850,80,50,50);
		tourner_gauche.setEnabled(false);
		tourner_droite.setEnabled(false);

        JPanel plateau = new VuePlateau(800, 800, partie.getPlateau(), controleurSouris);
        plateau.setBounds(20, 20, 800, 800);
        add(plateau);

		add(tourner_gauche);
		add(tourner_droite);

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
	}



	private class ControleurSouris implements MouseListener {
		private VueTuile precedent;

		public void jouerTuile(VueTuile vue) {
				vue.setTuile(courant.getTuile());
				precedent.setTuile(null);
				courant.setTuile(null);
				courant = null;
				tourner_gauche.setEnabled(false);
				tourner_droite.setEnabled(false);
                precedent = null;
				for (VueMain vm : vueMains)
					vm.update();
		}

		public void selectionnerTuile(VueTuile vue) {
			vue.setSelectionnee(true);
			courant = vue;
			tourner_gauche.setEnabled(true);
			tourner_droite.setEnabled(true);
			precedent = vue;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
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
		@Override
		public void mouseEntered(MouseEvent e) {
			VueTuile vue = (VueTuile)e.getSource();
            vue.setBrillance(true);
		}
		@Override
		public void mouseExited(MouseEvent e) {
            VueTuile vue = (VueTuile)e.getSource();
            vue.setBrillance(false);
		}
		@Override
		public void mouseReleased(MouseEvent e) {

		}
		@Override
		public void mousePressed(MouseEvent e) {

		}
	};
}
