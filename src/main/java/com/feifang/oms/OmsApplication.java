package com.feifang.oms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@SpringBootApplication
@EnableScheduling
public class OmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmsApplication.class, args);
	}

	/**
	 * DataSource配置
	 * @return
	 */
	@Bean
	@ConfigurationProperties(prefix="spring.proxool")
	public DataSource dataSource() {
		return new org.logicalcobwebs.proxool.ProxoolDataSource();
	}

	@Bean(name="transactionManager")
	public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory em){
		return new JpaTransactionManager(em);
	}

	@Bean
	UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
		return new UndertowEmbeddedServletContainerFactory();
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
