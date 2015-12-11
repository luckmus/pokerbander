package ru.pokerbender;

import java.util.LinkedList;
import java.util.List;

import hello.Application;
import ru.pokerbender.Table.GAME_STAGE;
import ru.pokerbender.Table.PLAYERS_ACTION;

public class Game {
	private LinkedList<Player> currentGamePlrs = new LinkedList();
	private int lastRate;
	private int minRate;
	private Player dealler;
	private int roundNum;
	private GAME_STAGE gameStage;
	private int gameSum;
	private int currentPlayerIndx = -1;
	private Deck deck;
	private List<Card> cards = new LinkedList();

	public Game() {
		// TODO Auto-generated constructor stub
	}
	
	
	public int getLastRate() {
		return lastRate;
	}


	public GAME_STAGE getGameStage() {
		return gameStage;
	}


	public void setDeck(Deck deck) {
		this.deck = deck;
	}


	public LinkedList<Player> getCurrentGamePlrs() {
		return currentGamePlrs;
	}


	public void setCurrentGamePlrs(LinkedList<Player> currentGamePlrs) {
		this.currentGamePlrs = currentGamePlrs;
	}


	
	public int getGameSum() {
		return gameSum;
	}
	
	public void setGameSum(int tableSum) {
		this.gameSum = tableSum;
	}
	
	public List<Card> getCards() {
		List<Card> res = new LinkedList();
		res.addAll(cards);
		return res;
	}
	
	public void setCards(List<Card> cards) {
		this.cards.clear();
		this.cards.addAll(cards);
	}

	
	
	public void distribution(){

		gameSum = 0;		
		
		if (currentPlayerIndx==-1){
			getCurrentPlayer().setDealer(true);
			dealler = getCurrentPlayer();
		}else{
			getCurrentPlayer().setDealer(false);
			dealler = getNextPlayer();
			dealler.setDealer(true);
		}
		lastRate = minRate;
		gameStage = GAME_STAGE.Preflop;
		
		gameSum += dealler.support(minRate);
		for(Player p:currentGamePlrs){
			p.addCard(deck.getNextCard());
			p.addCard(deck.getNextCard());
		}
		getFirstCards();
		
		
	}

	
	public void end(){
		gameStage = GAME_STAGE.ShowDown;
		Player winner = getWiner();
		winner.incSum(gameSum);
		gameSum = 0;
		
		
	}

	
	public Player getWiner(){
		//TODO: реализация getWiner
		return null;
	}
	
	
	public void action(Player p, PLAYERS_ACTION action, int sum){
		if (!getCurrentPlayer().equals(p)){
			throw new IllegalArgumentException("Дождись своей очереди!");
		}
		
		switch(action){
			case Pass:
				currentGamePlrs.remove(currentPlayerIndx);
				if (currentGamePlrs.size()==1){
					end();
				}
				else{
					if (currentPlayerIndx>=(currentGamePlrs.size()-1)){
						currentPlayerIndx = 0;						
					}
					else{
						currentPlayerIndx++;
					}
				}
			break;
			case Skip:
			break;
			case Support:
				if (sum<lastRate){
					throw new IllegalArgumentException("Недостаточно денег");
				} else if (sum>lastRate){
					throw new IllegalArgumentException("это повышение а не поддержание");
				}
				else{
					gameSum += p.support(sum);
					lastRate = sum;				
				}
				if (currentPlayerIsLast()){
					nextRound();
					return;
				}
				
			break;
			case PickUp:
				if (sum<lastRate){
					throw new IllegalArgumentException("Недостаточно денег");
				}
				else{
					gameSum += p.pickUp(sum);
					lastRate = sum;
				}
			break;
		}
		
		//TODO: проверка окончания круга		
		//проверка окончания круга
		
		getNextPlayer();
	}

	
	public void nextRound(){
		getFirstPlayer();
		switch(gameStage){
			case Flop:
				getFourthCard();
			break;
			case Turn:
				getFifthCard();
			break;
			case River:
				end();
			break;
		}
	}

	
	private boolean currentPlayerIsLast(){
		return (currentGamePlrs.getLast().equals(getCurrentPlayer()));
	}

	
	public List<Card> getFirstCards(){
		roundNum = 0;
		gameStage = GAME_STAGE.Flop;
		cards.add(deck.getNextCard());
		cards.add(deck.getNextCard());
		cards.add(deck.getNextCard());
		return getCards();
	}

	
	public List<Card> getFourthCard(){
		gameStage = GAME_STAGE.Turn;
		roundNum = 0;
		cards.add(deck.getNextCard());
		return getCards();
	}

	
	public List<Card> getFifthCard(){
		gameStage = GAME_STAGE.River;
		roundNum = 0;
		cards.add(deck.getNextCard());
		return getCards();
	}
	


	
	public int getFirstPlayerIndx(){
		currentPlayerIndx=0;
		return currentPlayerIndx;
	}

	
	private int getCurrentPlayerIndx(){
		if (currentPlayerIndx==-1){
			currentPlayerIndx=0;
		}
		return currentPlayerIndx;
	}

	
	private int getNextPlayerIndx(){
		if (currentPlayerIndx==-1){
			return getCurrentPlayerIndx();
		}
		else if (currentPlayerIndx==(currentGamePlrs.size()-1)){
			currentPlayerIndx = 0;
		}
		else{
			currentPlayerIndx++;
		}
		return currentPlayerIndx;
	}

	
	public Player getCurrentPlayer(){
		return currentGamePlrs.get(getCurrentPlayerIndx());
	}

	
	public Player getNextPlayer(){
		return currentGamePlrs.get(getNextPlayerIndx());
	}
	
	
	public Player getFirstPlayer(){
		return currentGamePlrs.get(getFirstPlayerIndx());
		
	}


}
 