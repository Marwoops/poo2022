import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class App {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new Frame();
            frame.setTitle("POO 2022");
			frame.setSize(1000, 1000);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class Frame extends JFrame {
	private JPanel container;

    public Frame() {
        
		container = new JPanel(new GridBagLayout());
		JPanel menu = new JPanel();
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		JButton carca = new JButton("Carcassonne");
		JButton domino = new JButton("Domino carré");

		carca.addActionListener((ActionEvent e) -> {
			selectionNbJoueurs(true);
		});

		domino.addActionListener((ActionEvent e) -> {
			selectionNbJoueurs(false);
		});

		menu.add(carca);
		menu.add(Box.createVerticalStrut(50));
		menu.add(domino);
		container.add(menu);
		add(container);
		pack();
   }

   public void selectionNbJoueurs(boolean estCarcassonne) {
		container.removeAll();
		container.add(new JLabel("Nombre de joueurs : "));
		for (int i = 2; i <= 4; i++) {
			JButton button = new JButton(i + "");
			button.addActionListener((ActionEvent e) -> {
				selectionNbIA(estCarcassonne, Integer.parseInt(button.getText()));
			});
			container.add(button);
		}
		validate();
		repaint();
   }

   public void selectionNbIA(boolean estCarcassonne, int nbJoueurs) {
		container.removeAll();
		container.add(new JLabel("Nombre d'IA : "));
		for (int i = 0; i <= nbJoueurs; i++) {
			JButton button = new JButton(i + "");
			button.addActionListener((ActionEvent e) -> {
				getContentPane().removeAll();
				add((estCarcassonne) ?
						new VuePartie(new PartieDeCarcassonne(nbJoueurs, Integer.parseInt(button.getText())))
						: new VuePartie(new PartieDeDomino(nbJoueurs, Integer.parseInt(button.getText()))));
				validate();
				repaint();
			});
			container.add(button);
		}
		validate();
		repaint();
   }



}
