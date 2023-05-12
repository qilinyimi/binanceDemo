package com.binance.demo.mybinance.service;

/**
 * <p>
 * 作用：BinanceDemo
 * </p>
 *
 * @author kyyi
 * @since 2023-05-11 17:20
 */
public interface BinanceDemoService {

  /**
   * 测试链接
   */
  String testConnect();

  /**
   * 测试链接[代理]
   */
  String testConnectProxy();

  /**
   * 交易信息[代理]
   */
  String testProxyExchangeInfo();
}
