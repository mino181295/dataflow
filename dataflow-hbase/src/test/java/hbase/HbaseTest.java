package hbase;

import java.io.IOException;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Matteo Minardi
 */
public class HbaseTest extends HbaseAbstractTest {
    
    @Test
    public void createAndDeleteTableTest() throws IOException {
        HTableDescriptor table = new HTableDescriptor(TableName.valueOf("TableTest"));
        table.addFamily(new HColumnDescriptor("ColumnTest"));
        
        assertFalse(manager.tableExists("TableTest"));
        manager.createTable(table);
        assertTrue(manager.tableExists("TableTest"));
        manager.deleteTable(table.getNameAsString());
        assertFalse(manager.tableExists("TableTest"));

    }

}
