package com.skilldistillery.common.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public abstract class Game {
	protected Dealer dealer;
	protected List<Player> playersList;
	protected Map<Player, Integer> scoresMap;
	protected Scanner sc;
	public Game(Scanner scanner) {
		playersList = new ArrayList<>();
		scoresMap = new HashMap<>();
		sc = scanner;
	}
	
	public void removePlayer(Player p) {
		playersList.remove(p);
		scoresMap.remove(p);
	}
	
	public void addPlayer(Player p) {
		playersList.add(p);
		scoresMap.put(p, 0);
	}
	public int getScore(Player p) {
		if (scoresMap.containsKey(p)) {
			return scoresMap.get(p);
		} else {
			return -999;
		}
	}
	public void addScore(Player p, int score) {
		if (scoresMap.containsKey(p)) {
			Integer newScore = scoresMap.get(p) + score;
			scoresMap.put(p, newScore);
		}
	}
	
}
