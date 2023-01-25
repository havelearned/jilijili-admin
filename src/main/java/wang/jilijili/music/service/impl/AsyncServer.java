package wang.jilijili.music.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: Amani
 * @Date: 2023/1/22 22:01
 * @Description:
 */
@Service
public class AsyncServer {
    @Async
    public void asyncTest(Integer counter, CountDownLatch downLatch) throws InterruptedException {
        Thread.sleep(2000);
        for (int i = 0; i < 10; i++) {
            counter += i;
        }
        System.out.println("异步任务,线程名称:" + Thread.currentThread().getName() + ",执行的任务->" + counter);

        // countDownLatch这个类使一个线程等待其他线程各自执行完毕后再执行。
        // 是通过一个计数器来实现的，计数器的初始值是线程的数量。每当一个线程执行完毕后，计数器的值就-1，当计数器的值为0时，表示所有线程都执行完毕，然后在闭锁上等待的线程就可以恢复工作了
        downLatch.countDown();

    }
}
