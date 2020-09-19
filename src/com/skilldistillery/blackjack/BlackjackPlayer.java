package com.skilldistillery.blackjack;

import com.skilldistillery.common.cards.*;

public class BlackjackPlayer extends Player {
	private BlackjackHand hand;
	private boolean dealer;
	
	

	public BlackjackPlayer(boolean isHuman, String name, boolean isDealer) {
		super(isHuman, name);
		dealer = isDealer;
		hand = new BlackjackHand();
		
	}
	
	public BlackjackHand getHand() {
		return hand;
	}
	
	public String getScoreString() {
		int score = scoreHand();
		if (score == -1) {
			return "Bust!";
		} else if (score == 21) {
			return "Blackjack!";
		} else {
			return "" + score;
		}
	}
	
	public int scoreHand() {
		if (hand.isBust()) {
			return -1;
		} else {
			return hand.getHandValue();
		}
	}

	public boolean canHit() {
		if (hand.isBust()) {
			return false;
		} else if (hand.isBlackjack()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isDealer() {
		return dealer;
	}

	public void setDealer(boolean dealer) {
		this.dealer = dealer;
	}
	
	
}
