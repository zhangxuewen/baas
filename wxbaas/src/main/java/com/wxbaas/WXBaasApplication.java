package com.wxbaas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * TODO spring boot
 * 
 * @author xuewen.zhangxuewen
 *
 */
@SpringBootApplication
public class WXBaasApplication implements CommandLineRunner {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(WXBaasApplication.class, args);
		System.out.println("================main start=======================");
	}
	

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	

}
