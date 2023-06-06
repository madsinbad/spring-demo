package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: demo.SpringDemoApplication
 * @Description:
 * @Author: weizhengbo
 * @Date: 2023/6/5 16:26
 * @Version: 1.0
 */
@SpringBootApplication
public class SpringDemoApplication {

    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }
}
