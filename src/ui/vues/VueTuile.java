import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class VueTuile extends JLabel {
    
    private Tuile tuile;

    public VueTuile(Tuile t) {
        tuile = t;
        if (t != null) {
            setText(((Domino) tuile).toString());
        } else {
            setText("1\n1\n1");
        }
    }

    public String afficherDomino() {
        return "";
    }
}
