package com.skilldistillery.blackjack;

import com.skilldistillery.common.cards.*;

public class BlackjackHand extends Hand {
	@Override
	public int getHandValue() {
		int value = getSoftValue();
		return value;
	}
	
	// score if aces are 11
	public int getBaseHandValue() {
		int value = 0;
		for (Card card : cards) {
			value += card.getRankValue();
		}
		return value;
	}
	
	
	public int numAces() {
		int numAces = 0;
		for (Card card : cards) {
			if (card.getRank().getValue() == 11) {
				numAces++;
			}
		}
		return numAces;
	}
	
	public boolean isSoft() {
		if (numAces() == 0) {
			return false;
		} else if (getBaseHandValue() == 21) {
			return false;
		}
		return true;
	}
	
	// get true score
	// TODO: clean up redundant logic
	public int getSoftValue() {
		int hardValue = getBaseHandValue();
		int value = hardValue;
		// if no aces then normal value
		if (!isSoft()) {
			return hardValue;
		}
		// if blackjack then already best case
		if (hardValue == 21) {
			return hardValue;
		}
		// if bust otherwise:
		else {
			int numLowAces = 0; // number of aces "used"
			while(value > 21 && numLowAces < numAces()) { // as long as still have an ace to use low value and we need to use it
				value -= 10; // subtract 10
				numLowAces++; // use up one more ace
			}
		}
		int difHard = 21 - hardValue;
		int difSoft = 21 - value;
		boolean useHard = ((hardValue <= 21) && (difHard < difSoft));
		if (useHard) {
			return hardValue;
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
