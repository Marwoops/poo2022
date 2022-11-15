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
        
        JPanel main1 = new VueMain(partie.getJoueur1(), ml);
        main1.setBounds(20, 850, 800, 80);
        add(main1);
	}

	private class ML implements MouseListener {
		private VueTuile precedent;

		@Override
		public void mouseClicked(MouseEvent e) {
			VueTuile vue = (VueTuile)e.getSource();

			if (courrant == null && vue.getMouvable()) {
				courrant = vue.getTuile();
				precedent = vue;
			} else {
				precedent.setTuile(null);
				vue.setTuile(courrant);
				precedent.repaint();
				vue.repaint();	
				courrant = null;
			}
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {

		}
		@Override
		public void mouseExited(MouseEvent e) {

		}
		@Override
		public void mouseReleased(MouseEvent e) {

		}
		@Override
		public void mousePressed(MouseEvent e) {

		}
	};
}
