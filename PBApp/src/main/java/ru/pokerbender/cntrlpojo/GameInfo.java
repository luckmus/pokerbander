package ru.pokerbender.cntrlpojo;

import java.util.List;

import ru.pokerbender.Card;
import ru.pokerbender.Game;
import ru.pokerbender.Table.GAME_STAGE;

public class GameInfo {

	private int lastRate;
	private GAME_STAGE gameStage;
	private int gameSum;
	private List<Card> cards;
	
	public GameInfo(Game game) {
		this.lastRate = game.getLastRate();
		this.gameStage = game.getGameStage();
		this.gameSum = game.getGameSum();
		this.cards = (game.getCards());
	}

	public int getLastRate() {
		return lastRate;
	}

	public void setLastRate(int lastRate) {
		this.lastRate = lastRate;
	}

	public GAME_STAGE getGameStage() {
		return gameStage;
	}

	public void setGameStage(GAME_STAGE gameStage) {
		this.gameStage = gameStage;
	}

	public int getGameSum() {
		return gameSum;
	}

	public void setGameSum(int gameSum) {
		this.gameSum = gameSum;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	

}
