import java.awt.*;
import javax.swing.*;

public class VuePlateau extends JComponent {
    
    public static final int largeur = 200;
    public static final int hauteur = 200;
    
    private Plateau plat;

    public void paintComponent(Graphics g) {

    }

    public Dimension getPreferredSize() {
        return new Dimension(largeur, hauteur);
    }
}
