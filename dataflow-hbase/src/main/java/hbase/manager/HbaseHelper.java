package hbase.manager;

import java.io.IOException;
import org.apache.hadoop.hbase.HTableDescriptor;

/**
 *
 * @author Matteo Minardi
 */
public interface HbaseHelper {
    
    boolean tableExists(String tableName) throws IOException;
    
    void createTable(HTableDescriptor table) throws IOException;
    
    void createOrOverwriteTable(HTableDescriptor table) throws IOException;
    
    void deleteTable(String tableName) throws IOException;
    
}
