package card;

/**
 * Interface representing a contract for 
 * implementing a poker card.
 * 
 * @author stephen
 *
 */
public interface Card {
  /**
   * Get the number on a card. For example
   * a 5 of Hearts would return 5.
   * 
   * @return number on card
   */
  int getNumberOnCard();
  
  /**
   * Get power of a card. Ideally, the power of
   * a card would be the same as it's number, however, 
   * cards like the Ace can have a power of 1 and a power of 
   * 14. i.e It can form a high straight and low straight hand.
   * 
   * @return power of card
   */
  int getPowerOfCard();

  /**
   * Get the card type.
   * 
   * @return card type
   */
  CardType getType();
  
  /**
   * Get rank of card, such as ace, jack, ten.
   * 
   * @return name of card
   */
  String getRank();
}
