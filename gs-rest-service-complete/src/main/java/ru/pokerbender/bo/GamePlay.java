package ru.pokerbender.bo;

import hello.Application;
import ru.pokerbender.Room;
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
}
