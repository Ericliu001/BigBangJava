package com.ericliu.general;

/**
 * Created by ericliu on 12/03/2016.
 */
public class SomeClass {

    private String mName;


    public static SomeClass newInstance(){
        return new SomeClass("haha");
    }

    public SomeClass(String name) {
        mName = name;
    }

    void doSomeStuff(){
        MyOutterClass outterClass = new MyOutterClass();
        MyOutterClass.MyInnerClass innerClass = outterClass.new MyInnerClass();
    }
}
