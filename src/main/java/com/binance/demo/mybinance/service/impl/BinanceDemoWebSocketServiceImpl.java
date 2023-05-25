package com.binance.demo.mybinance.service.impl;

import com.binance.connector.futures.client.impl.UMWebsocketClientImpl;
import com.binance.demo.mybinance.service.BinanceDemoWebSocketService;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 作用：Binance Demo Web Socket Service
 * </p>
 *
 * @author kyyi
 * @since 2023-05-11 17:22
 */
@Service
public class BinanceDemoWebSocketServiceImpl implements BinanceDemoWebSocketService {

  @Override
  public String testWebSocketConnectProxy() {
    UMWebsocketClientImpl client = new UMWebsocketClientImpl(); // defaults to production websocket URL unless stated
    int streamID1 = client.aggTradeStream("btcusdt", ((event) -> {
      System.out.println(event);
    }));

    //Combining Streams
    ArrayList<String> streams = new ArrayList<>();
    streams.add("btcusdt@trade");
    streams.add("bnbusdt@trade");

    int streamID2 = client.combineStreams(streams, ((event) -> {
      System.out.println(event);
    }));

    //Listening to User Data Stream
    int streamID3 = client.listenUserStream("listenKey", ((event) -> {
      System.out.println(event);
    }));

    //Closing a single stream
    client.closeConnection(streamID1); //closes aggTradeStream-btcusdt

    //Closing all streams
    client.closeAllConnections();
    return "true";
  }
}
