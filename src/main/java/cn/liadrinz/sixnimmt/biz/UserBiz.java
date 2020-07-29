package cn.liadrinz.sixnimmt.biz;

import cn.liadrinz.sixnimmt.dao.UserRepo;
import cn.liadrinz.sixnimmt.data.entity.User;
import cn.liadrinz.sixnimmt.data.protocol.Header;
import cn.liadrinz.sixnimmt.data.protocol.Message;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBiz {
    @Autowired
    private UserRepo userRepo;

    public Message createUser(Message request) {
        Message response = new Message();
        Header respHeader = new Header();
        respHeader.setRespMethod(request.getHeader().getRespMethod());
        response.setHeader(respHeader);
        User user = userRepo.save(JSON.parseObject(request.getData(), User.class));
        response.setData(JSON.toJSONString(user));
        return response;
    }
}
