package junit.threadStudy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.dingjh.example.threadStudy.TaskExecuter;
import com.dingjh.example.threadStudy.TaskJob;
import com.dingjh.example.threadStudy.useExample.TestTask;

public class ThreadStudyTest {
    @Test
    public void testThreadStudy() {
        int count = 4;
        TaskExecuter taskExecuter = new TaskExecuter(count);// 初始化任务池

        Map<String, Object> paramter1 = new HashMap<String, Object>();// 参数map
        paramter1.put("test", "test1");

        Map<String, Object> paramter2 = new HashMap<String, Object>();// 参数map
        paramter2.put("test", "test2");

        Map<String, Object> paramter3 = new HashMap<String, Object>();// 参数map
        paramter3.put("test", "test3");

        Map<String, Object> paramter4 = new HashMap<String, Object>();// 参数map
        paramter4.put("test", "test4");

        TaskJob taskJob1 = new TaskJob(paramter1, new TestTask());
        TaskJob taskJob2 = new TaskJob(paramter2, new TestTask());
        TaskJob taskJob3 = new TaskJob(paramter3, new TestTask());
        TaskJob taskJob4 = new TaskJob(paramter4, new TestTask());

        taskExecuter.fork(taskJob1);// 任务派发
        taskExecuter.fork(taskJob2);// 任务派发
        taskExecuter.fork(taskJob3);// 任务派发
        taskExecuter.fork(taskJob4);// 任务派发

        taskExecuter.join();
    }

    @Test
    public void testThreadStudy2() throws InterruptedException, ExecutionException {
        // ExecutorService es = Executors.newSingleThreadExecutor();
        ExecutorService es = Executors.newFixedThreadPool(2);// 初始化线程池大小
        Future<?> fr = es.submit(new RunnableTest());// 提交任务
        Future<?> fc = es.submit(new CallableTest());// 提交任务 // 取得返回值并输出
        System.out.println((String) fc.get()); // 检查任务是否执行完毕
        if (fr.isDone()) {
            System.out.println("执行完毕-RunnableTest.run()");
        } else {
            System.out.println("未执行完-RunnableTest.run()");
        } // 检查任务是否执行完毕
        if (fc.isDone()) {
            System.out.println("执行完毕-CallableTest.run()");
        } else {
            System.out.println("未执行完-CallableTest.run()");
        } // 停止线程池服务
        es.shutdown();
    }
}

class RunnableTest implements Runnable {
    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("已经执行-RunnableTest.run()");
    }
}

class CallableTest implements Callable<Object> {
    public Object call() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("已经执行-CallableTest.call()");
        return "返回值-CallableTest.call()";
    }
}
