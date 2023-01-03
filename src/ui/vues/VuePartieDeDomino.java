import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class VuePartieDeDomino extends VuePartie {

	private int[] score_domino = {0, 0, 0, 0};

	public VuePartieDeDomino(PartieDeDomino p) {
		super(p);
		controleurSouris = new ControleurSourisDomino();
		debuterPartie();
	}

	public void initMains() {
		VueTuile main1 = new VueDomino(partie.getJoueur(0).getCourante(), -1, -1, controleurSouris);
		vueMains.add(main1);

		VueTuile main2 = new VueDomino(partie.getJoueur(1).getCourante(), -1, -1, controleurSouris);
		vueMains.add(main2);

		if (partie.getNbJoueurs() > 2) {
			VueTuile main3 = new VueDomino(partie.getJoueur(2).getCourante(), -1, -1, controleurSouris);
			vueMains.add(main3);
		}

		if (partie.getNbJoueurs() > 3) {
			VueTuile main4 = new VueDomino(partie.getJoueur(3).getCourante(), -1, -1, controleurSouris);
			vueMains.add(main4);
		}
	}

	public void initPlateau() {
		vuePlateau = new VuePlateau(800, 800, partie.getPlateau(), controleurSouris, false);
	}

	private class ControleurSourisDomino extends ControleurSouris {

		public void postDefausse() {
			super.postDefausse();
			partie.prochainTour();
			postPose(courant);
		}

		public void postPose(VueTuile v) {
			if(partie.estFinie()){
				removeAll();
				setLayout(new GridBagLayout());
				JButton ok = new JButton("ok");
				ok.addActionListener((ActionEvent e) -> {
					System.exit(0);
				});
				add(new JLabel("La partie est terminée  "));
				add(ok);
				validate();
				repaint();
				return;
			}
			if(v!=null){
				//+3 = -1+4 pour que l'indice soit toujours positif
				score_domino[(partie.getIndiceJoueur()+3) % partie.getNbJoueurs()]+=partie.calculScore(v.getPosX(),v.getPosY());
			}
			System.out.println(score_domino[0]+" | "+score_domino[1]+" | "+score_domino[2]+" | "+score_domino[3]);
			selectionnerTuile(vueMains.get(partie.getIndiceJoueur()));
			courant.setTuile(partie.getJoueurCourant().getCourante());
			preTour(v);
		}
	}
}
