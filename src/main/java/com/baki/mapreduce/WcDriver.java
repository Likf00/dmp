package com.baki.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author yimting
 * @version 1.0.0
 * @ClassName WcDriver.java
 * @Description MapReduce的驱动程序，进行启动，以及分配任务
 * @createTime 2019年04月30日 09:28:00
 */
public class WcDriver {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        Job job ;
        boolean result = true;
        try {
            job = Job.getInstance(conf);

        job.setJarByClass(WcDriver.class);

        job.setMapperClass(WcMapper.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setReducerClass(WcReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setPartitionerClass(RePartition.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        result = job.waitForCompletion(true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e ){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.exit(result?0:1);

    }
}
