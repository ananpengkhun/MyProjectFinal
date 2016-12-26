package com.example.ananpengkhun.myprojectfinal.dao;

/**
 * Created by ananpengkhun on 12/26/16.
 */
public class TestValueDao {
    private static TestValueDao ourInstance;

    private String name;


    public static TestValueDao getInstance() {
        if(ourInstance == null){
            ourInstance = new TestValueDao();
        }
        return ourInstance;
    }

    private TestValueDao() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
