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
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ServerEndpoint("/game/{roomId}/{uId}")
@Component
public class GameEndpoint {

    private static RequestHandler requestHandler;
    private static ResponseHandler responseHandler;

    @Getter
    private static Map<Integer, Map<Integer, Session>> sessionMap;
    @Getter
    private Session session;
    @Getter
    private Integer roomId, uId;

    // 生命周期

    @OnOpen
    public void onOpen(Session session, @PathParam("roomId") Integer roomId, @PathParam("uId") Integer uId) {
        this.session = session;
        this.roomId = roomId;
        this.uId = uId;
        if (!sessionMap.containsKey(roomId)) {
            sessionMap.put(roomId, new HashMap<>(16));
        }
        sessionMap.get(roomId).put(uId, session);
    }
    @OnMessage
    public void onMessage(String reqMsg, Session session) {
        responseHandler.handle(requestHandler.handle(JSON.parseObject(reqMsg, Message.class)), this);
    }
    @OnError
    public void onError(Session session, Throwable e) {
        log.error(e.getMessage());
    }
    @OnClose
    public void onClose() {
        this.session = null;
        sessionMap.get(roomId).remove(uId);
    }

    // 注入

    @Autowired
    void getRequestHandler(RequestHandler requestHandler) {
        GameEndpoint.requestHandler = requestHandler;
    }

    @Autowired
    void getResponseHandler(ResponseHandler responseHandler) {
        GameEndpoint.responseHandler = responseHandler;
    }
}
