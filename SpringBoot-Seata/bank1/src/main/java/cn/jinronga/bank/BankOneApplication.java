package cn.jinronga.bank;

import cn.jinronga.annotation.EnableJRFeignClients;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJRFeignClients
@MapperScan(value = "cn.jinronga.bank.mapper")
public class BankOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankOneApplication.class, args);
	}

}
