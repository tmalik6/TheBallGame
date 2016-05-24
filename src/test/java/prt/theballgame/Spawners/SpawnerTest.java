/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prt.theballgame.Spawners;

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
public class SpawnerTest {
    
    Spawner instance = new Spawner();
       
    @Test
    public void testGetXWay() {   
        double expResult = 0.0;
        double result = instance.getXWay();
        assertNotEquals(expResult, result, 0.0);
    }

    
    @Test
    public void testGetYWay() {
        double expResult = 0.0;
        double result = instance.getYWay();
        assertNotEquals(expResult, result, 0.0);
    }

    
    @Test
    public void testGetPlaceX() {
        double expResult = Spawner.WIDTH-Spawner.WIDTH-1;
        double result = Spawner.getPlaceX();
        assertNotEquals(expResult, result, 0.0);
    }

    
    @Test
    public void testGetPlaceY() {
        double expResult = Spawner.HEIGHT-Spawner.HEIGHT-1;
        double result = Spawner.getPlaceY();
        assertNotEquals(expResult, result, 0.0);
    }
    
}
