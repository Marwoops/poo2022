import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VuePartie extends JComponent {
	private Partie partie;
	private Tuile courrant;
	
	public VuePartie(Partie p) {
		setLayout(null);
		
		partie = p;
		courrant = null;

		MouseListener ml = new ML();

        JPanel plateau = new VuePlateau(800, 800, partie.getPlateau(), ml);
        plateau.setBounds(20, 20, 800, 800);
        add(plateau);
        
        JPanel main1 = new VueMain(partie.getJoueur(0), ml);
        main1.setBounds(20, 850, 800, 80);
        add(main1);
	}

	private class ML implements MouseListener {
		private VueTuile precedent;

		@Override
		public void mouseClicked(MouseEvent e) {
			VueTuile vue = (VueTuile)e.getSource();

			if (courrant == null) {
				if (!vue.setSelectionnee(true)) return;

				courrant = vue.getTuile();
				precedent = vue;
			} else {
				if (!vue.setTuile(courrant)) return;
				precedent.setTuile(null);
				courrant = null;
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
