package ru.pokerbender.rest;

import ru.pokerbender.Application;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.pokerbender.bo.GamePlay;
import ru.pokerbender.cntrlpojo.PlayerInfo;
import ru.pokerbender.cntrlpojo.TableState;

@RestController
public class GreetingController {

    //http://localhost:8080/room/newtable
    @RequestMapping("/room/newtable")
    public TableState newTable() {
		return gamePlay().newTable();
    }
 	
	@RequestMapping(value="/room/gettable/{id}" , method=RequestMethod.GET)
    public TableState getTable(@PathVariable("id") String id) {
		return gamePlay().getTable(id);
    }
	
	@RequestMapping(value="/room/table/{id}/addplayer/{place}" , method=RequestMethod.GET)
    public PlayerInfo getTable(	@PathVariable("id") String id, 
    							@PathVariable("place") int place) {
		return gamePlay().addPlayer(id, place);
    }
	
	@RequestMapping(value="/room/table/{id}/startgame" , method=RequestMethod.GET)
    public TableState startGame(	@PathVariable("id") String id) {
		return gamePlay().startGame(id);
    }
	private GamePlay gamePlay(){
		return (GamePlay) Application.ac.getBean("gamePlay");
	}
}
