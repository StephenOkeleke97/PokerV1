package handsolver;

import java.util.List;

import card.Card;
import hand.Hand;

/**
 * Represents a poker hand.
 * 
 * @author stephen
 *
 */
public interface HandSolver {
  /**
   * Get hand from a list of cards.
   *  
   * @param cards list of cards from which hand is evaluated
   * @return hand instance 
   * @throws IllegalArgumentException if cards is null or 
   * the length of cards is not equal to 7
   */
  Hand solveHand(List<Card> cards) throws IllegalArgumentException;
  
  /**
   * Ranks hands from most powerful to least powerful.
   * 
   * @param hands hands to be ranked
   * @return new list object of hands with respect to rank
   */
  List<Hand> rankHands(List<Hand> hands);
  
  /**
   * Filter our list of hand for the strongest hand(s).
   * 
   * @param hands list of hands
   * @return winning hand(s)
   */
  List<Hand> getWinner(List<Hand> hands);
}
