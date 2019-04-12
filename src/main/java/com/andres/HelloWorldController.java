package com.andres;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		
		return "Hello";	
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		
		return new HelloWorldBean("Hello");	
	}
	
	
	
	@GetMapping(path = "/hello-world/{name}")
	public HelloWorldBean helloWorldPath(@PathVariable String name) {
		
		return new HelloWorldBean("Hello "+name);	
	}
	
	@GetMapping(path = "/hello-world-internacional")
	public String helloWorldInter(@RequestHeader(name = "Accept-Language",required = false) Locale locale) {
		
		// messageSource.getMessage --> Refiere al Bean de Application 
		return messageSource.getMessage("good.morning.message",null,locale);
	}
	
	@GetMapping(path = "/hello-world-internacional2")//por Header automatico
	public String helloWorldInter2() {
		
		// messageSource.getMessage --> Refiere al Bean de Application 
		return messageSource.getMessage("good.morning.message",null,LocaleContextHolder.getLocale());
	}
}
