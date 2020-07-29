package cn.liadrinz.sixnimmt.handler;

import cn.liadrinz.sixnimmt.data.protocol.Message;
import cn.liadrinz.sixnimmt.data.protocol.RespMethod;
import cn.liadrinz.sixnimmt.endpoint.GameEndpoint;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

@Component
public class ResponseHandler {
    public void handle(Message message, GameEndpoint endpoint) {
        if (RespMethod.SINGLE == message.getHeader().getRespMethod()) {
            endpoint.getSession().getAsyncRemote().sendText(JSON.toJSONString(message));
        } else if (RespMethod.BROADCAST == message.getHeader().getRespMethod()) {
            for (Session session : GameEndpoint.getSessionMap().get(endpoint.getRoomId()).values()) {
                session.getAsyncRemote().sendText(JSON.toJSONString(message));
            }
        }
    }
}
