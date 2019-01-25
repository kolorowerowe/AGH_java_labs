import java.awt.*;
import java.util.Random;

public class Bubble implements XmasShape {
    int x;
    int y;
    double scale;
    Color fillColor;



    public Color random(){
        Random rand = new Random();

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, g, b);

        return randomColor;
    }

    public Bubble(int x, int y, double scale){
        this.x=x;
        this.y=y;
        this.scale=scale;
        this.fillColor=random();
    }


    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(fillColor);
        g2d.fillOval(x,y,100,100);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}