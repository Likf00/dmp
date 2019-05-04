#!/bin/bash
echo "*********************正在开启集群服务****************************"
echo "*********************正在开启namenode节点************************"

ssh dy@localhost '/opt/app/hadoop/sbin/hadoop-daemon.sh start namenode'
echo "*********************正在开启datanode节点************************"
#for i in admin@hadoop-senior01.atguigu.com admin@hadoop-senior02.atguigu.com admin@hadoop-senior03.atguigu.com
#do
        #ssh $i '/opt/modules/hadoop-2.5.0-cdh5.3.6/sbin/hadoop-daemon.sh start datanode'
        ssh dy@localhost '/opt/app/hadoop/sbin/hadoop-daemon.sh start datanode'

#done

echo "*********************正在开启secondarynamenode节点************************"

ssh dy@localhost '/opt/app/hadoop/sbin/hadoop-daemon.sh start secondarynamenode'

echo "*********************正在开启ResourceManager节点************************"

ssh dy@localhost '/opt/app/hadoop/sbin/hadoop-daemon.sh start ResourceManager'

echo "*********************正在开启nodeManager节点************************"

#for i in admin@hadoop-senior01.atguigu.com admin@hadoop-senior02.atguigu.com admin@hadoop-senior03.atguigu.com
#do
        #ssh $i '/opt/modules/hadoop-2.5.0-cdh5.3.6/sbin/yarn-daemon.sh start datamanager'
        ssh dy@localhost '/opt/app/hadoop/sbin/hadoop-daemon.sh start nodemanager'
#done

echo "*********************正在开启jobhistoryServer节点************************"

ssh dy@localhost '/opt/app/hadoop/sbin/mr-jobhistory-daemon.sh start historyserver'

echo "*********************正在开启Spark-Master节点************************"

echo "*********************正在开启Spark-Worker节点************************"
ssh dy@localhost '/opt/app/spark/sbin/start-all.sh'

echo "*********************正在开启Hbase-Master节点************************"

echo "*********************正在开启Hbase-Worker节点************************"

ssh dy@localhost '/opt/app/hbase/bin/start-all.sh'