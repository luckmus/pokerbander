package ru.pokerbender.bo;

import ru.pokerbender.Application;
import ru.pokerbender.Player;
import ru.pokerbender.Room;
import ru.pokerbender.Table.PLAYERS_ACTION;
import ru.pokerbender.cntrlpojo.PlayerInfo;
import ru.pokerbender.cntrlpojo.TableState;

public class GamePlay {
	
	public Room getRoom(){
		return (Room) Application.ac.getBean("room");
	}
	
	public TableState newTable(){
	return new TableState(getRoom().getNewTable());
	}
	
	public TableState getTable(String id){
		return new TableState(getRoom().getTable(id));
	}
	
	public PlayerInfo addPlayer(String tableId, int place){
		return new PlayerInfo (getRoom().getTable(tableId).addPlayer(place));
	}
	
	public TableState startGame(String id){
		getRoom().getTable(id).startTable();
		getRoom().getTable(id).startGame();
		return new TableState(getRoom().getTable(id));
	}
	
	public TableState action(String tableId, String playerId, PLAYERS_ACTION action, int sum){
		Player p = getRoom().getTable(tableId).getPlayerById(playerId);
		getRoom().getTable(tableId).getGame().action(p, action, sum);
		return new TableState(getRoom().getTable(tableId));
	}
}
