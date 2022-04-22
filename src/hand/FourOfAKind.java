package hand;

import java.util.List;
import card.Card;

/**
 * A class representing a four of a kind in a poker hand.
 * 
 * @author stephen
 *
 */
public class FourOfAKind extends HandImpl {
  /**
   * The power of this hand.
   */
  private final int primaryPower = 8;
  /**
   * The power of this hand in the event of a tie. The tie breakers are the power
   * of the four followed by the last card in the hand. Hence, the secondary power
   * array will contain only the power of the four as its' first element, and the
   * power of the last card as its' second.
   */
  private int[] secondaryPower;

  /**
   * Creates an instance of this class.
   * 
   * @param handCards list of cards that make up hand
   */
  public FourOfAKind(List<Card> handCards) {
    super(handCards);
    this.secondaryPower = new int[] { handCards.get(0).getPowerOfCard(),
        handCards.get(4).getPowerOfCard() };
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
    return String.format("Four of a Kind, %ss: %s", name, handCards);
  }
}
