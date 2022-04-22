package hand;

import java.util.List;
import card.Card;

/**
 * A class representing a high card in a poker hand.
 * 
 * @author stephen
 *
 */
public class HighCard extends HandImpl {
  /**
   * The power of this hand.
   */
  private final int primaryPower = 1;
  /**
   * The power of this hand in the event of a tie. In the event of a tie, highest
   * card wins, and if necessary, the second-highest, third-highest,
   * fourth-highest and smallest card can be used to break the tie. Hence, the
   * secondary power array can contain only 5 items.
   */
  private int[] secondaryPower;

  /**
   * Creates an instance of this class.
   * 
   * @param handCards list of cards in hand
   */
  public HighCard(List<Card> handCards) {
    super(handCards);
    this.secondaryPower = handCards.stream().mapToInt(card -> card.getPowerOfCard()).toArray();
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
    return String.format("High Card, %s: %s", name, handCards);
  }
}
