package prt.theballgame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author daniel
 */
public class MovableCircle extends Circle{

   private final Spawner SP = new Spawner();
   
   private double x = SP.getPlaceX();
   private double y = SP.getPlaceY();
   private double dx = SP.getXWay(), dy = SP.getYWay();

   MovableCircle(Color color){
       this.setCenterX(x);
       this.setCenterY(y);
       this.setRadius(SP.radius);
       this.setFill(color);
       setDx(dx);
       setDy(dy);
   } 

    public double getDx() { return dx; }

    public final void setDx(double newDx) {
        while (newDx < -10 || newDx > 10) {
            newDx = dx;
        } 
        dx = newDx;  
    }

    public double getDy() { return dy; }

    public final void setDy(double newDy) {
       while(newDy < -10 || newDy > 10) {
            newDy = dy;
        } 
        dy = newDy;  
    }

    public void moveBall() {
        // Check boundaries
        if (x < SP.radius || x > SP.WIDTH - SP.radius) {
            dx *= -1; // Change ball move direction
        }
        if (y < SP.radius || y > SP.HEIGHT - SP.radius) {
            dy *= -1; // Change ball move direction   
        }
        // Adjust ball position
        x += dx;
        y += dy;
        setCenterX(x);
        setCenterY(y);
    }

}

