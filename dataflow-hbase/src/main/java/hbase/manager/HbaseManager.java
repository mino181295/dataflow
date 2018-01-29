/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbase.manager;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

/**
 *
 * @author Matteo Minardi
 */
public class HbaseManager {
    
    public HbaseManager() throws Exception {
        Configuration conf = HBaseConfiguration.create();

        String path = "/hbase/resources/hbase-site.xml";
        conf.addResource(new Path(path));
        System.out.println("Hbase is on..");

        TableName firstTable = TableName.valueOf("person");
        String firstFamily = "personal data";
        String secondFamily = "profession data";

        HTableDescriptor descriptor = new HTableDescriptor(firstTable);
        descriptor.addFamily(new HColumnDescriptor(firstFamily));
        descriptor.addFamily(new HColumnDescriptor(secondFamily));
        System.out.println("Table Descriptor created..");

        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        System.out.println("Admin retrived..");

        admin.createTable(descriptor);
        System.out.println("Table created..");

        Table currentTable = (HTable) connection.getTable(firstTable);
        System.out.println("Table " + currentTable.getName() + " retrived back..");

        Put put = new Put(Bytes.toBytes("row-1"));
        put.addImmutable(Bytes.toBytes(firstFamily), Bytes.toBytes("name"), Bytes.toBytes("matteo"));
        currentTable.put(put);
        System.out.println("Putted to " + currentTable.getName() + " a row.");

        Get get = new Get(Bytes.toBytes("row-1"));
        Result res = currentTable.get(get);
        String name = Bytes.toString(res.getValue(Bytes.toBytes(firstFamily), Bytes.toBytes("name")));
        System.out.println("Get row, result is " + name + " .");
        
        Thread.sleep(10000);
        admin.disableTable(firstTable);
        admin.deleteTable(firstTable);
        System.out.println("Table deleted. Bye.");
    }
    
}
