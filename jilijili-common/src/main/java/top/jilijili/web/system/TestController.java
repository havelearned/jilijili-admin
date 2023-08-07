package top.jilijili.web.system;


import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jilijili.common.core.service.impl.AsyncServerImpl;

import java.util.concurrent.CountDownLatch;

/**
 * 测试管理
 *
 * @author admin
 * @Date: 2023/1/22 21:39
 * @Description: 测试管理
 */
@RestController
@RequestMapping("/test")
public class TestController {

    AsyncServerImpl asyncServer;

    public TestController(AsyncServerImpl asyncServer) {
        this.asyncServer = asyncServer;
    }

    @RequestMapping("/testThread")
    public JSONObject testThread() throws InterruptedException {
        JSONObject output = new JSONObject();
        long startTime = System.currentTimeMillis();
        int counter = 10;

        //创建同步工具锁帧器
        CountDownLatch downLatch = new CountDownLatch(counter);
        for (int i = 0; i < counter; i++) {
            asyncServer.asyncTest(i, downLatch);
        }
        // 等待执行完成
        downLatch.await();
        long endTime = System.currentTimeMillis();
        output.put("msg", "succeed");
        output.put("花费时间: ", endTime - startTime);
        return output;

    }


    @RequestMapping("/test")
    public String test() {
        return "success";
    }
}
