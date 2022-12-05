import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VuePartie extends JComponent {
	private Partie partie;
	private Tuile courant;
    private JButton tourner_gauche;
	private JButton tourner_droite;


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
				courant.tournerGauche();
				repaint();
		});

		tourner_droite.addActionListener(
			(ActionEvent e) -> {
				courant.tournerDroite();
				repaint();
		});
        
        JPanel main1 = new VueMain(partie.getJoueur(0), controleurSouris);
        main1.setBounds(20, 820, 800, 86);
        add(main1);
        JPanel main2 = new VueMain(partie.getJoueur(1), controleurSouris);
        main2.setBounds(20, 900, 800, 86);
        add(main2);
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
				tourner_gauche.setEnabled(true);
				tourner_droite.setEnabled(true);
				precedent = vue;
			} else if (partie.estPosable(vue.getPosX(), vue.getPosY(), precedent.getTuile()) && vue.setTuile(courant)) {
				// pose de la tuile
				partie.poserTuile(vue.getPosX(), vue.getPosY(), precedent.getTuile());
				precedent.setTuile(null);
				courant = null;
				tourner_gauche.setEnabled(false);
				tourner_droite.setEnabled(false);
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
