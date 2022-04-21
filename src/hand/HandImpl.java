package hand;

import java.util.ArrayList;
import java.util.List;

import card.Card;

/**
 * Abstract class that represents a poker hand.
 * 
 * @author stephen
 *
 */
public abstract class HandImpl implements Hand {

  protected List<Card> handCards;
  
  /**
   * Creates an instance of a hand class.
   */
  public HandImpl() {
    
  }
  

  /**
   * Creates an instance of a hand class.
   * 
   * @param card list of cards that make up hand
   */
  public HandImpl(List<Card> handCards) {
    if (handCards.size() != 5)
      throw new IllegalArgumentException("A hand must have exactly 5 cards");
    this.handCards = handCards;
  }
  
  @Override
  public List<Card> getHandCards() {
    return new ArrayList<>(this.handCards);
  }

  @Override
  public int compareTo(Hand o) {
    if (this.getPrimaryPower() == o.getPrimaryPower()) {
      for (int i = 0; i < this.getSecondaryPower().length; i++) {
        if (this.getSecondaryPower()[i] != o.getSecondaryPower()[i])
          return o.getSecondaryPower()[i] - this.getSecondaryPower()[i];
      }
    }
    return o.getPrimaryPower() - this.getPrimaryPower();
  }
}
