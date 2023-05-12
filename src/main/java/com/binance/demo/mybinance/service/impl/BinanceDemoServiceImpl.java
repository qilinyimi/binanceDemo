package com.binance.demo.mybinance.service.impl;

import cn.hutool.json.JSONUtil;
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
  RestTemplate restTemplate;

  @Override
  public String testConnect() {
//    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(API_KEY, SECRET_KEY);
//    BinanceApiRestClient client = factory.newRestClient();
//    client.ping();
    JSONUtil.toJsonStr(restTemplate.getForObject("https://api.binance.com/api/v1/ping", Object.class));
    return "ping success";
  }
}
