package com.baki.learn;

/**
 * @author yimting
 * @version 1.0.0
 * @ClassName Singleton.java
 * @Description 内部类实现单例模式
 * @createTime 2019年05月03日 09:07:00
 */
public class Singleton {







/*    private Singleton(){
    }

    private static Singleton singleton ;

    public static Singleton getInstance() {
        if (null == singleton) {
            synchronized (Singleton.class){
                singleton = new Singleton();
            }
        }
        return singleton;
    }*/



    /**
     * 1. 构造方法

    private Singleton(){
    }

     * 2. 实现单例的模式的静态内部类

    private static class NewSingleton{
        private static final Singleton INSTANCE = new Singleton();
    }
    /**
     * 3. 获取单例的方法
     * @return
     * 内部类 NewSingleton 的静态变量 INSTANCE，这个变量是我们需要的那个单例，即外部类 NewSingleton 的对象，就是那个我们需要的唯一的对象。
     *
     * 当我们调用 Singleton.getInstance() 时，内部类 NewSingleton 才会初始化，静态变量 INSTANCE 被创建出来。
     *

    public static Singleton getInstance(){
        return  NewSingleton.INSTANCE;
    }
    private static final Singleton newSingleton = new Singleton();

    private Singleton(){

    }
    public static Singleton getNewSingleton() {
        return newSingleton;
    }
 */


}

class Test{
    public static void main(String[] args) {


    }
}
