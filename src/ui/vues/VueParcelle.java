import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.border.Border;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;

public class VueParcelle extends VueTuile {

    private static String cheminIcone = "../src/ui/icons/";
    private BufferedImage icone;
    
	public static BufferedImage genererIcone(BufferedImage img, int i) {
        int largeur = img.getWidth();
        int hauteur = img.getHeight();

        BufferedImage nouvelleIcone = new BufferedImage(largeur, hauteur, img.getType());
 
        Graphics2D g2 = nouvelleIcone.createGraphics();
 
        g2.rotate(Math.toRadians(-90*i), largeur / 2, hauteur / 2);
        g2.drawImage(img, null, 0, 0);
 
        return nouvelleIcone;
    }

	public VueParcelle(Tuile t, int x, int y, boolean m, MouseListener controleur) {
		super(t, x, y, m, controleur);
	}

	public void updateVue() {
		if (getTuile() != null) {
            try {
                BufferedImage img = ImageIO.read(new File(cheminIcone + ((Parcelle) getTuile()).getId() + ".png"));
                setOrientation(getTuile().getOrientation());
				icone = genererIcone(img, getOrientation());
            } catch(IOException e) {

            }
        } else {
			icone = null;
        }
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        if (icone != null)
            g.drawImage(icone, 0, 0, 80, 80, null); // 80 correspond à la taille du côté (en px) de la tuile dans le plateau
        else
            g.drawString("0", 40, 40);
	}

}
