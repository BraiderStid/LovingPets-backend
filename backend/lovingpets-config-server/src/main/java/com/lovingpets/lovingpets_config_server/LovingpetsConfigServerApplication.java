package com.lovingpets.lovingpets_config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class LovingpetsConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LovingpetsConfigServerApplication.class, args);
	}

}
