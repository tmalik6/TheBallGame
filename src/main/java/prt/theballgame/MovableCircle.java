package prt.theballgame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author daniel
 */
public class MovableCircle extends Circle {

    private final Spawner SP = new Spawner();

    public double x = SP.getPlaceX();
    public double y = SP.getPlaceY();
    public double dx = SP.getXWay(), dy = SP.getYWay();

    MovableCircle(Color color) {
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(SP.radius);
        this.setFill(color);
        setDx(dx);
        setDy(dy);
    }

    public double getDx() {
        return dx;
    }

    public final void setDx(double newDx) {
        while (newDx < -10 || newDx > 10) {
            newDx = dx;
        }
        dx = newDx;
    }

    public double getDy() {
        return dy;
    }

    public final void setDy(double newDy) {
        while (newDy < -10 || newDy > 10) {
            newDy = dy;
        }
        dy = newDy;
    }

    public void moveBall(boolean bounce) {
        if (bounce) {
            dx = -dx; 
            dy = -dy;            
        } else {
            if (x < SP.radius || x > SP.WIDTH - SP.radius) {
                dx *= -1;
            }

            if (y < SP.radius || y > SP.HEIGHT - SP.radius) {
                dy *= -1;
            }
        }

        x += dx;
        y += dy;
        setCenterX(x);
        setCenterY(y);

    }
    
//    public void controllBall() 
//    {
//        
//    }
}
