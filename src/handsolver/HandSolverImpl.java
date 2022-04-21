package handsolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;
import card.Card;
import card.CardImpl;
import card.CardSorterByNumber;
import card.CardSorterByPower;
import card.CardType;
import hand.Flush;
import hand.FourOfAKind;
import hand.FullHouse;
import hand.Hand;
import hand.HighCard;
import hand.Pair;
import hand.Straight;
import hand.StraightFlush;
import hand.ThreeOfAKind;
import hand.TwoPair;

/**
 * Represents a poker hand.
 * 
 * @author stephen
 *
 */
public class HandSolverImpl implements HandSolver {

  /**
   * Creates an instance of this class.
   */
  public HandSolverImpl() {

  }

  @Override
  public Hand solveHand(List<Card> cards) {
    if (cards == null || cards.size() != 7) {
      throw new IllegalArgumentException("Invalid arguments");
    }

    List<Card> toSolve = new ArrayList<>(cards);

    if (isStraightFlush(toSolve) != null)
      return isStraightFlush(toSolve);
    else if (isFourOfAKind(toSolve) != null)
      return isFourOfAKind(toSolve);
    else if (isFullHouse(toSolve) != null)
      return isFullHouse(toSolve);
    else if (isFlush(toSolve, true) != null)
      return isFlush(toSolve, true);
    else if (isStraight(toSolve) != null)
      return isStraight(toSolve);
    else if (isThreeOfAKind(toSolve) != null)
      return isThreeOfAKind(toSolve);
    else if (isTwoPair(toSolve) != null)
      return isTwoPair(toSolve);
    else if (isPair(toSolve) != null)
      return isPair(toSolve);
    else
      return isHighCard(toSolve);
  }

  @Override
  public List<Hand> rankHands(List<Hand> hands) {
    List<Hand> ranked = new ArrayList<>(hands);
    Collections.sort(ranked);
    return ranked;
  }

  @Override
  public List<Hand> getWinner(List<Hand> hands) {
    List<Hand> ranked = new ArrayList<>(hands);
    Collections.sort(ranked);

    int maxHand = ranked.get(0).getPrimaryPower();
    List<Hand> primaryFilter = ranked.stream().filter(rank -> rank.getPrimaryPower() >= maxHand)
        .collect(Collectors.toList());

    List<Hand> secondaryFilter = new ArrayList<>(primaryFilter);

    for (int i = 1; i < primaryFilter.size(); i++) {
      if (primaryFilter.get(i).compareTo(primaryFilter.get(0)) > 0) {
        secondaryFilter.remove(primaryFilter.get(i));
      }
    }
    return secondaryFilter;
  }

  /**
   * Check if a hand is a flush first. Then check if flush cards form a straight.
   * If yes, the hand is a straight flush.
   * 
   * @param hand hand of cards
   * @return straight flush or null if no match
   */
  private Hand isStraightFlush(List<Card> hand) {
    Hand flush = isFlush(hand, false);
    if (flush == null)
      return null;
    Hand straight = isStraight(flush.getHandCards());

    if (straight == null)
      return null;
    return new StraightFlush(straight.getHandCards());
  }

  /**
   * Get map of power to cards. If the size of the first element cards list is not
   * 4, then it is not a four of a kind.
   * 
   * @param hand hand of cards
   * @return four of a kind or null if no card matches
   */
  private Hand isFourOfAKind(List<Card> hand) {
    Map<Integer, List<Card>> numOfOccurences = getMapOfPowerToCards(hand);
    List<Integer> keysList = new ArrayList<>(numOfOccurences.keySet());

    List<Card> firstElement = numOfOccurences.get(keysList.get(0));
    if (firstElement.size() != 4)
      return null;

    List<Card> result = addFirstFiveCardsInMapToList(numOfOccurences);
    return new FourOfAKind(result);
  }

  /**
   * Get map of power to cards. If the size of the first element cards list is not
   * 3 or the size of second element is not 2, then it is not a full house.
   * 
   * @param hand hand of cards
   * @return full house or null if no card matches
   */
  private Hand isFullHouse(List<Card> hand) {
    Map<Integer, List<Card>> numOfOccurences = getMapOfPowerToCards(hand);
    List<Integer> keysList = new ArrayList<>(numOfOccurences.keySet());

    List<Card> firstElement = numOfOccurences.get(keysList.get(0));
    List<Card> secondElement = numOfOccurences.get(keysList.get(1));

    if (firstElement.size() != 3 || secondElement.size() != 2)
      return null;

    List<Card> result = addFirstFiveCardsInMapToList(numOfOccurences);
    return new FullHouse(result);
  }

  /**
   * Map the type of cards to a list of cards belonging to that type. If a type
   * has 5 or more cards in its list, then it is a flush. Sort the list in
   * descending order of power and return the first 5 cards of the flush type.
   * 
   * @param hand hand of cards
   * @param trim if true return no more than 5 cards else return up to 7
   * @return flush or null if no card matches
   */
  private Hand isFlush(List<Card> hand, boolean trim) {
    Map<CardType, List<Card>> numOfOccurences = new HashMap<>();
    for (Card card : hand) {
      if (numOfOccurences.get(card.getType()) == null) {
        List<Card> newCardList = new ArrayList<>();
        newCardList.add(card);
        numOfOccurences.put(card.getType(), newCardList);
      } else {
        numOfOccurences.get(card.getType()).add(card);
      }
    }

    for (Entry<CardType, List<Card>> entry : numOfOccurences.entrySet()) {
      if (entry.getValue().size() >= 5) {
        sortHandByPower(entry.getValue());
        if (trim) {
          return new Flush(entry.getValue().subList(0, 5));
        } else {
          return new Flush(entry.getValue());
        }
      }
    }
    return null;
  }

  /**
   * Sort hand according to number. Map card to card number and get the last
   * element. If last element is an ace, add a new ace card with number 14 to the
   * front of hand. This will help us differentiate a high straight from a low
   * straight.
   * 
   * @param hand hand of cards
   * @return straight or null if no card matches
   */
  private Hand isStraight(List<Card> hand) {
    hand = new ArrayList<>(hand);
    sortHandByNumber(hand);
    List<Integer> handNumbers = hand.stream().map(card -> card.getNumberOnCard())
        .collect(Collectors.toList());
    List<Card> result = new ArrayList<>();

    int lastCard = handNumbers.get(handNumbers.size() - 1);

    if (lastCard == 1) {
      CardType type = hand.get(hand.size() - 1).getType();
      hand.add(0, new CardImpl(14, type));
    }
    result.add(hand.get(0));
    for (int i = 1; i < hand.size(); i++) {
      if (hand.get(i).getNumberOnCard() == hand.get(i - 1).getNumberOnCard() - 1) {
        result.add(hand.get(i));
        if (result.size() >= 5) {
          return new Straight(result);
        }
      } else {
        result.clear();
        result.add(hand.get(i));
      }
    }

    return null;
  }

  /**
   * Get map of power to cards. If the size of the first element cards list is not
   * 3, then it is not a three of a kind.
   * 
   * @param hand hand of cards
   * @return three of a kind or null if no card matches
   */
  private Hand isThreeOfAKind(List<Card> hand) {
    Map<Integer, List<Card>> numOfOccurences = getMapOfPowerToCards(hand);
    List<Integer> keysList = new ArrayList<>(numOfOccurences.keySet());

    List<Card> firstElement = numOfOccurences.get(keysList.get(0));
    if (firstElement.size() != 3)
      return null;

    List<Card> result = addFirstFiveCardsInMapToList(numOfOccurences);
    return new ThreeOfAKind(result);
  }

  /**
   * Get map of power to cards. If the size of the first element cards list is not
   * 2 or the size of second element is not 2, then it is not a two pair.
   * 
   * @param hand hand of cards
   * @return two pair or null if no match
   */
  private Hand isTwoPair(List<Card> hand) {
    Map<Integer, List<Card>> numOfOccurences = getMapOfPowerToCards(hand);
    List<Integer> keysList = new ArrayList<>(numOfOccurences.keySet());

    List<Card> firstElement = numOfOccurences.get(keysList.get(0));
    List<Card> secondElement = numOfOccurences.get(keysList.get(1));

    if (firstElement.size() != 2 || secondElement.size() != 2)
      return null;

    List<Card> result = addFirstFiveCardsInMapToList(numOfOccurences);
    return new TwoPair(result);
  }

  /**
   * Get map of power to cards. If the size of the first element cards list is not
   * 2 then it is not a pair.
   * 
   * @param hand hand of cards
   * @return pair or null if no match
   */
  private Hand isPair(List<Card> hand) {
    Map<Integer, List<Card>> numOfOccurences = getMapOfPowerToCards(hand);
    List<Integer> keysList = new ArrayList<>(numOfOccurences.keySet());

    List<Card> firstElement = numOfOccurences.get(keysList.get(0));

    if (firstElement.size() != 2)
      return null;

    List<Card> result = addFirstFiveCardsInMapToList(numOfOccurences);
    return new Pair(result);
  }

  /**
   * Sort hand in descending order of power and return list of first five cards.
   * 
   * @param hand hand of cards
   * @return high card or null if no match
   */
  private Hand isHighCard(List<Card> hand) {
    sortHandByPower(hand);
    List<Card> result = hand.subList(0, 5);
    return new HighCard(result);
  }

  /**
   * Takes a map with Integer key and List of Card value. Iterates through the map
   * and add the cards in list of each entry to a result list while the size of
   * the result list is less than 5.
   * 
   * @param numOfOccurences map of power to list of cards with power
   * @return list of cards
   */
  private List<Card> addFirstFiveCardsInMapToList(Map<Integer, List<Card>> numOfOccurences) {
    List<Card> result = new ArrayList<>();

    main: for (Entry<Integer, List<Card>> entry : numOfOccurences.entrySet()) {
      for (Card card : entry.getValue()) {
        if (result.size() < 5)
          result.add(card);
        else
          break main;
      }
    }

    return result;
  }

  /**
   * Maps power of card to a list of cards that have that power. Sorts the map in
   * descending order of power, and then sorts map again in descending order of
   * size of cards in value (of map). The order is maintained using a linked
   * hashmap.
   * 
   * @param hand hand of cards
   * @return map of power to cards
   */
  private Map<Integer, List<Card>> getMapOfPowerToCards(List<Card> hand) {
    Map<Integer, List<Card>> numOfOccurences = getReverseSortedMap();

    for (Card card : hand) {
      if (numOfOccurences.get(card.getPowerOfCard()) == null) {
        List<Card> cardsOfNum = new ArrayList<>();
        cardsOfNum.add(card);
        numOfOccurences.put(card.getPowerOfCard(), cardsOfNum);
      } else {
        numOfOccurences.get(card.getPowerOfCard()).add(card);
      }
    }

    numOfOccurences = sortMapByNumOfOccurences(numOfOccurences);
    return numOfOccurences;
  }

  /**
   * Sort cards in descending order of number.
   * 
   * @param hand cards to sort
   */
  private void sortHandByNumber(List<Card> hand) {
    Collections.sort(hand, new CardSorterByNumber());
  }

  /**
   * Sort cards in descending order of power.
   * 
   * @param hand cards to sort
   */
  private void sortHandByPower(List<Card> hand) {
    Collections.sort(hand, new CardSorterByPower());
  }

  /**
   * Sort the keys of a map in reverse or decreasing order. In this case, the key
   * of the map is the power of a card.
   * 
   * @return TreeMap that maintains desired order when elements are inserted into
   *         it
   */
  private Map<Integer, List<Card>> getReverseSortedMap() {
    Comparator<Integer> reverseSort = new Comparator<Integer>() {

      @Override
      public int compare(Integer o1, Integer o2) {
        return o2 - o1;
      }
    };
    return new TreeMap<>(reverseSort);
  }

  /**
   * Sorts a map whose value is a list according to the size of that list. Returns
   * a linked hash map to maintain whatever prior order the map had before
   * sorting.
   * 
   * @param map map to sort
   * @return sorted map
   */
  private Map<Integer, List<Card>> sortMapByNumOfOccurences(Map<Integer, List<Card>> map) {
    Comparator<List<Card>> compareListSize = new Comparator<List<Card>>() {

      @Override
      public int compare(List<Card> o1, List<Card> o2) {
        return o2.size() - o1.size();
      }
    };

    Map<Integer, List<Card>> sorted = map.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(compareListSize)).collect(
            Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    return sorted;
  }
}
