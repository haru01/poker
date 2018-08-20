package poker;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

// 役
public class Hand {
    private final List<Card> cards;

    public Hand(Card... cards) {
        // 五枚チェック
        this.cards = Arrays.asList(cards);
    }

    // 役名
    public String getName() {
        return getHand().getKey();
    }

    // 役の強さを点数化。比較できるように
    public int getScore() {
        return getHand().getValue();
    }

    private AbstractMap.SimpleEntry<String, Integer> getHand() {
        // TODO ロイヤルストレートフラッシュ
        // TODO ストレートフラッシュ
        // TODO フォーカード
        if (isFullhouse()) {
            return new AbstractMap.SimpleEntry<>("フルハウス", 6000);
        }

        if (isFlush()) {
            return new AbstractMap.SimpleEntry<>("フラッシュ", 5000);
        }

        // TODO ストレート

        if (hasOneSameNumber(3)) {
            return new AbstractMap.SimpleEntry<>("スリーカード", 3000);
        }

        if (isTwoPair()) {
            return new AbstractMap.SimpleEntry<>("ツーペア", 2000);
        }

        if (hasOneSameNumber(2)) {
            return new AbstractMap.SimpleEntry<>("ワンペア", 1000);
        }
        // TODO ハイカード、ワンペア...にも強さの順序あり
        return new AbstractMap.SimpleEntry<>("ハイカード", 0);
    }

    private boolean isFlush() {
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

    @Override
    public String toString() {
        return getHand() + ", cards:" + cards.toString();
    }
}
