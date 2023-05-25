package com.binance.demo.mybinance.config;

import com.binance.connector.futures.client.impl.CMFuturesClientImpl;
import com.binance.connector.futures.client.utils.ProxyAuth;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 作用：CMFuturesClientImpl  Client
 * </p>
 *
 * @author kyyi
 * @since 2023-05-12 19:04
 */
@Configuration
public class CMFuturesClientImplClient {
  @Value("${binance.api.key:dbefbc809e3e83c283a984c3a1459732ea7db1360ca80c5c2c8867408d28cc83}")
  private String API_KEY;
  @Value("${binance.secret.key:2b5eb11e18796d12d88f13dc27dbbd02c2cc51ff7059765ed9821957d82bb4d9}")
  private String SECRET_KEY;
  @Bean("cmFuturesClientImplClient")
  public CMFuturesClientImpl cmFuturesClientImplClient() {
    return new CMFuturesClientImpl(API_KEY, SECRET_KEY);
  }
  @Bean("cmFuturesClientImplClientProxy")
  public CMFuturesClientImpl cmFuturesClientImplClientProxy() {
    CMFuturesClientImpl client = new CMFuturesClientImpl(API_KEY, SECRET_KEY);
    Proxy proxyConn = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
    //本地代理不需要定义认证信息
    Authenticator auth = new Authenticator() {
      public Request authenticate(Route route, Response response) throws IOException {
        if (response.request().header("Proxy-Authorization") != null) {
          return null; // Give up, we've already failed to authenticate.
        }

        String credential = Credentials.basic("username", "password");
        return response.request().newBuilder().header("Proxy-Authorization", credential).build();

      }
    };
    client.setProxy(new ProxyAuth(proxyConn, auth));
    return client;
  }
}
