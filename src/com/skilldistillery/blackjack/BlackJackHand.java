package com.skilldistillery.blackjack;

import com.skilldistillery.common.cards.Card;
import com.skilldistillery.common.cards.Hand;

public class BlackJackHand extends Hand {
	@Override
	public int getHandValue() {
		int value = 0;
		
		for (Card card : cards) {
			value += card.getRankValue();
		}
		
		return value;
	}
	
	public boolean isBlackjack() {
		if (getHandValue() == 21) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isBust() {
		if (getHandValue() > 21) {
			return true;
		}
		else {
			return false;
		}
	}
}
