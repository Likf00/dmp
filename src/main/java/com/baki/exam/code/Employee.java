package com.baki.exam.code;

/**
 * @author yimting
 * @version 1.0.0
 * @ClassName Employee.java
 * @Description 公司员工管理类
 * @createTime 2019年04月27日 17:29:00
 */
public class Employee {

    private String name;
    private String id;
    private String sex;
    private double wage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", sex='" + sex + '\'' +
                ", wage=" + wage +
                '}';
    }
}
