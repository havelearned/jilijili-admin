package wang.jilijili.music.controller;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.jilijili.music.service.impl.AsyncServerImpl;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: Amani
 * @Date: 2023/1/22 21:39
 * @Description:
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private AsyncServerImpl asyncServer;



//    @Autowired
//    private ReadCSVUtil readCSVUtil;

/*
    @Autowired
    private ReadCSVServer readCSVServer;
*/


/*
    @RequestMapping("/test/readCSV")
    public JSONObject testReadCSV(@RequestBody JSONObject get) throws JSONException, IOException, InterruptedException {
        JSONObject output = new JSONObject();
        String filePath = get.getString("filePath");
        filePath.replace("\\", "/");
        long startTime = System.currentTimeMillis();
        List<String> fileInPaths = this.readCSVUtil.getFileInPath(filePath);
        // 创建同步工具
        CountDownLatch downLatch = new CountDownLatch(fileInPaths.size());
        if (fileInPaths != null) {
            for (String file : fileInPaths) {
                this.readCSVServer.readCSVAndWrite(downLatch, file, filePath);
            }

        }
        downLatch.await();
        long endTime = System.currentTimeMillis();
        output.put("msg", "succeed");
        output.put("data", "已处理" + fileInPaths.size() + "个文件;耗时" + (endTime - startTime) / 1000 + "秒");
        return output;


    }
*/

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

    @RequestMapping("/")
    public String testSay(Cookie cookies) {
        System.out.println("获取得到的cookies:" + cookies);

        return "访问成功";

    }

    @RequestMapping("/test")
    public String test() {
        return "success";
    }
}
