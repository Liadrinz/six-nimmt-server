package cn.liadrinz.sixnimmt.handler;

import cn.liadrinz.sixnimmt.data.Message;
import cn.liadrinz.sixnimmt.endpoint.GameEndpoint;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import cn.liadrinz.sixnimmt.data.EnumTypes.*;

import javax.websocket.Session;
import java.nio.ByteBuffer;
import java.util.List;

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
