package com.baki.hbase.code;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author yimting
 * @version 1.0.0
 * @ClassName HbaseDDL.java
 * @Description Hbase的相关API操作
 * @createTime 2019年05月01日 11:15:00
 */
public class HbaseDDL {
    Connection connection;

    /**
     * 首先需要获取连接，以及一些配置信息
     */
    @Before
    public void init() {
        // 1. 读取 hbase-site.xml , 自动获得到 Hbase 的配置
        final Configuration conf = HBaseConfiguration.create();
        // 1.1 获取到 hbase 被 zookeeper 维护的地址信息
        conf.set("hbase.zookeeper.quorum", "192.168.168.14:2181");
        // 2. 创建 Hbase的 连接工厂 ， ConnectionFactory
        try {
            connection = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    /**
     * 创建表的API
     */
    Admin admin;

    @Test
    public void CreateTable() {
        try {
            admin = connection.getAdmin();
            final HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf("test"));
            final HColumnDescriptor f1 = new HColumnDescriptor("f1");
            // 列族有很多设置可以去设置
            //版本数：默认最大三个 ， 最小一个
            //f1.setMaxVersions();
            hTableDescriptor.addFamily(f1);
            admin.createTable(hTableDescriptor);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public void Close() throws Exception {
        admin.close();
        connection.close();
    }

}
