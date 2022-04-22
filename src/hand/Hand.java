package hand;

import java.util.List;

import card.Card;

/**
 * Interface that represents a poker card hand.
 * 
 * @author stephen
 *
 */
public interface Hand extends Comparable<Hand>{
  /**
   * Get the primary power of this hand.
   * 
   * @return primary power
   */
  int getPrimaryPower();

  /**
   * Get the secondary power of this hand.
   * 
   * @return secondary power
   */
  int[] getSecondaryPower();

  /**
   * Get the cards that make up this hand.
   * 
   * @return hand cards
   */
  List<Card> getHandCards();
}
