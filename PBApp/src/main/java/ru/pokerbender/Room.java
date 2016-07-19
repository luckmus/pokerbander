package ru.pokerbender;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Room {
	private Map<String, Table> tables= new ConcurrentHashMap();
	
	public Table getTable(String id){
		return tables.get(id);
	}
	
	public Table getNewTable(){
		Table res = (Table) Application.ac.getBean("table");
		//Table res = new Table();
		tables.put(res.getId(), res);
		return res;
	}
}
