package ru.pokerbender.cntrlpojo;

import ru.pokerbender.Card;
import ru.pokerbender.Player;

public class PlayerInfo {
	private String name;
	private String guid;
	private int moneySum;
	private Card[] cards;
	private boolean dealler;
	private int place;
	private boolean inGame;
	private boolean doAction;
	
	public PlayerInfo(Player player) {
		this.name = player.getName();
		this.guid = player.getGuid();
		this.moneySum = player.getSum();
		this.cards = new Card[player.getCards().length];
		System.arraycopy(player.getCards(), 0, cards, 0, cards.length);
		this.dealler = player.isDealer();
		
	}

	
	public boolean isInGame() {
		return inGame;
	}


	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}


	public boolean isDoAction() {
		return doAction;
	}


	public void setDoAction(boolean doAction) {
		this.doAction = doAction;
	}


	public boolean isDealler() {
		return dealler;
	}

	public void setDealler(boolean dealler) {
		this.dealler = dealler;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public int getMoneySum() {
		return moneySum;
	}

	public void setMoneySum(int moneySum) {
		this.moneySum = moneySum;
	}

	public Card[] getCards() {
		return cards;
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}
	
	

}
