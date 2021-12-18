package cn.jinronga.redis.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Api(value = "案例实战03：天猫网站首页以级UV的Redis统计方案")
@RestController
@Slf4j
public class HyperLogLogController {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
        //自己启动线程模拟，
        new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                Random random = new Random();
                String ip = null;
                ip = random.nextInt(255) + "." + random.nextInt(255) + "."
                        + random.nextInt(255) + "." + random.nextInt(255);

                Long hyl = redisTemplate.opsForHyperLogLog().add("hyl", ip);
                log.info("ip={},该ip访问得次数={}", ip, hyl);

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "name").start();

    }

    @GetMapping("/getHyperLog")
    public Long getHyperLogLogInfo(@RequestParam("key") String key) {
        Long size = redisTemplate.opsForHyperLogLog().size(key);
        return size;
    }
}
