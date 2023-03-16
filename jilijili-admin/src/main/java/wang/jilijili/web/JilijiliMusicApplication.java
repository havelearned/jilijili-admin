package wang.jilijili.web;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author admin
 */
@Log
@SpringBootApplication
@ComponentScan(basePackages = "wang.jilijili")
public class JilijiliMusicApplication {
    private static Environment environment;

    private static PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(JilijiliMusicApplication.class, args);
        String port = environment.getProperty("server.port");
        log.info("http://" + getIpAddress() + ":" + port + "/swagger-ui/index.html 接口文档");
        log.info("http://" + getIpAddress() + ":" + port + "/test/test 测试接口");

        log.info("密码:"+passwordEncoder.encode("123456"));
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


    @Autowired
    public  void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        JilijiliMusicApplication.passwordEncoder = passwordEncoder;
    }
}
