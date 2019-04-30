package com.baki.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author yimting
 * @version 1.0.0
 * @ClassName MyMapper.java
 * @Description 对于文件进行预处理，进行输出
 * @createTime 2019年04月30日 09:14:00
 */
public class WcMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private Text k = new Text();
    private IntWritable v = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split(" ");
        for (String word : words) {
            k.set(word);
            context.write(k, v);
        }
    }
}
