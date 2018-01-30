/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbase.manager;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.protobuf.generated.HBaseProtos;
import org.apache.hadoop.hbase.util.VersionInfo;

/**
 * @author Matteo Minardi
 */
//        System.out.println("Hbase is on..");
//
//        TableName firstTable = TableName.valueOf("person");
//        String firstFamily = "personal data";
//        String secondFamily = "profession data";
//
//        HTableDescriptor descriptor = new HTableDescriptor(firstTable);
//        descriptor.addFamily(new HColumnDescriptor(firstFamily));
//        descriptor.addFamily(new HColumnDescriptor(secondFamily));
//        System.out.println("Table Descriptor created..");
//
//        Connection connection = ConnectionFactory.createConnection(conf);
//        Admin admin = connection.getAdmin();
//        System.out.println("Admin retrived..");
//
//        admin.createTable(descriptor);
//        System.out.println("Table created..");
//
//        Table currentTable = (HTable) connection.getTable(firstTable);
//        System.out.println("Table " + currentTable.getName() + " retrived back..");
//
//        Put put = new Put(Bytes.toBytes("row-1"));
//        put.addImmutable(Bytes.toBytes(firstFamily), Bytes.toBytes("name"), Bytes.toBytes("matteo"));
//        currentTable.put(put);
//        System.out.println("Putted to " + currentTable.getName() + " a row.");
//
//        Get get = new Get(Bytes.toBytes("row-1"));
//        Result res = currentTable.get(get);
//        String name = Bytes.toString(res.getValue(Bytes.toBytes(firstFamily), Bytes.toBytes("name")));
//        System.out.println("Get row, result is " + name + " .");
//
//        Thread.sleep(10000);
//        admin.disableTable(firstTable);
//        admin.deleteTable(firstTable);
//        System.out.println("Table deleted. Bye.");
public class HbaseManager implements AutoCloseable, HbaseHelper {

    private static final String DEFAULT_CONF_PATH = "/hbase/resources/hbase-site.xml";

    private Configuration conf;
    
    private Connection conn;
    private Admin admin;

    public HbaseManager(Configuration conf) {
        if (conf == null) {
            throw new IllegalArgumentException("Wrong config object");
        }
        this.conf = conf;
    }

    public HbaseManager() {
        this.conf = ConfigurationFactory.getConfigFromEnv();
        if (this.conf == null) {
            this.conf = ConfigurationFactory.getConfigFromPath(DEFAULT_CONF_PATH);
        }
    }

    public void connect() throws IOException {
        this.conn = ConnectionFactory.createConnection(this.conf);
        this.admin = this.conn.getAdmin();
    }

    public Admin getAdmin() {
        return this.admin;
    }
    
    public Connection getConnection() {
        return this.conn;
    }
    
    public String getHbaseVersion() {
        return VersionInfo.getVersion();
    }

    @Override
    public void close() throws IOException {
        if (this.conn != null) {
            this.conn.close();
        }
    }

    @Override
    public void createTable(HTableDescriptor table) throws IOException {
        this.admin.createTable(table);
    }

    @Override
    public void createOrOverwriteTable(HTableDescriptor table) throws IOException {
        if (this.tableExists(table.getNameAsString())) {
            this.deleteTable(table.getNameAsString());
        }
        this.createTable(table);
    }

    @Override
    public void deleteTable(String name) throws IOException {
        this.admin.disableTable(TableName.valueOf(name));
        this.admin.deleteTable(TableName.valueOf(name));
    }

    @Override
    public boolean tableExists(String tableName) throws IOException {
        return this.admin.tableExists(TableName.valueOf(tableName));
    }

    public static class ConfigurationFactory {

        public static Configuration getConfigFromPath(String path) {
            Configuration conf = HBaseConfiguration.create();
            conf.addResource(new Path(path));
            return conf;
        }

        public static Configuration getConfigFromEnv() {
            Configuration conf = HBaseConfiguration.create();
            
            String path = System.getProperty("HBASE_CONF_DIR");
            if (path == null) {
                return null;
            } 
            
            conf.addResource(new Path(path, "hbase-site.xml"));
            return conf;
        }

    }

}
