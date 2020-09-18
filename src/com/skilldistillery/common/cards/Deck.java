package com.skilldistillery.common.cards;

import java.util.ArrayList;

public class Deck extends CardGroup {
	
	public Deck() {
		cards = new ArrayList<>(52);
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(suit, rank));
			}
		}
	}

}
