/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prt.theballgame;

import javafx.animation.Timeline;
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
public class BallPaneTest {
    
    public BallPaneTest() {
    }
    
    @Test
    public void testInitdata() {
        BallPane instance = new BallPane();
        int expResult = 0;
        int result = instance.initdata();
        assertEquals(expResult, result);
    }
    
}
