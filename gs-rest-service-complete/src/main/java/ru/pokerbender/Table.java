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
	private Game game;
	@Deprecated
	private int gameSum;
	@Deprecated
	private List<Card> cards = new LinkedList();
	@Deprecated
	private int currentPlayerIndx = -1;
	private boolean started;
	@Deprecated
	private Deck deck;
	@Deprecated
	private LinkedList<Player> currentGamePlrs = new LinkedList();
	private int minRate = 10;
	@Deprecated
	private int lastRate = minRate;
	@Deprecated
	private Player dealler;
	@Deprecated
	private int roundNum;
	@Deprecated
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
	
	
	public void startTable(){
		if (getPlayersCount()<2){
			throw new IllegalArgumentException("Недостаточно игроков");
		}
		
		started = true;
	}
	
	public void startGame(){
		if (!started){
			throw new IllegalArgumentException("Игра не запушена");
		}
		LinkedList<Player> currentGamePlrs = new LinkedList();
		for(Player p : players){
			if (p!=null){
				currentGamePlrs.add(p);
			}
		}
		game = (Game) Application.ac.getBean("game");
		game.distribution();
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

}
