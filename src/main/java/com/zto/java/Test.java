package com.zto.java;

/**
 * @author yimting
 * @version 1.0.0
 * @ClassName Test.java
 * @Description test
 * @createTime 2019年04月27日 11:18:00
 */
public class Test {
    @Description(desc = "",author = "")
    public static void main(String[] args) {
        Person person = new Person();
        person.name = "xioming ";
        System.out.println(person);
    }
}
