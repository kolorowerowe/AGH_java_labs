import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BouncingBallsPanel extends JPanel {

    AnimationThread watek;
    Integer ballSize = 40; // powinna być parzysta

    public Color randomColor(){
        Random rand = new Random();

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, g, b);

        return randomColor;
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        Integer ret= 0;
        while (ret==0) {
            ret = r.nextInt((max - min) + 1) + min;
        }
        return ret;
    }

    static class Ball{
        int x;
        int y;
        double vx;
        double vy;
        Color color;
    }

    List<Ball> balls = new ArrayList<>();


    class AnimationThread extends Thread{
        public void run(){
            for(;;){
                for(Ball b:balls){

                    if ( (b.x > 700-ballSize && b.vx>0) || (b.x <= 0 && b.vx < 0))
                        b.vx = -b.vx;
                    if ( (b.y > 700-ballSize-65 && b.vy>0) || (b.y <= 0  && b.vy < 0))
                        b.vy = -b.vy;

                    b.x += b.vx;
                    b.y += b.vy;
                }

                for(Ball b1:balls){
                    for(Ball b2:balls) {

                        if (b1 != b2) {
                            Integer distanceX = Math.abs((b2.x + ballSize / 2) - (b1.x + ballSize / 2));
                            Integer distanceY = Math.abs((b2.y + ballSize / 2) - (b1.y + ballSize / 2));
                            Double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

                            //System.out.println("X: " + distanceX + ", Y: " + distanceY);
                            if (distance <= ballSize) {
                                System.out.println(distance + " COLLISION");

                                Integer collisionPointX = (b1.x + b2.x + ballSize)/2;
                                Integer collisionPointY = (b1.y + b2.y + ballSize)/2;

                                Double tempx = b1.vx;
                                Double tempy = b1.vy;

                                b1.vx = b2.vx;
                                b1.vy = b2.vy;

                                b2.vx = tempx;
                                b2.vy = tempy;

                                b1.x += b1.vx;
                                b1.y += b1.vy;

                                b2.x += b2.vx;
                                b2.y += b2.vy;

                            }

                        }
                    }
                }



                repaint();
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public void paintComponent(Graphics g) {
        Graphics2D g2d=(Graphics2D)g;
        for (Ball b:balls){
            g2d.setColor(b.color);
            g2d.fillOval(b.x,b.y, ballSize ,ballSize);
        }
    }

    BouncingBallsPanel(){
        setOpaque(false);
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
        watek = new AnimationThread();


    }

    void onStart(){
        System.out.println("Start or resume animation thread");
        if (watek.isAlive()) {
            watek.resume();
        }
        else {
            watek.start();
        }
    }


    void onStop(){
        System.out.println("Suspend animation thread");
        watek.suspend();
    }

    void onPlus(){
        Ball newBall = new Ball();
        newBall.x = getRandomNumberInRange(2*ballSize,700-2*ballSize);
        newBall.x = getRandomNumberInRange(12*ballSize,700-2*ballSize);
        newBall.vx = getRandomNumberInRange(-3,3);
        newBall.vy = getRandomNumberInRange(-3,3);
        newBall.color=randomColor();
        balls.add(newBall);
        System.out.println("Add a ball");
    }

    void onMinus(){
        if(balls.size()>0){
            balls.remove(0);
            System.out.println("Remove a ball");
        }
        else{
            System.out.println("There is no ball to remove");
        }
    }
}