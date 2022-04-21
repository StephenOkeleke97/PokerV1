package hand;

import java.util.List;
import card.Card;

/**
 * A class representing a three of a kind in a poker hand.
 * 
 * @author stephen
 *
 */
public class ThreeOfAKind extends HandImpl {
  /**
   * The power of this hand.
   */
  private final int primaryPower = 4;
  /**
   * The power of this hand in the event of a tie. In the event of a tie, the
   * highest ranking three of a kind wins. If still tied, the highest side card,
   * and then, the second-highest side card wins. Hence, the secondary power array
   * can contain only 3 items.
   */
  private int[] secondaryPower;

  /**
   * Creates an instance of this class.
   * 
   * @param handCards list of cards that make up hand
   */
  public ThreeOfAKind(List<Card> handCards) {
    super(handCards);
    this.secondaryPower = new int[] { handCards.get(0).getPowerOfCard(),
        handCards.get(3).getPowerOfCard(), handCards.get(4).getPowerOfCard() };
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
    return String.format("Three of a Kind, %ss: %s", name, handCards);
  }

}
