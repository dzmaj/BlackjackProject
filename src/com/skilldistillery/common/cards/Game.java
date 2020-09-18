package com.skilldistillery.common.cards;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public abstract class Game {
	protected Map<Integer, Player> players;
	protected Map<Player, Integer> scores;
	public Game() {
		players = new TreeMap();
		scores = new HashMap();
	}
}
