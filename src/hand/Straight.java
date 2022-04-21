package hand;

import java.util.List;
import card.Card;

/**
 * A class representing a straight in a poker hand.
 * 
 * @author stephen
 *
 */
public class Straight extends HandImpl {

  /**
   * The power of this hand.
   */
  private final int primaryPower = 5;
  /**
   * The power of this hand in the event of a tie. The tie breaker is the power of
   * the top card in the straight. Hence, the secondary power array can contain
   * only one item.
   */
  private int[] secondaryPower;

  /**
   * Creates an instance of this class.
   * 
   * @param handCards list of cards that make up hand
   */
  public Straight(List<Card> handCards) {
    super(handCards);
    this.secondaryPower = new int[] { handCards.get(0).getPowerOfCard() };
  }

  @Override
  public int getPrimaryPower() {
    return primaryPower;
  }

  @Override
  public int[] getSecondaryPower() {
    return secondaryPower.clone();
  }

  public String toString() {
    String name = handCards.get(0).getRank();
    return String.format("%s-High Straight: %s", name, handCards);
  }
}
