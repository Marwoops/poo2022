import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class VueTuile extends JLabel {
    
    private Tuile tuile;

    public VueTuile(Tuile t) {
        tuile = t;
        if (t != null) {
            setText(((Parcelle) tuile).getId() + "");
        } else {
            setText("0");
        }
    }
}
