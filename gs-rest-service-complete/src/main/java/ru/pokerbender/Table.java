package ru.pokerbender;

import java.util.LinkedList;
import java.util.List;

import hello.Application;


public class Table {
	public enum PLAYERS_ACTION{
		Support,
		PickUp,
		Skip,
		Pass
	}
	
	public enum GAME_STAGE{
		Preflop,
		Flop,
		Turn,
		River,
		ShowDown
	}
	private Player[] players = new Player[6];
	private int tableSum;
	private List<Card> cards = new LinkedList();
	private int currentPlayerIndx = -1;
	private boolean started;
	private Deck deck;
	private LinkedList<Player> currentGamePlrs = new LinkedList();
	private int minRate = 10;
	private int lastRate = minRate;
	private Player dealler;
	private int roundNum;
	private GAME_STAGE gameStage;
	
	public int getMinRate() {
		return minRate;
	}
	public void setMinRate(int minRate) {
		this.minRate = minRate;
	}
	public Player[] getPlayers() {
		return players;
	}
	public void setPlayers(Player[] players) {
		this.players = players;
	}
	public int getTableSum() {
		return tableSum;
	}
	public void setTableSum(int tableSum) {
		this.tableSum = tableSum;
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
	
	public void addPlayer(int place, Player player){
		if (player==null){
			throw new IllegalArgumentException("Игрок не указан");
		}
		if ((place<0) | (place>5)){
			throw new IllegalArgumentException("Нет такого места");
		}
		for (Player p:players){
			if ((p!=null) && (p.equals(player))){
				throw new IllegalArgumentException("Игрок уже сидит за столом");				
			}
		}
	}
	
	
	public void startGame(){
		if (getPlayersCount()<2){
			throw new IllegalArgumentException("Недостаточно игроков");
		}
		
		started = true;
	}
	
	public void distribution(){
		if (!started){
			throw new IllegalArgumentException("Игра не запушена");
		}
		cards.clear();
		deck = (Deck) Application.ac.getBean("deck");
		tableSum = 0;
		currentGamePlrs.clear();
		for(Player p : players){
			if (p!=null){
				currentGamePlrs.add(p);
			}
		}
		
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
		
		tableSum += dealler.support(minRate);
		
		
	}
	
	public void end(){
		gameStage = GAME_STAGE.ShowDown;
		Player winner = getWiner();
		winner.incSum(tableSum);
		tableSum = 0;
		
		
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
					tableSum += p.support(sum);
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
					tableSum += p.pickUp(sum);
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
	
	private int getPlayersCount(){
		int i = 0;
		for (Player p:players){
			if (p!=null){
				i++;
			}
		}
		return i;
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
