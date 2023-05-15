package com.binance.demo.mybinance.builder;

import cn.hutool.core.util.StrUtil;
import com.binance.connector.futures.client.impl.CMFuturesClientImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 作用：客户端构建选择器
 * </p>
 *
 * @author zhanglb1@tedu.cn
 * @since 2023-05-14 8:51
 */
@Slf4j
@Component
public class BinanceClientBuilder {

  @Value("${client.proxy:false}")
  Boolean proxy;

  @Resource(name = "cmFuturesClientImplClient")
  CMFuturesClientImpl cmFuturesClientImplClient;

  @Resource(name = "cmFuturesClientImplClientProxy")
  CMFuturesClientImpl cmFuturesClientImplClientProxy;

  public CMFuturesClientImpl client() {
    log.info(StrUtil.format("CMFuturesClient -->[{}] model",proxy? "Proxy":"Direct"));
    if (proxy) {
      return cmFuturesClientImplClientProxy;
    }
    return cmFuturesClientImplClient;
  }
}
