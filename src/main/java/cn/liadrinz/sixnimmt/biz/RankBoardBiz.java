package cn.liadrinz.sixnimmt.biz;

import cn.liadrinz.sixnimmt.dao.RankBoardRepo;
import cn.liadrinz.sixnimmt.data.entity.RankBoard;
import cn.liadrinz.sixnimmt.data.protocol.Header;
import cn.liadrinz.sixnimmt.data.protocol.Message;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RankBoardBiz {
    @Autowired
    private RankBoardRepo rankBoardRepo;

    public Message createRankBoard(Message request) {
        Message response = new Message();
        Header respHeader = new Header();
        respHeader.setRespMethod(request.getHeader().getRespMethod());
        RankBoard rankBoard = rankBoardRepo.save(JSON.parseObject(request.getData(), RankBoard.class));
        response.setData(JSON.toJSONString(rankBoard));
        return response;
    }

}
