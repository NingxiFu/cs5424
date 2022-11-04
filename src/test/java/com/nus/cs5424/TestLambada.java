///**
// * @(#)TestLambada.java, Nov 04, 2022.
// *
// * Copyright 2022 fenbi.com. All rights reserved.
// * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */package com.nus.cs5424;
//
//import org.junit.Test;
//
//import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
///**
// * @author guochenghui
// *
// *
//**/
//public class TestLambada {
//
//    @Test
//    public void filer() {
//        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
//
//        System.out.println("Languages which starts with J :");
//        List<String> j = languages.stream().filter(s -> s.startsWith("J")).collect(Collectors.toList());
//
//        j.forEach(System.out::println);
//    }
//
//    @Test
//    public void inTest(){
//        Set<Integer> set = new HashSet<>();
//        set.add(1);
//        set.add(2);
//        set.add(3);
//
//        String replace = set.toString().replace('[', '(').replace(']', ')');
//        System.out.println(replace);
//    }
//
//    @Test
//    public void range(){
//        List<Integer> range = IntStream.range(1, 501).boxed().collect(Collectors.toList());
//        range.forEach(System.out::println);
//    }
//
//}