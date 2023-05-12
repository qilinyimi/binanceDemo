package com.binance.demo.mybinance.service.impl;

import cn.hutool.core.util.StrUtil;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.connector.futures.client.impl.CMFuturesClientImpl;
import com.binance.connector.futures.client.utils.ProxyAuth;
import com.binance.demo.mybinance.service.BinanceDemoService;
import jakarta.annotation.Resource;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
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
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(API_KEY, SECRET_KEY);
    BinanceApiRestClient client = factory.newRestClient();
//    client.getServerTime();
//    String jsonStr = JSONUtil.toJsonStr(
//        restTemplate.getForObject("https://api.binance.com/api/v1/ping", Object.class));
    return StrUtil.format("Binance服务器时间戳:{}",client.getServerTime());
  }

  @Override
  public String testConnectProxy() {
//    UMFuturesClientImpl client = new UMFuturesClientImpl(API_KEY, SECRET_KEY);
    CMFuturesClientImpl client = new CMFuturesClientImpl(API_KEY, SECRET_KEY);
    Proxy proxyConn = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
    Authenticator auth = new Authenticator() {
      public Request authenticate(Route route, Response response) throws IOException {
        if (response.request().header("Proxy-Authorization") != null) {
          return null; // Give up, we've already failed to authenticate.
        }

        String credential = Credentials.basic("username", "password");
        return response.request().newBuilder().header("Proxy-Authorization", credential).build();

      }
    };
    ProxyAuth proxy = new ProxyAuth(proxyConn, auth);
    client.setProxy(proxy);
    return StrUtil.format("Binance服务器时间戳（Proxy模式）:{}",client.market().time());
  }
}
