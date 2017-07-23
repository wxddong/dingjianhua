package com.dingjh.example.threadStudy.useExample;

import java.util.Map;

import com.dingjh.example.threadStudy.IFactoryService;
import com.dingjh.log.logUtil.LogManager;

public class TestTask implements IFactoryService {
    @Override
    public Map<String, Object> work(Map<String, Object> paramter) {
        // TODO Auto-generated method stub
        try {
            String testStr = paramter.get("test").toString();
            System.out.println(Thread.currentThread().getName());
            System.out.println(testStr);
            if (testStr.contains("2")) {
                System.out.println("我是2222");
                try {
                    Thread.sleep(2000);// 为了测试出效果，让每次任务执行都需要一定时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                LogManager.log(LogManager.LEVEL_INFO,
                        LogManager.LEVEL_INFO,this.getClass().getName(),"work",
                        "我是2222停止后 " );
                System.out.println("我是2222停止后");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("异常信息：" + e.getMessage());
        }
        return paramter;
    }
}
