package com.binance.demo.mybinance.controller;


import com.binance.demo.mybinance.service.BinanceDemoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 作用：Binance Demo
 * </p>
 *
 * @author kyyi
 * @since 2023-05-11 17:19
 */
@RestController()
@RequestMapping("binance/")
public class DemoController {

  @Resource
  BinanceDemoService binanceDemoService;

  /**
   * 测试链接
   */
  @GetMapping("test/connect")
  public String testConnect() {
    return binanceDemoService.testConnect();
  }
}
