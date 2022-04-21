package driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import card.Card;
import card.StandardDeck;
import hand.Hand;
import handsolver.HandSolver;
import handsolver.HandSolverImpl;

public class Driver {

  public static void main(String[] args) {
    HandSolver solver = new HandSolverImpl();
    List<Card> deck = new StandardDeck().getDeck();
    Collections.shuffle(deck);

    List<Card> p1 = new ArrayList<>();
    List<Card> p2 = new ArrayList<>();
    List<Card> p3 = new ArrayList<>();
    List<Card> p4 = new ArrayList<>();

    List<List<Card>> playerList = Arrays.asList(p1, p2, p3, p4);

    List<Card> community = new ArrayList<>();
    assignCards(deck, community, 5);

    playerList.forEach(player -> {
      player.addAll(community);
      assignCards(deck, player, 7);
    });

    System.out.println("COMMUNITY: " + community);
    System.out.println("PLAYER 1: " + p1);
    System.out.println("PLAYER 2: " + p2);
    System.out.println("PLAYER 3: " + p3);
    System.out.println("PLAYER 4: " + p4);

    List<Hand> hands = playerList.stream().map(playerCards -> solver.solveHand(playerCards))
        .collect(Collectors.toList());

    hands.forEach(hand -> {
      System.out.println(hand);
    });

    List<Hand> ranked = solver.rankHands(hands);
    System.out.println(ranked);
    
    List<Hand> winner = solver.getWinner(hands);
    System.out.println("WINNER(S): " + winner);
  }

  private static void assignCards(List<Card> deck, List<Card> assignee, int numberToAssign) {
    Random rand = new Random();
    while (assignee.size() < numberToAssign) {
      int random = rand.nextInt(deck.size());
      Card card = deck.get(random);
      deck.remove(card);
      assignee.add(card);
    }
  }

}
