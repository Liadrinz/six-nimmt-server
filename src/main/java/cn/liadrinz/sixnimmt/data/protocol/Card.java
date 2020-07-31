package cn.liadrinz.sixnimmt.data.protocol;

import cn.liadrinz.sixnimmt.aop.UpdateIndex;
import cn.liadrinz.sixnimmt.game.Game;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Card {

    public static final int MAX = 105;
    private Long userId;
    private Integer number;
    private CardState state;

    @Setter
    private Integer cows;

    @Setter
    private Game context;

    public boolean equals(Card other) {
        return other.userId.equals(userId)
                && other.number.equals(number)
                && other.cows.equals(cows);
    }

    public void setUserId(Long userId) {
        if (this.userId != null) {
            context.getUserCardsMap().get(this.userId).remove(this);
        }
        this.userId = userId;
        context.getUserCardsMap().get(this.userId).add(this);
    }

    public void setNumber(Integer number) {
        Integer prev = this.number;
        this.number = number;
        if (prev != null) {
            Card original = context.getNumberCardMap().get(this.number);
            original.setNumber(prev);
            context.getNumberCardMap().put(prev, original);
        }
        context.getNumberCardMap().put(this.number, this);
    }

    public void setState(CardState state) {
        if (this.state != null) {
            context.getStateCardsMap().get(this.state).remove(this);
        }
        this.state = state;
        context.getStateCardsMap().get(this.state).add(this);
    }
}
