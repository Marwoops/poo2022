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
    private static Border border = BorderFactory.createLineBorder(Color.YELLOW,5);

    private Tuile tuile;
    private BufferedImage icone;
	private boolean brillance;
	private boolean mouvable;
    
    public static BufferedImage genererIcone(BufferedImage img, int i) {
        int largeur = img.getWidth();
        int hauteur = img.getHeight();

        BufferedImage nouvelleIcone = new BufferedImage(largeur, hauteur, img.getType());
 
        Graphics2D g2 = nouvelleIcone.createGraphics();
 
        g2.rotate(Math.toRadians(90*i), largeur / 2, hauteur / 2);
        g2.drawImage(img, null, 0, 0);
 
        return nouvelleIcone;
    }

    public VueTuile(Tuile t, boolean m, MouseListener ml) {
        setTuile(t);
		brillance = false;
		mouvable = m;
		addMouseListener(ml);
    }

	public Tuile getTuile() {
		return tuile;
	}

	public boolean getMouvable() {
		return mouvable;
	}

	public void setTuile(Tuile t) {
		tuile = t;
		setImg();
        repaint();
	}

	public void setBrillance(boolean b) {
		brillance = b;
        if (brillance)
            setBorder(border);
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
}
