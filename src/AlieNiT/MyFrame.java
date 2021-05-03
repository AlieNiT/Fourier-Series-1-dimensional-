package AlieNiT;

import java.awt.*;

import javax.swing.*;

public class MyFrame extends JFrame {

    MyPanel panel;

    MyFrame(int N) {

        panel = new MyPanel(N);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
