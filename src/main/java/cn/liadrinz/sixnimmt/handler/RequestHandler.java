package cn.liadrinz.sixnimmt.handler;

import cn.liadrinz.sixnimmt.data.Message;
import cn.liadrinz.sixnimmt.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Component
public class RequestHandler {
    public Message handle(Message message) {
        String bizName = message.getHeader().getBiz();
        String methodName = message.getHeader().getMethod();
        try {
            Object bean = SpringContextUtils.getBean(bizName + "Biz");
            Method method = bean.getClass().getMethod(methodName, Message.class);
            return (Message) method.invoke(bean, message);
        } catch (Exception e) {
            log.error("RPC failed", e.getMessage());
        }
        return null;
    }
}
