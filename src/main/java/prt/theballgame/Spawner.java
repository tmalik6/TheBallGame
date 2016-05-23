package prt.theballgame;

import java.util.Random;

/**
 *
 * @author daniel
 */
public class Spawner {

    private static double placeX;
    private static double placeY;
    private static int speed;
    private static double XWay;
    private static double YWay;
    public final int radius = 20;
    Random rand = new Random();
    
    public static final int WIDTH  = 750;
    public static final int HEIGHT = 650;


    //Math.PI
    Spawner() {
        speed = rand.nextInt(3);
        int angle0 = random(3, 87);
        int quadrant = random(3);
        int angle = angle0 + quadrant * 90;
        XWay = Math.cos(angle * Math.PI / 180);
        YWay = Math.sin(angle * Math.PI / 180);
        placeX = random(radius+1, WIDTH-radius-1);
        placeY = random(50+radius+1, HEIGHT-radius-1);
    }
    
    public double getXWay() {
        return XWay;
    }

    public double getYWay() {
        return YWay;
    }
    
    public static double getPlaceX() {
        return placeX;
    }

    public static double getPlaceY() {
        return placeY;
    }

    public static int getSpeed() {
        return speed;
    }
    
    public int random(int low, int high) {
        if (low > high) {
            return high;
        }
        int x = rand.nextInt(Integer.MAX_VALUE);
        int ret = low + (x % (high - low + 1));
        return ret;
    }

    int random(int high) {
        return rand.nextInt(high);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("X-koordináta =");
        builder.append(placeX);
        builder.append("Y-koordináta =");
        builder.append(placeY);
        builder.append("Sebesség =");
        builder.append(speed);
        return builder.toString();
    }
}
