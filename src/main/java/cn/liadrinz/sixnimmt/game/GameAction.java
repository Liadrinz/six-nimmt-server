package cn.liadrinz.sixnimmt.game;

import cn.liadrinz.sixnimmt.data.protocol.Card;

public interface GameAction {
    /**
     * 发牌
     */
    void dispatchCard();

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
