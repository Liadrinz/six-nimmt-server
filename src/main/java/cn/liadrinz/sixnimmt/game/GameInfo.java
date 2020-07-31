package cn.liadrinz.sixnimmt.game;

import cn.liadrinz.sixnimmt.data.protocol.Card;
import cn.liadrinz.sixnimmt.data.protocol.CardState;

import java.util.List;

public interface GameInfo {
    int maxPlayers = 10;
    int cardsEachPlayer = 10;

    /**
     *
     * @return 游戏是否开始
     */
    boolean isStarted();

    /**
     * 获取用户本局牛头数量
     * @param userId 用户id
     * @return 用户本局牛头数量
     */
    int getCows(int userId);

    /**
     * 获取指定用户指定状态的卡牌
     * @param userId 用户id
     * @param cardState 卡牌状态
     * @return 符合要求的所有卡牌
     */
    List<Card> getCards(Integer userId, CardState cardState);

    /**
     * 获取场面情况
     * @return 场面情况
     */
    List<List<Card>> getCardMatrix();
}
