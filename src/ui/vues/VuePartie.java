import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VuePartie extends JComponent {
	private Partie partie;
	private Tuile courant;
	
	public VuePartie(Partie p) {
		setLayout(null);
		
		partie = p;
		courant = null;

		MouseListener controleurSouris = new ControleurSouris();

        JPanel plateau = new VuePlateau(800, 800, partie.getPlateau(), controleurSouris);
        plateau.setBounds(20, 20, 800, 800);
        add(plateau);
        
        JPanel main1 = new VueMain(partie.getJoueur(0), controleurSouris);
        main1.setBounds(20, 850, 800, 80);
        add(main1);
	}

	private class ControleurSouris implements MouseListener {
		private VueTuile precedent;

		@Override
		public void mouseClicked(MouseEvent e) {
			VueTuile vue = (VueTuile)e.getSource();

			if (courant == null) {
				if (!vue.setSelectionnee(true)) return;

				courant = vue.getTuile();
				precedent = vue;
			} else {
				if (!vue.setTuile(courant)) return;
				precedent.setTuile(null);
				courant = null;
                precedent = null;
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
