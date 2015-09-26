package ru.pokerbender.cntrl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import ru.pokerbender.Player;
import ru.pokerbender.Table;

public class TableState {
	private List<PlayerInfo> players;
	private GameInfo gameInfo;
	private boolean started;
	private int minRate;
	
	
	public TableState(Table table) {
		
		this.started = table.isStarted();
		this.minRate = table.getMinRate();
		players = new ArrayList();
		List<Player> gplayers = null;
		if (table.getGame()!=null){
			this.gameInfo = new GameInfo(table.getGame());
			gplayers = new ArrayList();
			gplayers.addAll(table.getGame().getCurrentGamePlrs());
			Collections.sort(gplayers);
		}
		for (int i=0; i< table.getPlayers().length; i++){
			Player p = table.getPlayers()[i];
			if (p == null){
				continue;
			}
			PlayerInfo pi = new PlayerInfo(p);
			pi.setPlace(i);
			if (gplayers != null){
				
				int indx = Collections.binarySearch(gplayers, p);
				pi.setInGame(indx!=-1);
				if (indx>=0){
					Player gp = gplayers.get(indx);
					pi.setDoAction(gp.equals(table.getGame().getCurrentPlayer()));					
				}
				
			}
			
		} 
		
	}

}
