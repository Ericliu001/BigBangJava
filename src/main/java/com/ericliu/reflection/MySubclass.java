package com.ericliu.reflection;

/**
 * Created by ericliu on 18/04/2016.
 */
public class MySubclass  extends  MySuperclass{

    private void doSomething(){

        System.out.println("print from " + this.getClass().getSimpleName());
    }
}
