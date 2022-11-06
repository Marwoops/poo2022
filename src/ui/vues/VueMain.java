import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class VueMain extends JPanel {

    private Joueur joueur;

    public VueMain(Joueur j) {
        joueur = j;
        
        setLayout(null);

        LinkedList<Tuile> main = j.getMain();

        for (int i = 0; i < main.size(); i++) {
            JLabel t = new VueTuile(main.get(i));
            t.setBounds(0, 0, 80, 80);
            add(t);
        }
 
    }

}
