import java.awt.*;
import java.util.Random;

public class Background implements XmasShape {
    int x[];
    int y[];

    public Background(int x, int y){
        this.x = new int[4];
        this.y = new int[4];

        this.x[0]=0;
        this.x[1]=0;
        this.x[2]=x;
        this.x[3]=x;

        this.y[0]=0;
        this.y[1]=y;
        this.y[2]=y;
        this.y[3]=0;


    }


    @Override
    public void render(Graphics2D g2d) {


        GradientPaint grad = new GradientPaint(0,0,new Color(0,0,100),0,y[1], new Color(0,0,10));
        g2d.setPaint(grad);

        g2d.fillPolygon(x,y,x.length);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.scale(1,1);
    }

}