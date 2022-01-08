package com.example.MusicApp2022;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


// Class that we use to access beans which are created by Spring framework
public class SpringApplicationContext implements ApplicationContextAware {

	private static ApplicationContext CONTEXT;
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
       CONTEXT = context;		
	}
	
	public static Object getBean(String beanName) {
		return CONTEXT.getBean(beanName);
	}
	
	

}
