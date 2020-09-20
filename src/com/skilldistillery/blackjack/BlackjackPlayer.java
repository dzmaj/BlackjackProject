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
		String str = "";
		if (score == -1) {
			return "Bust!";
		}
		if (hand.isSoft()) {
			str += score;
		} else {
			if (score == -1) {
				str = "Bust!";
			} else if (score == 21) {
				str = "Blackjack!";
			} else {
				str += score;
			}
		}
		return str;
	}

	public int scoreHand() {
		int value = hand.getHandValue();
		if (value > 21) {
			return -1;
		} else {
			return value;
		}
	}

	public boolean canHit() {

		int value = hand.getHandValue();
		if (value > 21) {
			return false;
		} else if (value == 21) {
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
