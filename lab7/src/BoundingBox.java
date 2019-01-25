import java.awt.*;
import java.io.IOException;

public class BoundingBox {
    double xmin = -1;
    double ymin = -1;
    double xmax = -1;
    double ymax = -1;

    public BoundingBox(double x1, double y1, double x3, double y3){
        addPoint(x1,y1);
        addPoint(x3,y3);
    }

    void addPoint(double x, double y){
        if(xmin==-1){
            xmin=x;
            xmax=x;
            ymax=y;
            ymin=y;
        }
        else {
            if(x<xmin)
                xmin=x;
            if(x>xmax)
                xmax=x;
            if(y<ymin)
                ymin=y;
            if(y>ymax)
                ymax=y;
        }

    }

    boolean contains(double x, double y){
        if (x>=xmin && x<=xmax && y>=ymin && y<=ymax)
            return true;
        return false;
    }

    boolean contains(BoundingBox bb){
        if (contains(bb.xmin, bb.ymin) && contains(bb.xmax,bb.ymax))
            return true;
        return false;
    }


    boolean intersects(BoundingBox bb){
        if (contains(bb))
                return true; //jezeli jesedn jest w drugim to się przecinają
        if (bb.xmax<xmin || bb.xmin>xmax || bb.ymax<ymin || bb.ymin>ymax) // jezeli jeden znajduje sie obok drugiego to się nie przecianją
            return false;
        return true; //zwykle przecinanie
    }

    BoundingBox add(BoundingBox bb){
        addPoint(bb.xmin,bb.ymin);
        addPoint(bb.xmax,bb.ymax);
        return this;
    }

    boolean isEmpty(){
        if (xmin==-1)
            return true;
        return false;
    }

    double getCenterX(){
        if (isEmpty())
            return Double.MAX_VALUE;
        return (xmin+xmax)/2;
    }

    double getCenterY(){
        if (isEmpty())
            return Double.MAX_VALUE;
        return (ymin+ymax)/2;    }

    double distanceTo(BoundingBox bbx){
        if (this.isEmpty() || bbx.isEmpty()){
            return Double.MAX_VALUE;
        }
        else {
            double R = 6372.8;
            double lat1=bbx.getCenterY();
            double lon1=bbx.getCenterX();
            double lat2=this.getCenterY();
            double lon2=this.getCenterX();

            double dLat = Math.toRadians(lat2 - lat1);
            double dLon = Math.toRadians(lon2 - lon1);
            lat1 = Math.toRadians(lat1);
            lat2 = Math.toRadians(lat2);

            double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
            double c = 2 * Math.asin(Math.sqrt(a));
            return R * c;
        }
    }

    public String toString(){
        StringBuilder b =  new StringBuilder();
        b.append("Środek: " + getCenterX() + ", " + getCenterY() + "\n");
        return b.toString();
    }

    public static void main(String args[]) throws IOException {
        BoundingBox b = new BoundingBox(21.5524730925644, 50.0014594746933, 21.672940945089, 50.0966280829525);
        BoundingBox b2 = new BoundingBox(19.7922353426102, 49.9676667885393,20.2173454438402,50.1261382575516);

        System.out.print(b.distanceTo(b2));


    }

}