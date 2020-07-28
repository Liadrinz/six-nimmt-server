package cn.liadrinz.sixnimmt.biz;

import cn.liadrinz.sixnimmt.data.Message;
import org.springframework.stereotype.Component;

@Component
public class TestBiz {
    public Message testMethod(Message message) {
        message.setData("Hello");
        return message;
    }
}
