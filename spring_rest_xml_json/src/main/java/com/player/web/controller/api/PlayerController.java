package com.player.web.controller.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.player.data.model.Player;
import com.player.data.model.Ranks;

@Controller
public class PlayerController {
	
	@ResponseBody
	@RequestMapping(value = "/getplayer/{id}/{name}",
		method = RequestMethod.GET,
		produces={"application/json", "application/xml"})
	public Player getStudent(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") final int id,
			@PathVariable("name") final String name) {
		
		// Create a new player object and return it
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		String play = "Tennis";
		Player player = new Player(id, name, dateFormatter.format(new Date()), play);
		
		List<Ranks> ranks = player.getRanks();
		ranks.add(new Ranks("Singles", 5));
		ranks.add(new Ranks("Doubles", 4));
		
		return player;
	}
	
	@ResponseBody
	@RequestMapping(value = "/postplayer",
		method = RequestMethod.POST,
		produces={"application/json", "application/xml"},
		consumes={"application/json", "application/xml"})
	public Player echoStudent(@RequestBody Player player,
			HttpServletRequest request, HttpServletResponse response) {
		
		// Just echo the same student back
		return player;
	}
}
