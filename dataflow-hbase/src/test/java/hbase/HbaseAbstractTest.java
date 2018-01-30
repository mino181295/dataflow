/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbase;

import hbase.manager.HbaseManager;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;

/**
 *
 * @author Matteo Minardi
 */
public class HbaseAbstractTest {

    HbaseManager manager;

    @Before
    public void initAll() throws IOException {
        
        manager = new HbaseManager();
        manager.connect();
        
        System.out.println("HBase server version : " + manager.getHbaseVersion());

        
        createTestTables();
    }

    @After
    public void disposeAll() throws IOException {
        disposeTestTables();
        
        manager.close();
    }
    
    private void createTestTables(){}
    private void disposeTestTables(){}

}
