import java.awt.*;
import javax.swing.*;

public class VuePartie extends JComponent {
	private Partie partie;
	
	public VuePartie(Partie p) {
		partie = p;

        JPanel plateau = new VuePlateau(800, 800, partie.getPlateau());
        plateau.setBounds(20, 20, 800, 800);
        add(plateau);
        
        JPanel main1 = new VueMain(partie.getJoueur1());
        main1.setBounds(20, 850, 800, 80);
        add(main1);

		setLayout(null);
	}

}
