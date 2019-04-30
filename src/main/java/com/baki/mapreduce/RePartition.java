package com.baki.mapreduce;

import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author yimting
 * @version 1.0.0
 * @ClassName RePartition.java
 * @Description 自定义的分区类
 * @createTime 2019年04月30日 19:05:00
 */
public class RePartition<K,V> extends Partitioner<K,V> {

    @Override
    public int getPartition(K key, V value, int numPartitions) {
        return ((key.hashCode()+value.hashCode())&Integer.MAX_VALUE % numPartitions);
    }
}
