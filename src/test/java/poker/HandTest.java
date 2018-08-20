package poker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class HandTest {

    @DisplayName("スコアを使って役の強い順に並べかえできる")
    @Nested
    class getScoreAndSort {
        @Test
        void sortSandbox() {
            Hand highCard = new Hand(highCard());
            Hand onePair = new Hand(onePair());
            Hand twoPair = new Hand(twoPair());
            Hand threeCard = new Hand(threeCard());
            Hand flush = new Hand(flush());
            Hand fullHouse = new Hand(fullHouse());
            List<Hand> cards = asList(twoPair, highCard, flush, onePair, threeCard, fullHouse);

            // act
            List<Hand> strongOrder = cards.stream().sorted((c1, c2) -> {
                return c2.getScore() - c1.getScore();
            }).collect(toList()); // TODO 勝者決めを Gameなどどこかに配置し直す

            // assert
            assertIterableEquals(asList(fullHouse, flush, threeCard, twoPair, onePair, highCard),
                    strongOrder);
        }
    }

    @DisplayName("役名が取得できる")
    @Nested
    class getHandName {
        // TODO ストレートフラッシュ

        // TODO フォーカード
        @DisplayName("フルハウス")
        @Test
        void returnFullHousePair() {
            assertEquals("フルハウス", new Hand(fullHouse()).getName());
        }

        @DisplayName("フラッシュ")
        @Test
        void returnFlush() {
            assertEquals("フラッシュ", new Hand(flush()).getName());
        }

        // TODO ストレート

        @DisplayName("スリーカード")
        @Test
        void returnThreeCard() {
            assertEquals("スリーカード", new Hand(threeCard()).getName());
        }

        @DisplayName("ツーペア")
        @Test
        void returnTwoPair() {
            assertEquals("ツーペア", new Hand(twoPair()).getName());
        }

        @DisplayName("ワンペア")
        @Test
        void returnOnePair() {
            assertEquals("ワンペア", new Hand(onePair()).getName());
        }

        @DisplayName("ハイカード")
        @Test
        void returnHighCard() {
            assertEquals("ハイカード", new Hand(highCard()).getName());
        }
    }

    Card[] flush() {
        return new Card[]{
                new Card("♠", "3"),
                new Card("♠", "2"),
                new Card("♠", "A"),
                new Card("♠", "4"),
                new Card("♠", "9"),
        };
    }

    Card[] fullHouse() {
        return new Card[]{
                new Card("♠", "A"),
                new Card("♠", "2"),
                new Card("♦︎", "2"),
                new Card("♥ ", "A"),
                new Card("♦︎", "A"),
        };
    }

    Card[] threeCard() {
        return new Card[]{
                new Card("♠", "A"),
                new Card("♠", "2"),
                new Card("♦︎", "4"),
                new Card("♥ ", "A"),
                new Card("♦︎", "A"),
        };
    }


    Card[] twoPair() {
        return new Card[]{
                new Card("♠", "A"),
                new Card("♦︎", "A"),
                new Card("♠", "2"),
                new Card("♦︎", "2"),
                new Card("♠", "3"),
        };
    }

    Card[] onePair() {
        return new Card[]{
                new Card("♠", "A"),
                new Card("♦︎", "A"),
                new Card("♠", "2"),
                new Card("♠", "3"),
                new Card("♠", "4"),
        };
    }

    Card[] highCard() {
        return new Card[]{
                new Card("♠", "A"),
                new Card("♠", "2"),
                new Card("♠", "J"),
                new Card("♠", "Q"),
                new Card("♦︎", "K")};
    }
}
