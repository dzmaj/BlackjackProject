package com.skilldistillery.common.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	List<Card> deck;
	
	public Deck() {
		deck = new ArrayList<>(52);
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(suit, rank));
			}
		}
	}
	
	public int checkDeckSize() {
		return deck.size();
	}
	public Card dealCard(int position) {
		return deck.remove(position);
	}
	public Card dealCard() {
		return dealCard(0);
	}
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
}
