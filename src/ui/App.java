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
        
		//VuePartie partie = new VuePartie(new PartieDeCarcassonne());
		VuePartie partie = new VuePartie(new PartieDeDomino());
		partie.setBounds(0,0, 1920,1080);
		add(partie);
		setLayout(null);
              
   }



}
