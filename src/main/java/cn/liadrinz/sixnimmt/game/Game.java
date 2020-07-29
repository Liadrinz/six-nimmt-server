package cn.liadrinz.sixnimmt.game;

import cn.liadrinz.sixnimmt.data.entity.User;
import cn.liadrinz.sixnimmt.data.protocol.Card;
import cn.liadrinz.sixnimmt.data.protocol.CardState;
import cn.liadrinz.sixnimmt.util.CardUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Game implements GameAction, GameInfo {
    private final List<User> players;
    private final List<Card> cardPool;

    public Game(List<User> players) {
        assert players.size() <= maxPlayers;
        this.cardPool = CardUtil.getNewCards();
        this.players = players;
    }

    private List<List<Card>> cardMatrix = new ArrayList<>();
    private Queue<Card> gamingBuffer = new ConcurrentLinkedQueue<>();

    @Override
    public void dispatchCard() {
        for (int i = 0; i < cardsEachPlayer * players.size(); ++i) {
            cardPool.get(i).setUserId(players.get(i % players.size()).getId());
        }
    }

    @Override
    public void putCard(Card card) {
        gamingBuffer.add(card);
    }

    @Override
    public void settle() {
        if (gamingBuffer.size() == players.size()) {
            while (!gamingBuffer.isEmpty()) {
                final Card card = gamingBuffer.poll();
                int minPositiveDelta = Card.MAX;
                List<Card> targetRowToAppend = cardMatrix.get(0);
                for (List<Card> row : cardMatrix) {
                    Card rowsLast = row.get(row.size() - 1);
                    int delta = card.getNumber() - rowsLast.getNumber();
                    if (delta > 0 && delta < minPositiveDelta) {
                        minPositiveDelta = delta;
                        targetRowToAppend = row;
                    }
                }
                if (targetRowToAppend.size() == 5) {
                    targetRowToAppend.forEach(e -> {
                        e.setUserId(card.getUserId());
                    });
                }
                targetRowToAppend.clear();
                targetRowToAppend.add(card);
            }
        }
    }

    @Override
    public int getCows(int userId) {
        return 0;
    }

    @Override
    public List<Card> getCards(Integer userId, CardState cardState) {
        return null;
    }
}
