package cn.liadrinz.sixnimmt.game;

import cn.liadrinz.sixnimmt.data.entity.User;
import cn.liadrinz.sixnimmt.data.protocol.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameTest {

    @Test
    public void gameInitTest() {
        final List<User> users = new ArrayList<>();
        for (int i = 1; i < 11; ++i) {
            User user = new User();
            user.setId(i);
            users.add(user);
        }
        Game game = new Game(users);
        game.dispatchCard();
        int prev = -1;
        for (List<Card> cardsEachUser : game.getUserCardsMap().values()) {
            int size = cardsEachUser.size();
            assert prev == -1 || prev == size;
            prev = size;
        }
    }
}
