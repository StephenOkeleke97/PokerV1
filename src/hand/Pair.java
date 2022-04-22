package hand;

import java.util.List;
import card.Card;

/**
 * A class representing a pair in a poker hand.
 * 
 * @author stephen
 *
 */
public class Pair extends HandImpl {
  /**
   * The power of this hand.
   */
  private final int primaryPower = 2;
  /**
   * The power of this hand in the event of a tie. In the event of a tie, the
   * highest pair wins. If still tied, the highest side card wins, and if
   * necessary, the second-highest and third-highest side card can be used to
   * break the tie. Hence, the secondary power array can contain only four items.
   */
  private int[] secondaryPower;

  /**
   * Creates an instance of this class.
   * 
   * @param handCards list of cards that make up hand
   */
  public Pair(List<Card> handCards) {
    super(handCards);
    this.secondaryPower = new int[] { handCards.get(0).getPowerOfCard(),
        handCards.get(2).getPowerOfCard(), handCards.get(3).getPowerOfCard(),
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
    return String.format("One Pair, %ss: %s", name, handCards);
  }
}
