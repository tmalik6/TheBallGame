package prt.theballgame;
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

/**
 * A mozgatást végző osztály.
 * 
 */
public class MovableCircle extends Circle {

    private final Spawner SP = new Spawner();

    public double x = SP.getPlaceX();
    public double y = SP.getPlaceY();
    public double dx = SP.getXWay(), dy = SP.getYWay();

    /**
     *
     * Konstruktor, mely létrehoz egy mozgatható kör-t reprezentáló objektumot.
     * @param color milyen szinű legyen a kör
     *
     */
    MovableCircle(Color color) {
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(SP.radius);
        this.setFill(color);
        CheckDx(dx);
        CheckDy(dy);
    }

    @SuppressWarnings("checkstyle:javadocmethod")
    private final void CheckDx(double newDx) {
        while (newDx < -10 || newDx > 10) {
            newDx = dx;
        }
        dx = newDx;
    }

    @SuppressWarnings("checkstyle:javadocmethod")
    private final void CheckDy(double newDy) {
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
