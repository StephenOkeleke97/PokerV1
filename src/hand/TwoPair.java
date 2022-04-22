package hand;

import java.util.List;
import card.Card;

/**
 * A class representing a two pair in a poker hand.
 * 
 * @author stephen
 *
 */
public class TwoPair extends HandImpl {

  /**
   * The power of this hand.
   */
  private final int primaryPower = 3;
  /**
   * The power of this hand in the event of a tie. In the event of a tie, the
   * highest pair wins. If still tied, the highest second pair wins, then the
   * highest side card wins. Hence, the secondary power array can contain only 3
   * items.
   */
  private int[] secondaryPower;

  /**
   * Creates an instance of this class.
   * 
   * @param handCards list of cards that make up hand
   */
  public TwoPair(List<Card> handCards) {
    super(handCards);
    this.secondaryPower = new int[] { handCards.get(0).getPowerOfCard(),
        handCards.get(2).getPowerOfCard(), handCards.get(4).getPowerOfCard() };
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
    String firstPair = handCards.get(0).getRank();
    String secondPair = handCards.get(2).getRank();
    return String.format("Two Pair, %ss and %ss: %s", firstPair, secondPair, handCards);
  }
}
