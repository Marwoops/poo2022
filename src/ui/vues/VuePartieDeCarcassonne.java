// package ui.vues;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class VuePartieDeCarcassonne extends VuePartie {

	private JButton pion_haut;
	private JButton pion_droite;
	private JButton pion_bas;
	private JButton pion_gauche;
	private JButton pion_centre;

	private static Color[] couleurs_pions = {Color.BLUE,Color.RED,Color.GREEN,Color.YELLOW};
	private int[] pions_restant = {8,8,8,8};

	private VueTuile VuePion;


	public VuePartieDeCarcassonne(PartieDeCarcassonne p) {
		super(p);
		controleurSouris = new ControleurSourisCarcassonne();
		initBoutonsCarcassonne();
		debuterPartie();
	}

	private void initBoutonsCarcassonne() {
		pion_haut = new JButton("↑");
		pion_droite = new JButton("→");
		pion_centre = new JButton("▪");
		pion_gauche = new JButton("←");
		pion_bas = new JButton("↓");

		pion_haut.setBounds(900,400,50,50);
		pion_droite.setBounds(950,450,50,50);
		pion_bas.setBounds(900,500,50,50);
		pion_gauche.setBounds(850,450,50,50);
		pion_centre.setBounds(900,450,50,50);

		setPionButtonEnabled(false);

		add(pion_haut);
		add(pion_droite);
		add(pion_bas);
		add(pion_gauche);
		add(pion_centre);

		pion_haut.addActionListener(
			(ActionEvent e) ->{
				ajoutePion(0);
			});
		
		pion_droite.addActionListener(
			(ActionEvent e) ->{
				ajoutePion(3);
				});

		pion_bas.addActionListener(
			(ActionEvent e) -> {
				ajoutePion(2);
				});

		pion_gauche.addActionListener(
			(ActionEvent e) -> {
				ajoutePion(1);
				});

		pion_centre.addActionListener(
			(ActionEvent e) -> {
				ajoutePion(4);
				});
	}
	
	public void initMains() {
		VueTuile main1 = new VueParcelle(partie.getJoueur(0).getCourante(), -1, -1, controleurSouris);
		vueMains.add(main1);

		VueTuile main2 = new VueParcelle(partie.getJoueur(1).getCourante(), -1, -1, controleurSouris);
		vueMains.add(main2);

		if (partie.getNbJoueurs() > 2) {
			VueTuile main3 = new VueParcelle(partie.getJoueur(2).getCourante(), -1, -1, controleurSouris);
			vueMains.add(main3);
		}

		if (partie.getNbJoueurs() > 3) {
			VueTuile main4 = new VueParcelle(partie.getJoueur(3).getCourante(), -1, -1, controleurSouris);
			vueMains.add(main4);
		}
	}

	public void initPlateau() {
		vuePlateau = new VuePlateau(800, 800, partie.getPlateau(), controleurSouris, true);
	}

	private void ajoutePion(int pos) {
		if(pions_restant[partie.getIndiceJoueur()]>=1) {
			pions_restant[partie.getIndiceJoueur()]--;
			((VueParcelle)VuePion).ajouterPion(pos, couleurs_pions[partie.getIndiceJoueur()]);
			repaint();
		}
	}

	private void setPionButtonEnabled(boolean b) {
			pion_haut.setEnabled(b);
			pion_droite.setEnabled(b);
			pion_bas.setEnabled(b);
			pion_gauche.setEnabled(b);
			pion_centre.setEnabled(b);
	}

	public void finDePartie(){
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
	}

	private class ControleurSourisCarcassonne extends ControleurSouris {

		public void postDefausse() {
			super.postDefausse();
			partie.getJoueurCourant().pioche();
			pioche();
			preTour(courant);
		}

		public void postPose(VueTuile v) {
			VuePion = v;
			setPionButtonEnabled(true);

			selectionnerTuile(vueMains.get(partie.getIndiceJoueur()));
			courant.setTuile(partie.getJoueurCourant().getCourante());
			preTour(v);
		}
	}
}
