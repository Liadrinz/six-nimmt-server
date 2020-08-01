package cn.liadrinz.sixnimmt.game;

import cn.liadrinz.sixnimmt.data.entity.User;
import cn.liadrinz.sixnimmt.data.protocol.Card;
import cn.liadrinz.sixnimmt.data.protocol.CardState;
import cn.liadrinz.sixnimmt.util.CardUtil;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Game implements GameAction, GameInfo {
    @Getter
    private final List<User> players;
    @Getter
    private final List<Card> cardPool;

    @Getter
    private final Map<Long, List<Card>> userCardsMap = new HashMap<>(maxPlayers);
    @Getter
    private final Map<CardState, List<Card>> stateCardsMap = new HashMap<>(CardState.values().length);
    @Getter
    private final Map<Integer, Card> numberCardMap = new HashMap<>(Card.MAX);

    public Game(List<User> players) {
        assert players.size() <= maxPlayers;
        this.players = players;
        for (User player : players) {
            userCardsMap.put(player.getId(), new ArrayList<>());
        }
        for (CardState state : CardState.values()) {
            stateCardsMap.put(state, new ArrayList<>());
        }
        for (int i = 1; i < Card.MAX; ++i) {
            numberCardMap.put(i, null);
        }
        for (int i = 0; i < 4; ++i) {
            cardMatrix.add(new ArrayList<>(5));
        }
        this.cardPool = CardUtil.getNewCards(this);
    }

    @Getter
    private final List<List<Card>> cardMatrix = new ArrayList<>();
    private final Queue<Card> gamingBuffer = new ConcurrentLinkedQueue<>();

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
    public void putCard(int card) {
        gamingBuffer.add(numberCardMap.get(card));
    }

    @Override
    public void settle() {
        if (gamingBuffer.size() == players.size()) {
            while (!gamingBuffer.isEmpty()) {
                final Card card = gamingBuffer.poll();
                int minPositiveDelta = Card.MAX;
                List<Card> targetRowToAppend = cardMatrix.get(0);
                for (List<Card> row : cardMatrix) {
                    Card rowsLast = row.get(1 | row.size() - 1);
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

    @Override
    public void startGame() {

    }

    @Override
    public void finishGame() {

    }

    @Override
    public boolean isStarted() {
        return false;
    }
}
