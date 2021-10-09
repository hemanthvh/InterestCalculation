package com.interest;

import javax.jms.ConnectionFactory;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.jms.support.converter.MessageType;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAsync
@SpringBootApplication
@EnableSwagger2
@EnableJms
@ComponentScan(basePackages = {"com.interest"})
public class InterestCalculationApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterestCalculationApplication.class, args);
	}
	
	@Bean
	public JmsListenerContainerFactory<?> beanFactory (ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer){
		
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		
		return factory;
	}
	
	@Bean
	public MessageConverter jackonJmsMessageConverter() {
		
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);	
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
}
