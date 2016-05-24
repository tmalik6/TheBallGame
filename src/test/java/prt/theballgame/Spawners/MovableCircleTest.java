/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prt.theballgame.Spawners;

import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author daniel
 */
public class MovableCircleTest {
    
    private final Spawner SP = new Spawner();
    private final MovableCircle mv = new MovableCircle(Color.GREEN);
    
    
    @Test
    public void testMoveBall() {
        assertNotEquals(mv.dy*-1,mv.y+mv.dy*-1,0.0);
        assertNotEquals(mv.x, SP.radius, 0.0);
        assertNotEquals(mv.x, SP.HEIGHT, 0.0);
        
        assertNotEquals(mv.y, SP.radius, 0.0);
        assertNotEquals(mv.y, SP.HEIGHT, 0.0);
    }
    
    @Test
    public void testDx() {
        assertNotEquals(-10.0, mv.dx, 0.0);
        assertNotEquals(10.0, mv.dx, 0.0);
    }
    
    @Test
    public void testDy() {
        assertNotEquals(-10.0, mv.dy, 0.0);
        assertNotEquals(10.0, mv.dy, 0.0);
    }
   
    @Test
    public void testCoorinates() {
        assertNotEquals(mv.x, new MovableCircle(Color.GREEN).x);
        assertNotEquals(mv.y, new MovableCircle(Color.GREEN).y);
    }
}
