import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;

public class VueTuile extends JLabel {
    
    private static String cheminIcone = "../src/ui/icons/";

    private Tuile tuile;
    private BufferedImage icone;
	private boolean brillance;
    
    public static BufferedImage genererIcone(BufferedImage img, int i) {
        int largeur = img.getWidth();
        int hauteur = img.getHeight();

        BufferedImage nouvelleIcone = new BufferedImage(largeur, hauteur, img.getType());
 
        Graphics2D g2 = nouvelleIcone.createGraphics();
 
        g2.rotate(Math.toRadians(90*i), largeur / 2, hauteur / 2);
        g2.drawImage(img, null, 0, 0);
 
        return nouvelleIcone;
    }

    public VueTuile(Tuile t) {
        tuile = t;
		brillance = false;
		
		addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
						System.out.println("meurs");
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						brillance = true;
						repaint();
					}
					@Override
					public void mouseExited(MouseEvent e) {
						brillance = false;
						repaint();
					}
					@Override
					public void mouseReleased(MouseEvent e) {

					}
					@Override
					public void mousePressed(MouseEvent e) {

					}
				});

        if (t != null) {
            try {
                BufferedImage img = ImageIO.read(new File(cheminIcone + ((Parcelle) tuile).getId() + ".png"));
                icone = genererIcone(img, tuile.getOrientation());
            } catch(IOException e) {

            }
        } else {
            setText("0");
        }
    }

	public void setBrillance(boolean b) {
		brillance = b;
	}

    public void paintComponent(Graphics g) {
        if (icone != null && !brillance)
            g.drawImage(icone, 0, 0, 80, 80, null); // 80 correspond à la taille du côté (en px) de la tuile dans le plateau
        else
            g.drawString("0", 40, 40);
    }
}
