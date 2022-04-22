package hand;

import java.util.List;
import card.Card;

/**
 * A class representing a straight flush in a
 * poker hand. 
 * 
 * @author stephen
 *
 */
public class StraightFlush extends HandImpl {
  /**
   * The power of this hand.
   */
  private final int primaryPower = 9;
  /**
   * The power of this hand in the event of
   * a tie. The highest ranking top card wins. Hence,
   * this array can contain only one element which
   * is the highest ranking top card.
   */
  private int[] secondaryPower;
  
  /**
   * Creates an instance of this class.
   * 
   * @param handCards list of cards that make up hand
   */
  public StraightFlush(List<Card> handCards) {
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
    String name;
    if (handCards.get(0).getPowerOfCard() == 14) name = "Royal Flush";
    else name = handCards.get(0).getRank() + "-High Straight Flush";
    return String.format("%s: %s", name, handCards);
  }
}
