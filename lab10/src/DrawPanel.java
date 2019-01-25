import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawPanel extends JPanel {




    public void paintComponent(Graphics g){


        List<XmasShape> shapes = new ArrayList<>();
        int[] x={-100,100,0};
        int[] y={100,100,0};
        int x_sr=500;
        setBackground(new Color(0,0,50));

        shapes.add(new Background(1000, 700));
        //wszystkie galezie
        shapes.add(new Branch( x ,y, 2, x_sr, 400));
        shapes.add(new Branch( x ,y, 1.6, x_sr, 350));
        shapes.add(new Branch( x ,y, 1.2, x_sr, 300));
        shapes.add(new Branch( x ,y, 0.8, x_sr, 270));
        shapes.add(new Branch( x ,y, 0.5, x_sr, 240));

        //bombki
        shapes.add(new Bubble(500,480,0.2 ));
        shapes.add(new Bubble(550,490,0.2 ));
        shapes.add(new Bubble(460,420,0.2 ));
        shapes.add(new Bubble(400,400,0.2 ));
        shapes.add(new Bubble(450,320,0.2 ));
        shapes.add(new Bubble(350,320,0.2 ));
        shapes.add(new Bubble(300,480,0.2 ));
        shapes.add(new Bubble(390,300,0.2 ));
        shapes.add(new Bubble(400,470,0.2 ));
        shapes.add(new Bubble(370,440,0.2 ));
        shapes.add(new Bubble(380,260,0.2 ));
        shapes.add(new Bubble(430,270,0.2 ));
        shapes.add(new Bubble(450,370,0.2 ));
        shapes.add(new Bubble(360,360,0.2 ));

        //gwiazda na zubku
        shapes.add(new Star(500,230,0.5,0,Color.yellow));
        //gwiazdki
        shapes.add(new Star(50,100,0.1,0.1,Color.white));
        shapes.add(new Star(100,320,0.1,0.2,Color.white));
        shapes.add(new Star(200,45,0.1,0.3,Color.white));
        shapes.add(new Star(350,140,0.1,0.4,Color.white));
        shapes.add(new Star(520,100,0.1,0.5,Color.white));
        shapes.add(new Star(650,170,0.1,0.6,Color.white));
        shapes.add(new Star(800,100,0.1,0.7,Color.white));
        shapes.add(new Star(850,280,0.1,0.8,Color.white));
        shapes.add(new Star(950,100,0.1,0.9,Color.white));

        //presenty
        shapes.add(new Present(350,620,1.2,0.4));
        shapes.add(new Present(10,410,2,2.5));
        shapes.add(new Present(750,540,2,1.2));
        shapes.add(new Present(770,440,1,1));

        super.paintComponent(g);

        for (XmasShape xs : shapes) {
            xs.draw((Graphics2D)g);
        }

        g.setColor(new Color(139,69,19));
        g.fillRect(480,600,40,60);
        g.setFont(new Font("Manjari", Font.BOLD, 38));
        g.setColor(Color.white);
        g.drawString("Wesołych świąt!", 330, 50);


        System.out.println("painting");

    }
}