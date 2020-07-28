package cn.liadrinz.sixnimmt.util;

import cn.liadrinz.sixnimmt.data.Card;

import java.util.ArrayList;
import java.util.List;

public class CardUtil {
    public static List<Card> getNewCards() {
        List<Card> result = new ArrayList<>();
        for (int i = 0; i < 104; ++i) {
            int random = (int) (Math.random() * 7);
            Card card = new Card();
            card.setNumber(i + 1);
            card.setCows(random);
            result.add(card);
        }
        return result;
    }
}
