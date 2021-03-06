package cn.liadrinz.sixnimmt.game;

import cn.liadrinz.sixnimmt.data.protocol.Card;

public interface GameAction {
    /**
     * 开始游戏
     */
    void startGame();

    /**
     * 结束游戏
     */
    void finishGame();

    /**
     * 发牌
     */
    void dispatchCard();

    /**
     * 出一张牌
     * @param card 卡牌数字
     */
    void putCard(int card);

    /**
     * 出一张牌
     * @param card 卡牌
     */
    void putCard(Card card);

    /**
     * 结算一回合的出牌
     */
    void settle();
}
