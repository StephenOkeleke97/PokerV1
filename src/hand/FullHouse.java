package hand;

import java.util.List;
import card.Card;

/**
 * A class representing a full house in a poker hand.
 * 
 * @author stephen
 *
 */
public class FullHouse extends HandImpl {

  /**
   * The power of this hand.
   */
  private final int primaryPower = 7;
  /**
   * The power of this hand in the event of a tie. The tie breakers are the power
   * of the three followed by the power of the pair. Hence, the secondary power
   * array will contain only the power of the three as its' first element, and
   * then the power of the pair as its' second.
   */
  private int[] secondaryPower;

  /**
   * Creates an instance of this class.
   * 
   * @param handCards list of cards that make up hand
   */
  public FullHouse(List<Card> handCards) {
    super(handCards);
    this.secondaryPower = new int[] { handCards.get(0).getPowerOfCard(),
        handCards.get(3).getPowerOfCard() };
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
    String rank3s = handCards.get(0).getRank();
    String rank2s = handCards.get(3).getRank();
    return String.format("Full House, %ss over %ss: %s", rank3s, rank2s, handCards);
  }
}
