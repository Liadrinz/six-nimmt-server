package cn.liadrinz.sixnimmt.game;

import cn.liadrinz.sixnimmt.data.entity.User;
import cn.liadrinz.sixnimmt.data.protocol.Card;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GameTest {

    private static final List<User> users = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        for (int i = 1; i < 4; ++i) {
            User user = new User();
            user.setId(i);
            users.add(user);
        }
    }

    @Test
    void gameInitTest() {
        Game game = new Game(users);
        game.dispatchCard();
        int prev = -1;
        for (List<Card> cardsEachUser : game.getUserCardsMap().values()) {
            int size = cardsEachUser.size();
            assert prev == -1 || prev == size;
            prev = size;
        }
    }

    @Test
    void cardPutTest() {
        Game game = new Game(users);
        game.dispatchCard();
        for (long i = 1; i < 4; ++i) {
            Card card = game.getUserCardsMap().get(i).get(0);
            game.putCard(card);
        }
        game.settle();
    }
}
