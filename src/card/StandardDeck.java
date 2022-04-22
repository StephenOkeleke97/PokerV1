package card;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a standard deck of cards.
 * 
 * @author stephen
 *
 */
public class StandardDeck implements CardDeck {
  
  /**
   * Creates an instance of this class.
   */
  public StandardDeck() {
    
  }

  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      for (int j = 1; j < 14; j++) {
        if (i == 0) {
          deck.add(new CardImpl(j, CardType.CLUBS));
        } else if (i == 1) {
          deck.add(new CardImpl(j, CardType.DIAMONDS));
        } else if (i == 2) {
          deck.add(new CardImpl(j, CardType.HEARTS));
        } else {
          deck.add(new CardImpl(j, CardType.SPADES));
        }
      }
    }
    return deck;
  }

}
