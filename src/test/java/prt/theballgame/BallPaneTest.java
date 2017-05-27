/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prt.theballgame;

import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import prt.theballgame.Spawners.MovableCircle;

/**
 *
 * @author daniel
 */
public class BallPaneTest {
    
    public BallPaneTest() {
    }
    
    private final BallPane BP = new BallPane();
    private final MovableCircle mv = new MovableCircle(Color.GREEN);
    
    @Test
    public void testdist2_1() {
        double result = mv.dy;
        BP.controllBall(1, mv);
        mv.controllBall(1);
        double expResult = -1*(mv.dy);
        assertEquals(expResult, result,0.0);
    }
    @Test
    public void testdist2_2() {
        double result = mv.dy;
        BP.controllBall(2, mv);
        mv.controllBall(2);
        double expResult = mv.dy;
        assertEquals(expResult, result,0.0);
    }
    @Test
    public void testdist2_3() {
        double result = mv.dx;
        BP.controllBall(3, mv);
        mv.controllBall(3);
        double expResult = -1*(mv.dx);
        assertEquals(expResult, result,0.0);
    }
    @Test
    public void testdist2_4() {
        double result = mv.dx;
        BP.controllBall(4, mv);
        mv.controllBall(4);
        double expResult = mv.dx;
        assertEquals(expResult, result,0.0);
    }
}
