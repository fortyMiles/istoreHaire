package com.istore.common.web.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component("springUtil")
public class SpringUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
		SpringUtil.applicationContext = applicationContext;
	}

	public static <T> T getBean(Class<T> cla) {
		return applicationContext.getBean(cla);
	}

	public static <T> T getBean(String beanId, Class<T> cla) {
		return applicationContext.getBean(beanId, cla);
	}
	
	public static Object getBean(String beanId){
		return applicationContext.getBean(beanId);
	}

}
