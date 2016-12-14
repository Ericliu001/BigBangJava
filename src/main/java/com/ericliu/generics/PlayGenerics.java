package com.ericliu.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by ericliu on 12/12/16.
 */

public class PlayGenerics {


    private void playGenericClass() {
        GenericClass<?> genericClass = new GenericClass<>();
        /**
         *  cannot call modify method because it takes a generic parameter type,
         *  while the definition of genericClass uses a wildcard type
         *
         */
//        genericClass.modify("haha");


        genericClass.read();
    }


    private void merge(Set s1, Set s2) {
        s1.add("haha");

        List<Object> objectList = new ArrayList<>();
        objectList.add("haha");


    }


    private void mergeWildcard(Set<?> s1, Set<?> s2) {
        /**
         *   compile error, you can't put any element into a wildcard Collection
         */
//        s1.add("haha");
    }


    private <E> void mergeBoundedWildcard(Set<? extends E> s1, Set<? extends E> s2) {
    }
}
