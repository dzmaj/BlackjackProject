package com.skilldistillery.blackjack;

import com.skilldistillery.common.cards.Player;

public class BlackjackPlayer extends Player {
	public boolean hit() {
		return false;
	}
	public boolean Stay() {
		return false;
	}
	public void checkHand() {
		System.out.println(hand);
		return;
	}
}
