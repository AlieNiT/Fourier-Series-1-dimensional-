package AlieNiT;

import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class MyPanel extends JPanel implements ActionListener {
    final int MAX_POINTS = 400;
    final int PANEL_WIDTH = 1200;
    final int PANEL_HEIGHT = 600;
    Timer timer;

    int N;
    double[] Rs;
    double[] Ws;
    double[] As;
    ArrayList<Point>[] plots;

    MyPanel(int N) {
        Rs = new double[N];
        Ws = new double[N];
        As = new double[N];
        plots = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            plots[i] = new ArrayList<>();
        }
        this.N = N;
        for (int i = 0; i < N; i++) {
            Rs[i] = 600 / (Math.PI * (2 * i + 1));
            Ws[i] = (double) (2 * i + 1) / 50;
            As[i] = 0;
        }

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.black);
        timer = new Timer(10, this);
        timer.start();

    }

    private void addPoint(Point p, int i) {
        plots[i].add(0, p);
        if (plots[i].size() > 400)
            plots[i].remove(400);
    }

    public void paint(Graphics G) {
        super.paint(G); // paint background
        Graphics2D g2D = (Graphics2D) G;
        g2D.setPaint(Color.white);
        //g2D.setPaint(Color.);
        ArrayList<Color> colors = new ArrayList<>();
        for (int r = 0; r < 100; r++) colors.add(new Color((int) (r * (double) 255 / 100), 255, 0));
        for (int g = 100; g > 0; g--) colors.add(new Color(255, (int) (g * (double) 255 / 100), 0));
        for (int b = 0; b < 100; b++) colors.add(new Color(255, 0, (int) (b * (double) 255 / 100)));
        for (int r = 100; r > 0; r--) colors.add(new Color((int) (r * (double) 255 / 100), 0, 255));
        for (int g = 0; g < 100; g++) colors.add(new Color(0, (int) (g * (double) 255 / 100), 255));
        for (int b = 100; b > 0; b--) colors.add(new Color(0, 255, (int) (b * (double) 255 / 100)));
        colors.add(new Color(0, 255, 0));
        double Y = PANEL_HEIGHT / 2, X = 300;
        drawLine(g2D, 600, Y, 1200, Y);
        for (int i = 0; i < N; i++) {
            System.out.println("Radius: " + Rs[i] + " , Angle: " + As[i] + " Angular velocity: " + Ws[i]);
        }
        for (int i = 0; i < N; i++) {
            g2D.setPaint(colors.get((100 * i % colors.size())));

            drawCircle(g2D, X - Rs[i], Y - Rs[i], 2 * Rs[i]);
            drawLine(g2D, X, Y, X + Rs[i] * Math.cos(As[i]), Y + Rs[i] * Math.sin(As[i]));

            addPoint(new Point(Y), i);
            drawLine(g2D, X, Y, 600, Y);
            X += Rs[i] * Math.cos(As[i]);
            Y += Rs[i] * Math.sin(As[i]);
        }
        for (int j = 0; j < N; j++) {
            g2D.setPaint(colors.get((100 * j % colors.size())));
            for (int i = 0; i < plots[j].size() - 1; i++) {
                drawLine(g2D, 600 + i * 600.0 / MAX_POINTS, plots[j].get(i).y, 600 + (i + 1) * 600.0 / MAX_POINTS, plots[j].get(i + 1).y);
            }
        }
        drawLine(g2D, 600, 100, 600, 500);
//        drawLine(g2D,X,Y,600,Y);
    }

    void drawCircle(Graphics2D g, double X, double Y, double R) {
        g.drawOval((int) X, (int) Y, (int) R, (int) R);
    }

    void drawLine(Graphics2D g, double x1, double y1, double x2, double y2) {
        g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < N; i++)
            As[i] += Ws[i];
        repaint();
    }

}

class Point {
    double y;

    Point(double y) {
        this.y = y;
    }
}
