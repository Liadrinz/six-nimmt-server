package cn.liadrinz.sixnimmt.util;

import cn.liadrinz.sixnimmt.data.protocol.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CardUtil {
    public static List<Card> getNewCards() {
        List<Card> result = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < Card.MAX; ++i) {
            int random = rand.nextInt();
            Card card = new Card();
            card.setNumber(i + 1);
            card.setCows(random);
            result.add(card);
        }
        Collections.shuffle(result);
        return result;
    }
}
