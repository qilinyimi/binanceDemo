package com.binance.demo.mybinance.builder;

import com.binance.connector.futures.client.impl.CMFuturesClientImpl;
import com.binance.demo.mybinance.config.LocalApplicationContext;

/**
 * <p>
 * 作用：Binance Client
 * </p>
 *
 * @author zhanglb1@tedu.cn
 * @since 2023-05-16 9:35
 */
public class BinanceClient {
   public static CMFuturesClientImpl client(){
     Boolean clientProxy = LocalApplicationContext.getBoolean("client.proxy");
      if(clientProxy){
          return LocalApplicationContext.getBean("cmFuturesClientImplClientProxy",CMFuturesClientImpl.class);
      }
     return LocalApplicationContext.getBean("cmFuturesClientImplClient",CMFuturesClientImpl.class);
   }
}
