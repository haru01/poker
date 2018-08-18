package poker;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

// 手札
public class Cards {
    private final List<Card> cards;

    public Cards(Card... cards) {
        // 五枚チェック
        this.cards = Arrays.asList(cards);
    }

    // 役名
    public String getHandName() {
        return getHand().name;
    }

    public int getScore() {
        return getHand().score;
    }

    private Hand getHand() {
        // TODO ストレートフラッシュ
        // TODO フォーカード
        if (isFullhouse()) {
            return new Hand("フルハウス", 6000);
        }

        if (isFlash()) {
            return new Hand("フラッシュ", 5000);
        }

        // TODO ストレート

        if (hasOneSameNumber(3)) {
            return new Hand("スリーカード", 3000);
        }

        if (isTwoPair()) {
            return new Hand("ツーペア", 2000);
        }

        if (hasOneSameNumber(2)) {
            return new Hand("ワンペア", 1000);
        }
        // TODO ハイカード、ワンペア...にも強さの順序あり
        return new Hand("ハイカード", 0);
    }

    private boolean isFlash() {
        // ストレートフラッシュとは区別つけていない
        return cards.stream()
                .collect(groupingBy(Card::getSuit))
                .entrySet()
                .stream()
                .filter(e -> e.getValue().size() == 5)
                .collect(toList())
                .size() == 1;
    }

    private boolean isFullhouse() {
        return hasOneSameNumber(3) && hasOneSameNumber(2);
    }

    private boolean isTwoPair() {
        return cards.stream()
                .collect(groupingBy(Card::getRank))
                .entrySet()
                .stream()
                .filter(e -> e.getValue().size() == 2)
                .collect(toList())
                .size() == 2;
    }

    private boolean hasOneSameNumber(int size) {
        return cards.stream()
                .collect(groupingBy(Card::getRank))
                .entrySet()
                .stream()
                .filter(e -> e.getValue().size() == size)
                .collect(toList())
                .size() == 1;
    }

    // 役
    class Hand {
        // 役の名前
        private String name;
        // 役の強さを点数化（勝者をきめられるように）
        private int score;

        private Hand(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return "handName:" + name + ", score:" + score;
        }
    }

    @Override
    public String toString() {
        return getHand() + ", cards:" + cards.toString();
    }
}
