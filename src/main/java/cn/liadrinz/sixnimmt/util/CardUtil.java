package cn.liadrinz.sixnimmt.util;

import cn.liadrinz.sixnimmt.data.protocol.Card;
import cn.liadrinz.sixnimmt.data.protocol.CardState;
import cn.liadrinz.sixnimmt.game.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CardUtil {
    public static List<Card> getNewCards(Game context) {
        List<Card> result = new ArrayList<>();
        Random rand = new Random();
        for (int i = 1; i < Card.MAX; ++i) {
            int random = rand.nextInt();
            Card card = new Card();
            card.setContext(context);
            card.setState(CardState.IN_HAND);
            card.setNumber(i);
            card.setCows(random);
            result.add(card);
        }
        Collections.shuffle(result);
        return result;
    }
}
