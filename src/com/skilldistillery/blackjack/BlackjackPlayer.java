package com.skilldistillery.blackjack;

import com.skilldistillery.common.cards.*;

public class BlackjackPlayer extends Player {
	private BlackjackHand hand;
	private boolean dealer;
	private boolean hasDoubled;

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
		if (score <= 0) {
			return "Bust!";
		}
		if (hand.isSoft()) {
			str += score;
		} else {
			if (score <= 0) {
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
			// Players who busted should still lose to dealer who busts afterwards
			if (this.dealer) {
				return 0;
			}
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

	public boolean hasDoubled() {
		return hasDoubled;
	}
	public void setHasDoubled(boolean bool) {
		hasDoubled = bool;
	}

}
