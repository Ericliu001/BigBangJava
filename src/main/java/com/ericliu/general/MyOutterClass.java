package com.ericliu.general;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ericliu on 6/01/2016.
 */
public class MyOutterClass {


    public String getPackageName() {
        return null;
    }



    public class MyInnerClass{


        void sayHello(){

            System.out.println("Hello, world.");
            List<String> l1 = new ArrayList<>();
            Set<Integer> s1 = new HashSet<>();
            merge(l1, s1);
        }
    }


    private void merge(Collection<? extends String> c1, Collection<? extends Integer> c2) {

    }
}
