package com.abin.lee.jvm.gc.cms;

import com.abin.lee.jvm.common.util.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by abin on 2018/4/18 20:59.
 * jvm-capability
 * com.abin.lee.jvm.gc.cms
 */
@Slf4j
@Component
public class MemoryOverflowPerformance {
    public static Map<String, String> map = Maps.newConcurrentMap();
    public static List<Integer> list = Lists.newCopyOnWriteArrayList();

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000);
        int i = 0;
        while (true) {
            String str = new String(i + "");
            Thread.sleep(1);
            if (i++ % 1000 == 0) {
                map.put(i + "", str);
            }
        }
    }

    /**
     * -server -Xms30m -Xmx30m -XX:NewSize=20m -XX:MaxNewSize=20m -XX:PermSize=10m -XX:MaxPermSize=10m -XX:-UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection
     -XX:CMSInitiatingOccupancyFraction=80 -XX:+CMSParallelRemarkEnabled -XX:SoftRefLRUPolicyMSPerMB=0
     jstat -gcutil -h 5 8056 1000 1000
     * @param param
     * @throws InterruptedException
     */
    public void addList(Integer param) throws InterruptedException {
        log.info("param="+param + " ,--start---currentTime=" + DateUtil.getYMDHMSTime());
        for (int j = 0; j <param ; j++) {
            list.add(j);
        }
        log.info("param="+param + " ,--end---currentTime=" + DateUtil.getYMDHMSTime());
    }
    public void removeList() throws InterruptedException {
        log.info("removeList=" + " ,--start---currentTime=" + DateUtil.getYMDHMSTime());
        while(true){
            AtomicInteger increase = new AtomicInteger(0);
            for(Iterator<Integer> iterator=list.iterator();iterator.hasNext();){
                Integer temp = iterator.next();
                if(increase.get()>100)
                    Thread.sleep(1000);
                iterator.remove();
                increase.getAndIncrement();
            }
        }
//        log.info("removeList=" + " ,--end---currentTime=" + DateUtil.getYMDHMSTime());
    }

}
