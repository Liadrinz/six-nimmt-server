package cn.liadrinz.sixnimmt.endpoint;

import cn.liadrinz.sixnimmt.handler.RequestHandler;
import cn.liadrinz.sixnimmt.data.Message;
import cn.liadrinz.sixnimmt.handler.ResponseHandler;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@ServerEndpoint("/game/{uid}")
@Component
public class GameEndpoint {
    private static RequestHandler requestHandler;
    private static ResponseHandler responseHandler;
    @Getter
    private static List<Session> sessions;
    @Getter
    private Session session;

    @OnOpen
    public void onOpen(Session session, @PathParam("uid") Integer uid) {
        this.session = session;
    }
    @OnMessage
    public void onMessage(String reqMsg, Session session) {
        responseHandler.handle(requestHandler.handle(JSON.parseObject(reqMsg, Message.class)), this);
    }
    @OnError
    public void onError(Session session, Throwable e) {

    }
    @OnClose
    public void onClose() {

    }

    @Autowired
    void getRequestHandler(RequestHandler requestHandler) {
        GameEndpoint.requestHandler = requestHandler;
    }

    @Autowired
    void getResponseHandler(ResponseHandler responseHandler) {
        GameEndpoint.responseHandler = responseHandler;
    }
}
