package com.ericliu.general;

/**
 * Created by ericliu on 12/03/2016.
 */
public class SomeClass {

    void doSomeStuff(){
        MyOutterClass outterClass = new MyOutterClass();
        MyOutterClass.MyInnerClass innerClass = outterClass.new MyInnerClass();
    }
}
