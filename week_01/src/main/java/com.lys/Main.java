package com.lys;

import java.lang.reflect.Method;

public class Main {


    public static void main(String[] arg) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader("Hello.xlass");
        Class<?> c = myClassLoader.findClass("Hello");
        Method helloMethod = c.getDeclaredMethod("hello");
        helloMethod.invoke(c.newInstance());

    }
}
