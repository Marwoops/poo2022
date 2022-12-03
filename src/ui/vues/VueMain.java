import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class VueMain extends JPanel {

    private Joueur joueur;

    public VueMain(Joueur j, MouseListener controleur) {
        joueur = j;
        LinkedList<Tuile> main = j.getMain();

        for (int i = 0; i < main.size(); i++) {
            JLabel t = new VueTuile(main.get(i), -1, -1, true, controleur);
            add(t);
        }
    }
}
