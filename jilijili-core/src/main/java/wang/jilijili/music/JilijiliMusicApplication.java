package wang.jilijili.music;

import lombok.extern.java.Log;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author admin
 */
@Log
@SpringBootApplication
@ConfigurationPropertiesScan
@MapperScan(basePackages = "wang.jilijili.music.mapper")
@ComponentScan(basePackages = "wang.jilijili.music.*")
public class JilijiliMusicApplication {
    private static Environment environment;


    public static void main(String[] args) {
        SpringApplication.run(JilijiliMusicApplication.class, args);
        String port = environment.getProperty("server.port");
        log.info("https://" + getIpAddress() + ":" + port + "/swagger-ui/index.html 接口文档");
        log.info("https://" + getIpAddress() + ":" + port + "/test/test 测试接口");


    }

    public static String getIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "null";
    }


    @Autowired
    public void setEnvironment(Environment environment) {
        JilijiliMusicApplication.environment = environment;
    }
}
