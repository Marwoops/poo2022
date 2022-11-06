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
    
    private Partie partie;

    public Frame() {
        partie = new PartieDeCarcassonne();
                
        JPanel plateau = new VuePlateau(800, 800, partie.getPlateau());
        plateau.setBounds(20, 20, 800, 800);

        setLayout(null);
        add(plateau);
   }
}
