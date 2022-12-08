import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.border.Border;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;

public class VueTuile extends JLabel {
    
    private static String cheminIcone = "../src/ui/icons/";
    private static Border bordureSelection = BorderFactory.createLineBorder(Color.YELLOW,5);
    private static Border bordureBrillance = BorderFactory.createLineBorder(Color.BLACK,3);

    private Tuile tuile;
	private int posX;
	private int posY;
    private BufferedImage icone;
	private boolean brillance;
	private boolean selectionnee;
	private boolean mouvable;
    
    public static BufferedImage genererIcone(BufferedImage img, int i) {
        int largeur = img.getWidth();
        int hauteur = img.getHeight();

        BufferedImage nouvelleIcone = new BufferedImage(largeur, hauteur, img.getType());
 
        Graphics2D g2 = nouvelleIcone.createGraphics();
 
        g2.rotate(Math.toRadians(-90*i), largeur / 2, hauteur / 2);
        g2.drawImage(img, null, 0, 0);
 
        return nouvelleIcone;
    }

    public VueTuile(Tuile t, int x, int y, boolean m, MouseListener controleur) {
		setPreferredSize(getPreferredSize());
        setTuile(t);
		posX = x;
		posY = y;
		brillance = false;
		selectionnee = false;
		mouvable = m;
		addMouseListener(controleur);
    }

	public Tuile getTuile() {
		return tuile;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public boolean getMouvable() {
		return mouvable;
	}

	public boolean estVide() {
		return tuile == null;
	}

	public boolean setTuile(Tuile t) {
		if (tuile != null && t != null)
			return false;

		if (t == null) {
			selectionnee = false;
			setBorder(null);
		}

		tuile = t;
		setImg();
        repaint();
		return true;
	}

	public boolean setBrillance(boolean b) {
		if (selectionnee)
			return false;

		brillance = b;

        if (brillance)
            setBorder(bordureBrillance);
        else
            setBorder(null);

        repaint();
		return true;
	}

	public boolean estSelectionnable(Joueur courant) {
		return (mouvable && tuile != null && tuile.getJoueur() == courant);
	}

	public void setSelectionnee(boolean s) {
		selectionnee = s;
		if (s)
			setBorder(bordureSelection);
		else
			setBorder(null);
		repaint();
	}
	
	private void setImg() {
		if (tuile != null) {
            try {
                BufferedImage img = ImageIO.read(new File(cheminIcone + ((Parcelle) tuile).getId() + ".png"));
                icone = genererIcone(img, tuile.getOrientation());
            } catch(IOException e) {

            }
        } else {
			icone = null;
        }
	}

    public void paintComponent(Graphics g) {
        if (icone != null)
            g.drawImage(icone, 0, 0, 80, 80, null); // 80 correspond à la taille du côté (en px) de la tuile dans le plateau
        else
            g.drawString("0", 40, 40);
    }

	public Dimension getPreferredSize() {
		return new Dimension(80,80);
	}
}
