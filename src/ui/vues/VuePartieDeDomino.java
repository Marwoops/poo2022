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

		JPanel score1 = new JPanel(new GridBagLayout());
		score1.add(new JLabel("Joueur 1 : "+partie.getJoueur(0).getScore()));
		scores.add(score1);
		JPanel score2 = new JPanel(new GridBagLayout());
		score2.add(new JLabel("Joueur 2 : "+partie.getJoueur(1).getScore()));
		scores.add(score2);
		if(partie.getNbJoueurs()>2){
JPanel score3 = new JPanel(new GridBagLayout());
		score3.add(new JLabel("Joueur 3 : "+partie.getJoueur(2).getScore()));
		scores.add(score3);
		}
		if(partie.getNbJoueurs()>3){
JPanel score4 = new JPanel(new GridBagLayout());
		score4.add(new JLabel("Joueur 4 : "+partie.getJoueur(3).getScore()));
		scores.add(score4);
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
			if(partie.estFinie()){
				finDePartie();
				return;
			}
			selectionnerTuile(vueMains.get(partie.getIndiceJoueur()));
			courant.setTuile(partie.getJoueurCourant().getCourante());
			preTour(v);
		}
	}
}
