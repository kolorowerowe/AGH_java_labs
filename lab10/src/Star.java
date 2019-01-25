import java.awt.*;

public class Star implements XmasShape {
    int x;
    int y;
    int x_start;
    int y_start;
    double scale;
    double rotate;
    Color color;



    public Star(int x, int y, double scale,double rotate, Color color){
        this.x=x;
        this.y=y;
        this.scale=scale;
        this.rotate=rotate;
        this.color=color;


    }


    @Override
    public void render(Graphics2D g2d) {
        int radius[] = {118,40,90,40};
        int nPoints = 16;
        int[] X = new int[nPoints];
        int[] Y = new int[nPoints];

        for (double current=0.0; current<nPoints; current++)
        {
            int i = (int) current;
            double x = Math.cos(current*((2*Math.PI))/nPoints)*radius[i % 4];
            double y = Math.sin(current*((2*Math.PI))/nPoints)*radius[i % 4];

            X[i] = (int) x;
            Y[i] = (int) y;
        }
        g2d.setColor(color);
        g2d.fillPolygon(X, Y, nPoints);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scale,scale);
        g2d.rotate(rotate);
    }
}