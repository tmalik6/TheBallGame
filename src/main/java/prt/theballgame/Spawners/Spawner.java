package prt.theballgame.Spawners;
//CHECKSTYLE:ON
/*
 * #%L
 * TheBallGame
 * %%
 * Copyright (C) 2016 Debreceni Egyetem, Informatikai Kar
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import java.util.Random;
import org.slf4j.LoggerFactory;
import prt.theballgame.BallSettings;

/**
 * A kör alapadait reprezentáló osztály.
 * 
 */
public class Spawner {

    private static double placeX;
    private static double placeY;
    private static int speed;
    private static double XWay;
    private static double YWay;
    Random rand = new Random();

    public static int WIDTH = BallSettings.getWidth();
    public static int HEIGHT = BallSettings.getHeight();
    public static int radius = BallSettings.getRadius();
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Spawner.class); 
    
    /**
     * 
     * Konstruktor, mely létrehoz egy álló kör alapadait reprezentáló objektumot.
     * 
     */
    public Spawner() {
        int angle0 = random(30, 70);
        int quadrant = random(3);
        int angle = angle0 + quadrant * 90;
        XWay = Math.cos(angle * Math.PI / 180);
        YWay = Math.sin(angle * Math.PI / 180);
        placeX = random(radius + 1, WIDTH - radius - 1);
        placeY = random(50 + radius + 1, HEIGHT - radius - 1);
        //logger.info(XWay + "");
        //logger.info(YWay + "");
    }

    /**
     * Visszad egy x vektort.
     *
     * @return az x vektor
     */
    public double getXWay() {
        return XWay;
    }

    /**
     * Visszad egy y vektort.
     *
     * @return az y vektor
     */
    public double getYWay() {
        return YWay;
    }

    /**
     * Visszad egy x koordinátát.
     *
     * @return az x koordináta
     */
    public static double getPlaceX() {
        return placeX;
    }

    /**
     * Visszad egy y koordinátát.
     *
     * @return az y koordináta
     */
    public static double getPlaceY() {
        return placeY;
    }

    @SuppressWarnings("checkstyle:javadocmethod")
    private int random(int low, int high) {
        if (low > high) {
            return high;
        }
        int x = rand.nextInt(Integer.MAX_VALUE);
        int ret = low + (x % (high - low + 1));
        return ret;
    }

    @SuppressWarnings("checkstyle:javadocmethod")
    private int random(int high) {
        return rand.nextInt(high);
    }

     public static void checker(){
        if(WIDTH != BallSettings.getWidth() || HEIGHT != BallSettings.getHeight() ){
        WIDTH = BallSettings.getWidth();
        HEIGHT = BallSettings.getHeight();
        }
    }
     
    @SuppressWarnings("checkstyle:javadocmethod")
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
