package ru.pokerbender;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

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
	private String id /*= UUID.randomUUID().toString().toUpperCase()*/;
	private Player[] players/* = new Player[6]*/;
	private Game game;
	private boolean started;
	private int minRate = 10;
	
	
	public Table() {
		super();
	}
	
	public void init(){
		id = UUID.randomUUID().toString().toUpperCase();
		players = new Player[6];
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	
	public Game getGame() {
		return game;
	}
	public boolean isStarted() {
		return started;
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
