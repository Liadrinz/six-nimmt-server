package cn.liadrinz.sixnimmt.data.protocol;

import lombok.Data;

@Data
public class Card {

    public static final int MAX = 105;
    private Long userId;
    private Integer number;
    private Integer cows;

    public boolean equals(Card other) {
        return other.userId.equals(userId)
                && other.number.equals(number)
                && other.cows.equals(cows);
    }
}
