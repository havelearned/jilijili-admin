package wang.jilijili.common.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import wang.jilijili.common.core.pojo.entity.FileManage;
import wang.jilijili.common.core.service.FileManageService;

import java.util.concurrent.CountDownLatch;

/**
 * @author admin
 * @Date: 2023/1/22 22:01
 * @Description:
 */
@Service
public class AsyncServerImpl {

    FileManageService fileManageService;

    @Autowired
    public void setFileManageService(FileManageService fileManageService) {
        this.fileManageService = fileManageService;
    }

    /**
     * 异步保存文件信息
     *
     * @param fileManage
     */
    @Async
    public void asyncSaveFile(FileManage fileManage) {
        this.fileManageService.save(fileManage);
    }

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
