import java.awt.*;
import java.util.Random;

public class Present implements XmasShape {
    int x;
    int y;
    double scaleX;
    double scaleY;
    Color fillColor;
    Color boxColor;



    public Color random(){
        Random rand = new Random();

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, g, b);

        return randomColor;
    }

    public Present(int x, int y, double scaleX, double scaleY){
        this.x=x;
        this.y=y;
        this.scaleX=scaleX;
        this.scaleY=scaleY;
        this.fillColor=random();
        this.boxColor=random();
    }


    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(boxColor);
        g2d.fillRect(0,0,100,100);
        g2d.setColor(fillColor);
        double wstX=5/scaleY;
        double wstY=5/scaleX;
        g2d.fillRect(0,45,100,(int)wstX);
        g2d.fillRect(49,0,(int)wstY,100);

    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scaleX,scaleY);
    }
}