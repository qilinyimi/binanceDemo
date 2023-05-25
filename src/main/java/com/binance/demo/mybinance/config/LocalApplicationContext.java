package com.binance.demo.mybinance.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 作用：MyApplicationContext
 * </p>
 *
 * @author kyyi
 * @since 2023-05-16 9:31
 */
@Component
public class LocalApplicationContext implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(@NotNull ApplicationContext applicationContext)
      throws BeansException {
    LocalApplicationContext.applicationContext = applicationContext;
  }

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  /**
   * 获得Boolean类型的Properties值
   */
  public static Boolean getBoolean(String property) {
    return applicationContext.getEnvironment().getProperty(property, Boolean.class);
  }

  /**
   * This code is a static method that returns a bean from the application context. The method takes two parameters, a String property and a Class type. It then uses the applicationContext object to get the bean with the given property and required type and returns it.
   *  Step-by-step explanation:
   * 1. The method is declared as static, meaning it can be called without creating an instance of the class.
   * 2. The method takes two parameters: a String property and a Class type.
   * 3. The method uses the applicationContext object to get the bean with the given property and required type.
   * 4. The method returns the bean.
   */
  public static <T> T getBean(String property, Class<T> requiredType) {
    return applicationContext.getBean(property, requiredType);
  }
}
