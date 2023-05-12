package com.binance.demo.mybinance.service.impl;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.demo.mybinance.service.BinanceDemoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

  @Override
  public String testConnect() {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(API_KEY, SECRET_KEY);
    BinanceApiRestClient client = factory.newRestClient();
    client.ping();
    return "ping success";
  }
}
