package com.binance.demo.mybinance.service.impl;

import cn.hutool.core.util.StrUtil;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.demo.mybinance.builder.BinanceClient;
import com.binance.demo.mybinance.builder.BinanceClientBuilder;
import com.binance.demo.mybinance.service.BinanceDemoService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * 作用：Binance Demo Service
 * </p>
 *
 * @author kyyi
 * @since 2023-05-11 17:22
 */
@Service
public class BinanceDemoServiceImpl implements BinanceDemoService {

  @Value("${binance.api.key:2223232}")
  private String API_KEY;
  @Value("${binance.secret.key:2223232}")
  private String SECRET_KEY;

  @Resource
  BinanceClientBuilder binanceClientBuilder;
  @Resource
  RestTemplate restTemplate;

  @Override
  public String testConnect() {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(API_KEY, SECRET_KEY);
    BinanceApiRestClient client = factory.newRestClient();
    //    String jsonStr = JSONUtil.toJsonStr(
    //        restTemplate.getForObject("https://api.binance.com/api/v1/ping", Object.class));
    return StrUtil.format("Binance服务器时间戳:{}", client.getServerTime());
  }

  @Override
  public String testConnectProxy() {
//    return StrUtil.format("Binance服务器时间戳（Proxy模式）:{}",
//        binanceClientBuilder.client().market().time());
    return StrUtil.format("Binance服务器时间戳（Proxy模式）:{}",
        BinanceClient.client().market().time());
  }

  @Override
  public String testProxyExchangeInfo() {
    return  StrUtil.format("Binance交易信息（Proxy模式）:{}",
        binanceClientBuilder.client().market().exchangeInfo());
  }
}
