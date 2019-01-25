import java.awt.*;

public class Branch implements XmasShape {
    int x[];
    int y[];
    int x_start;
    int y_start;
    double scale;



    public Branch(int x[], int y[], double scale, int x_start, int y_start){
        this.x=x;
        this.y=y;

        this.x_start=x_start;
        this.y_start=y_start;
        this.scale=scale;


    }


    @Override
    public void render(Graphics2D g2d) {
        GradientPaint grad = new GradientPaint(0,0,new Color(0,200,0),0,100, new Color(0,50,0));
        g2d.setPaint(grad);
        g2d.fillPolygon(x,y,x.length);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x_start, y_start);
        g2d.scale(scale,scale);
    }
}