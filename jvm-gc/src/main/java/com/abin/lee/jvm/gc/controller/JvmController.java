package com.abin.lee.jvm.gc.controller;

import com.abin.lee.jvm.gc.cms.MemoryOverflowPerformance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by abin on 2018/4/11 0:02.
 * spring-boot-rapid
 * com.abin.lee.rapid.framework.api.controller
 */
@Controller
@RequestMapping("/jvm")
@Slf4j
public class JvmController {

    @Autowired
    MemoryOverflowPerformance memoryOverflowPerformance;


    /**
     * -server -Xms30m -Xmx30m -XX:NewSize=20m -XX:MaxNewSize=20m -XX:PermSize=10m -XX:MaxPermSize=10m -XX:-UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection  -XX:CMSInitiatingOccupancyFraction=80 -XX:+PrintGCTimeStamps -XX:+PrintGCDetails
     * @param param
     * @return
     */
    @RequestMapping(value = "/cmsAddList")
    @ResponseBody
    public String cmsAddList(Integer param) {
        String result = "FAILURE";
        try {
            this.memoryOverflowPerformance.addList(param);
            result = "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/cmsRemoveList")
    @ResponseBody
    public String cmsRemoveList(Integer param) {
        String result = "FAILURE";
        try {
            this.memoryOverflowPerformance.removeList();
            result = "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
