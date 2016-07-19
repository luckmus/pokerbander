package ru.pokerbender.restcntrlr;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.pokerbender.Application;
import ru.pokerbender.bo.GamePlay;
import ru.pokerbender.cntrlpojo.TableState;

@RestController
public class GamePlayCntrlr {
	@RequestMapping("/room1/newtable")
    public TableState newTable() {
		return gamePlay().newTable();
    }
 	
	@RequestMapping(value="/room1/gettable/{id}" , method=RequestMethod.GET)
    public TableState getTable(@PathVariable("id") String id) {
		return gamePlay().getTable(id);
    }
	
	private GamePlay gamePlay(){
		return (GamePlay) Application.ac.getBean("gamePlay");
	}
	
}
