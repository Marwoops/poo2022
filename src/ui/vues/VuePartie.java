import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class VuePartie extends JComponent {
	private Partie partie;
	private Tuile courant;
	private VuePlateau vuePlateau;
	private LinkedList<VueMain> vueMains;
	
	public VuePartie(Partie p) {
		setLayout(null);
		
		partie = p;
		courant = null;

		MouseListener controleurSouris = new ControleurSouris();

        vuePlateau = new VuePlateau(800, 800, partie.getPlateau(), controleurSouris);
        vuePlateau.setBounds(20, 20, 800, 800);
        add(vuePlateau);
        
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

		@Override
		public void mouseClicked(MouseEvent e) {
			VueTuile vue = (VueTuile)e.getSource();

			if (courant == null) {
				// selection de la tuile
				if (!vue.estSelectionnable(partie.getJoueurCourant())) return;
				vue.setSelectionnee(true);
				courant = vue.getTuile();
				precedent = vue;
			} else if (partie.jouerTour(vue.getPosX(), vue.getPosY(), precedent.getTuile())) {
				vue.setTuile(courant);
				precedent.setTuile(null);
				courant = null;
                precedent = null;
				for (VueMain vm : vueMains)
					vm.update();
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
