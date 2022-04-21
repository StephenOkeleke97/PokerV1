package hand;

import java.util.ArrayList;
import java.util.List;
import card.Card;

/**
 * A class representing a flush in a poker hand.
 * 
 * @author stephen
 *
 */
public class Flush extends HandImpl {

  /**
   * The power of this hand.
   */
  private final int primaryPower = 6;
  /**
   * The power of this hand in the event of a tie. In the event of a tie, The
   * highest ranked card wins. If still tied, the second-highest, third-highest,
   * fourth-highest, and fifth-highest cards can be used to break the tie. Hence
   * the secondary power array must contain 5 items.
   */
  private int[] secondaryPower;
  private List<Card> handCards;

  /**
   * Creates an instance of this class.
   * 
   * @param card list of cards that make up hand
   */
  public Flush(List<Card> handCards) {
    this.handCards = handCards;
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
  
  @Override
  public List<Card> getHandCards() {
    return new ArrayList<>(this.handCards);
  }

  public String toString() {
    String name = handCards.get(0).getRank();
    return String.format("%s-High Flush: %s", name, handCards);
  } 
}
