package com.yicj.hello.generic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

//https://juejin.im/post/6881435547894546439
@Slf4j
public class GenericClassAndMethodTest {
    @Test
    public void test1(){
        MainTest<String> data = new MainTest<String>(){} ;
        Type type = data.getClass().getGenericSuperclass();
        ParameterizedType genType1 = (ParameterizedType) type ;
        log.info("====> {}", genType1);
    }

    @Test
    public void test2(){
        MainTest<String> data = new MainTest<String>(){} ;
        Type type = data.getClass().getGenericSuperclass();
        ParameterizedType genType1 = (ParameterizedType) type ;
        log.info("====> {}", genType1);
    }

    private static class MainTest<T>{
        private T param ;

        static <T> T printData(T t){
            System.out.println("t = " + t);
            return t ;
        }
    }



}
