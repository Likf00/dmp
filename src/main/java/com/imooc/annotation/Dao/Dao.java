package com.imooc.annotation.Dao;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author yimting
 * @version 1.0.0
 * @ClassName Dao.java
 * @Description 通过注解，去生成SQL语句的 最终效果
 * @createTime 2019年04月29日 10:09:00
 */
public class Dao {

    public static void main(String[] args) {
        Filter f1 = new Filter();
        //查询Id 为1 的用户
        f1.setId(20);
        f1.setUserName("baki");
        f1.setNickName("mysql");

        Filter f2 = new Filter();
        // 查询username 为User 的用户
        f2.setUserName("user");

        Filter f3 = new Filter();
        //按照逗号分割，进行查询任意一个邮箱的用户
        f3.setEmail("iodfjjf@162.com,djfi@qq.com,999999999@qq.com");

        String sql1=query(f1);
        String sql2 = query(f2);
        String sql3 = query(f3);

        System.out.println(sql1);
        System.out.println(sql2);
        System.out.println(sql3);

    }

    private static String query (Filter f){
        StringBuilder sb = new StringBuilder();
        //1.获取到Class
        Class c = f.getClass();
        //2.获取到table名字
        boolean annotationPresent = c.isAnnotationPresent(Table.class);
        if(!annotationPresent){
            return null;
        }
        //获得Table的名字
        Table table = (Table)c.getAnnotation(Table.class);
        String tableName = table.value();

        sb.append("select * from ").append(tableName).append(" where 1=1");
        //3. 遍历所有的字段
        Field[] declaredFields = c.getDeclaredFields();
        for(Field field : declaredFields){
            //处理每个字段对应的SQL
            //字段名
            boolean annotationPresent1 = field.isAnnotationPresent(Column.class);
            if(!annotationPresent1){
                continue;
            }
            Column annotation = field.getAnnotation(Column.class);
            String columnName = annotation.value();
            //字段值
            String fieldName = field.getName();
            String getMethodName = "get"+columnName.substring(0,1).toUpperCase()+fieldName.substring(1);
            Object fieldValue = null;
            try {
                Method getMethod = c.getMethod(getMethodName);
                fieldValue =  getMethod.invoke(f);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //拼装sql
            if(fieldValue==null||(fieldValue instanceof Integer && (Integer)fieldValue==0)){
                continue;
            }
            sb.append(" and ").append(columnName);
            if(fieldValue instanceof String ){
                if(((String) fieldValue).contains(",")){
                    String[] values = ((String) fieldValue).split(",");
                    sb.append(" in (");
                    for(String fields : values){
                        sb.append("'").append(fields).append("'").append(",");
                    }
                    sb.deleteCharAt(sb.length()-1).append(")");
                }else{
                    sb.append(" = ").append("'").append(fieldValue).append("'");
                }

            }else if(fieldValue instanceof Integer){
                sb.append(" = ").append(fieldValue);
            }
        }
        return sb.append(";").toString();
    }

}
