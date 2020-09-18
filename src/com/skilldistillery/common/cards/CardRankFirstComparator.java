package com.skilldistillery.common.cards;

import java.util.Comparator;

public class CardRankFirstComparator implements Comparator<Card> {

	@Override
	public int compare(Card o1, Card o2) {
		if (o1.getRank().ordinal() > o2.getRank().ordinal()) {
			return 1;
		} else if (o1.getRank().ordinal() < o2.getRank().ordinal()) {
			return -1;
		} else if (o1.getRank().ordinal() == o2.getRank().ordinal()) {
			if (o1.getSuit().ordinal() > o2.getSuit().ordinal()) {
				return 1;
			}
			if (o1.getSuit().ordinal() < o2.getSuit().ordinal()) {
				return -1;
			} else if (o1.getSuit().ordinal() == o2.getSuit().ordinal()) {
				return 0;
			}
		}
		return 0;
	}
}
