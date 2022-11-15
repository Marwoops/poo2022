import java.awt.*;
import javax.swing.*;

public class App {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new Frame();
            frame.setTitle("POO 2022");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class Frame extends JFrame {
    
    public Frame() {
        
		VuePartie partie = new VuePartie(new PartieDeCarcassonne());
		partie.setBounds(0,0, 1920,1080);
		add(partie);
		setLayout(null);
              
   }
}
