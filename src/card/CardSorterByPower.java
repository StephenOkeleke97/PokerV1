package card;

import java.util.Comparator;

/**
 * Sorts card in decreasing order of power.
 * 
 * @author stephen
 *
 */
public class CardSorterByPower implements Comparator<Card>{

  @Override
  public int compare(Card o1, Card o2) {
    return o2.getPowerOfCard() - o1.getPowerOfCard();
  }

}
