package com.binance.demo.mybinance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyBinanceApplication {

	public static void main(String[] args) {
		//1.HTTP
		//http.proxyHost
		//http.proxyPort [默认值：80]
		//http.nonProxyHosts
		//
		//2.HTTPS
		//https.proxyHost
		//https.proxyPort [默认值：443]
		//https.nonProxyHosts
		//
		//3.FTP
		//ftp.proxyHost
		//ftp.proxyPort [默认值：80]
		//ftp.nonProxyHosts
		//
		//4.SOCKS
		//socksProxyHost
		//socksProxyPort [默认值：1080]
		System.setProperty("https.proxyHost","127.0.0.1");
		System.setProperty("https.proxyPort","7890");
		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "7890");
		System.setProperty("socksProxyHost", "127.0.0.1");
		System.setProperty("socksProxyPort", "7891");
		SpringApplication.run(MyBinanceApplication.class, args);
	}

}
