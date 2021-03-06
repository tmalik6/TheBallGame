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

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import prt.theballgame.BallSettings;

/**
 * A mozgatást végző osztály.
 * 
 */
public class MovableCircle extends Circle {

    private final Spawner SP = new Spawner();
    public  double x = Spawner.getPlaceX();
    public  double y = Spawner.getPlaceY();
    public  double dx = SP.getXWay(), dy = SP.getYWay();
    private final int radius = BallSettings.getRadius();
    /**
     *
     * Konstruktor, mely létrehoz egy mozgatható kör-t reprezentáló objektumot.
     * @param color milyen szinű legyen a kör
     *
     */
    public MovableCircle(Color color) {
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(radius);
        this.setFill(color);
        CheckDx(dx);
        CheckDy(dy);
    }
    
    public MovableCircle(Color color, int i) {
        this.setCenterX(i);
        this.setCenterY(i);
        this.setRadius(radius);
        this.setFill(color);
        CheckDx(dx);
        CheckDy(dy);
    }
    
    @SuppressWarnings("checkstyle:javadocmethod")
    private void CheckDx(double newDx) {
        while (newDx < -10 || newDx > 10) {
            newDx = dx;
        }
        dx = newDx;
    }

    @SuppressWarnings("checkstyle:javadocmethod")
    private void CheckDy(double newDy) {
        while (newDy < -10 || newDy > 10) {
            newDy = dy;
        }
        dy = newDy;
    }

    /**
     * Beállítja a mozgó kör új koordinátáit és vektorait.
     *
     * @param bounce honnan pattant a labda
     *            {@code true} abban az esetben, ha másik lapdáról
     *            {@code false} abban az esetben, ha falról
     *  
     */
    public void moveBall(boolean bounce) {
        if (bounce) {
            dx = -dx;
            dy = -dy;
        } else {
            if (x < radius || x > Spawner.WIDTH - radius) {
                dx *= -1;
            }

            if (y < radius || y > Spawner.HEIGHT - radius) {
                dy *= -1;
            }
        }

        x += dx;
        y += dy;
        setCenterX(x);
        setCenterY(y);

    }
    
    /**
     * Beállítja a játékos körének új koordinátáit és vektorait.
     *
     * @param sign melyik irányba kell mozdulni a körnek
     *            {@code 1,2} abban az esetben, ha függőlegesen változtason irányt
     *            {@code 3,4} abban az esetben, ha vízszintesen változtason irányt
     */
    public void controllBall(int sign) {
        switch (sign) {
            case 1:
                dy = -dy;
            case 2:
                dy = -dy;
            case 3:
                dx = -dx;
            case 4:
                dx = -dx;
        }
    }
}
