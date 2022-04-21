package card;

import java.util.Objects;

import util.NumberInWords;

/**
 * Represents a card in a 52 playing card deck.
 * 
 * @author stephen
 *
 */
public class CardImpl implements Card {
  
  private int number;
  private CardType type;
  private int power;
  
  /**
   * Creates an instance of this class.
   * The number of the card. Special cards
   * like the Ace, Jack etc. should be mapped to
   * a number. For example the Ace will be 1 and the
   * Jack will be 11.
   * The power of the card is typically the number, however
   * an ace can have a power of 1 and 14.
   * 
   * @param number number on card
   * @param type type on card
   */
  public CardImpl(int number, CardType type) {
     this.number = number;
     this.type = type;
     if (number == 1) this.power = 14;
     else this.power = number;
  }

  @Override
  public int getNumberOnCard() {
    return number;
  }

  @Override
  public int getPowerOfCard() {
    return power;
  }

  @Override
  public CardType getType() {
    return type;
  }
  
  public String toString() {
    return String.format("%s of %s", getName(number), type);
  }
  
  /**
   * Get name of card from the number.
   * 
   * @param number number on card
   * @return name of card
   */
  private String getName(int number) {
    if (number == 1 || number == 14) return "Ace";
    else if (number == 11) return "Jack";
    else if (number == 12) return "Queen";
    else if (number == 13) return "King";
    else return String.valueOf(number);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, type);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof CardImpl)) {
      return false;
    }
    CardImpl other = (CardImpl) obj;
    return number == other.number && type == other.type;
  }

  @Override
  public String getRank() {
    if (number == 1 || number == 14) return "Ace";
    else if (number == 11) return "Jack";
    else if (number == 12) return "Queen";
    else if (number == 13) return "King";
    else return NumberInWords.getWords(number);
  }
}
