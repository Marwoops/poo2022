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
    
    public Frame() {
        
		JPanel container = new JPanel(new GridBagLayout());
		JPanel menu = new JPanel();
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		JButton carca = new JButton("Carcassonne");
		JButton domino = new JButton("Domino carré");

		carca.addActionListener((ActionEvent e) -> {
			getContentPane().removeAll();
			add(new VuePartie(new PartieDeCarcassonne(2, 0)));
			validate();
		});

		domino.addActionListener((ActionEvent e) -> {
			getContentPane().removeAll();
			add(new VuePartie(new PartieDeDomino(2, 0)));
			validate();
		});

		menu.add(carca);
		menu.add(Box.createVerticalStrut(50));
		menu.add(domino);
		container.add(menu);
		add(container);
		pack();
   }



}
