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

	public void finDePartie(){
		removeAll();
		setLayout(new BoxLayout(this,1));
		JPanel fin = new JPanel(new GridLayout(6,1));

		JPanel partie_finie = new JPanel(new GridBagLayout());
		JButton ok = new JButton("ok"); 
		ok.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});

		partie_finie.add(new JLabel("La partie est terminée  "));
		partie_finie.add(ok);
		fin.add(partie_finie);


		JPanel scores = new JPanel(new GridLayout(5,1));

		JPanel score = new JPanel(new GridBagLayout());
		score.add(new JLabel("Score : "));
		scores.add(score);

		for (int i = 0; i < partie.getNbJoueurs(); i++) {
			JPanel scoreJoueur = new JPanel(new GridBagLayout());

			String abandon = (partie.getJoueur(i).getAbandon()) ? " (abandon)" : "";
			JLabel label = new JLabel("Joueur " + (i+1) + " : " + partie.getJoueur(i).getScore() + abandon);
			label.setForeground(partie.getJoueur(i).getCouleur());

			scoreJoueur.add(label);
			scores.add(scoreJoueur);
		}

		fin.add(scores);
		add(fin);

		validate();
		repaint();
	}

	private class ControleurSourisDomino extends ControleurSouris {

		public void postDefausse() {
			super.postDefausse();
			partie.prochainTour();
			postPose(courant);
		}

		public void postPose(VueTuile v) {
			selectionnerTuile(vueMains.get(partie.getIndiceJoueur()));
			courant.setTuile(partie.getJoueurCourant().getCourante());
			preTour(v);
		}
	}
}
