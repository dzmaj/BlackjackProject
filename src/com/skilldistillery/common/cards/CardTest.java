package com.skilldistillery.common.cards;

import java.util.Scanner;

public class CardTest {
  public static void main(String[] args) {
//    Rank[] ranks = Rank.values();
//    for(int i=0; i<ranks.length; i++) {
//      System.out.println(ranks[i] + " " + ranks[i].getValue());
//    }
//    
//    for(Suit s : Suit.values()){
//      System.out.println(s.getSymbol());
//    }
	  CardTest ct = new CardTest();
	  ct.run();
  }
  
  public void run() {
	  Scanner sc = new Scanner(System.in);
	  Deck deck = new Deck();
	  deck.shuffle();
	  System.out.println("How many cards do you want?");
	  int numCards = sc.nextInt();
	  
	  for (int i = 0; i < numCards; i++) {
		  System.out.println(deck.dealCard());
	  }
	  System.out.println(Character.toChars(0x1f0a5));
  }
}
