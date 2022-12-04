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

		addKeyListener(new ControleurKey());

        JPanel plateau = new VuePlateau(800, 800, partie.getPlateau(), controleurSouris);
        plateau.setBounds(20, 20, 800, 800);
        add(plateau);
        
        JPanel main1 = new VueMain(partie.getJoueur(0), controleurSouris);
        main1.setBounds(20, 820, 800, 86);
        add(main1);
        JPanel main2 = new VueMain(partie.getJoueur(1), controleurSouris);
        main2.setBounds(20, 900, 800, 86);
        add(main2);
	}

	private class ControleurKey implements KeyListener{
		

		public void keyPressed(KeyEvent e){

			System.out.println("bonjour");	

			//VueTuile vue = (VueTuile)e.getSource();

			//if(!vue.getSelectionnee()){return;}

			if(e.getKeyCode()==e.VK_KP_LEFT){
				courant.tournerGauche();
			}
			if(e.getKeyCode()==e.VK_KP_RIGHT){
				courant.tournerDroite();
			}
			//générer nouvelle tuile ?
			//vue.repaint();

		}

		public void keyReleased(KeyEvent e){
			System.out.println("bonjour");	
		}

		public void keyTyped(KeyEvent e){
			System.out.println("bonjour");	
		}
	
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
			} else if (partie.estPosable(vue.getPosX(), vue.getPosY(), precedent.getTuile()) && vue.setTuile(courant)) {
				// pose de la tuile
				partie.poserTuile(vue.getPosX(), vue.getPosY(), precedent.getTuile());
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
