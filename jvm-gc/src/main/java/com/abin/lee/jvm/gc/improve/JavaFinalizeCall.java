package com.abin.lee.jvm.gc.improve;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abin on 2018/4/21 23:21.
 * jvm-capability
 * com.abin.lee.jvm.gc.improve
 * http://jianshi-dlw.iteye.com/blog/1177146
 * -Xms30m -Xmx30m -Xmn10m -XX:+UseParallelGC
 */
public class JavaFinalizeCall {

    public static void main(String[] args) throws Exception {
        List<Order> caches = new ArrayList<Order>();
        for (int i = 0; i < 100000; i++) {
            caches.add(new Order(i, "lee"+i));
//            caches.add(new byte[1024 * 1024 * 3]);
        }
//        getJvmMemory();
        System.out.println("-----------------------add-------list ..... after");
        Thread.sleep(5000);
        System.out.println("-----------------------add-------list ..... after----sleep 5000 ");
//        caches.clear();
//        getJvmMemory();
        System.out.println("-----------------------add-------list----two  ..... before ");
        for (int i = 0; i < 2; i++) {
            caches.add(new Order(i, "lee"+i));
        }
        System.out.println("-----------------------add-------list----two  ..... after ");
//        getJvmMemory();
        Thread.sleep(1000000);
    }


    public static void getJvmMemory(){
        MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean();
        MemoryUsage usage = memorymbean.getHeapMemoryUsage();
        System.out.println("INIT HEAP: " + usage.getInit());
        System.out.println("MAX HEAP: " + usage.getMax());
        System.out.println("USE HEAP: " + usage.getUsed());
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class  Order {
        private int id;
        private String name;

        @Override
        protected void finalize() throws Throwable {
            System.out.println("-----------------------call this finalize-------------------------------call this finalize---------------");
            super.finalize();
        }
    }


}