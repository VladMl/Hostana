package com.vladml.hostana;

import com.vladml.hostana.repository.HostRepository;
import com.vladml.hostana.util.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HostanaApp implements CommandLineRunner {


	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	HostRepository hostRepository;

	@Autowired
	private Config myConfig;

	public static void main(String[] args) {
		System.out.println("Current Directory = " + System.getProperty("user.dir"));
		ConfigurableApplicationContext ctx = SpringApplication.run(HostanaApp.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		logger.info("All users -> {}", hostRepository.findAll());
		myConfig.load();
	}

}
