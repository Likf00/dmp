package com.baki.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author yimting
 * @version 1.0.0
 * @ClassName WcReducer.java
 * @Description 将预处理完的文件，进行统计聚合
 * @createTime 2019年04月30日 09:23:00
 */
public class WcReducer extends Reducer<Text, IntWritable,Text,IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0 ;
        for(IntWritable v : values){
            sum += v.get();
        }
        context.write(key,new IntWritable(sum));
    }
}
