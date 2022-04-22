package card;

import java.util.Comparator;

/**
 * Used to solve a straight hand. Since an Ace
 * can make a low straight and a high straight, 
 * it's number as opposed to power is more
 * important.
 * 
 * @author stephen
 *
 */
public class CardSorterByNumber implements Comparator<Card>{

  @Override
  public int compare(Card o1, Card o2) {
    return o2.getNumberOnCard() - o1.getNumberOnCard();
  }

}
