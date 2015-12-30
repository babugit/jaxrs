package com.player.data.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@XmlRootElement(name = "player")
@XmlType(propOrder = {"id", "name", "birthDate", "play", "ranks"})
@JsonPropertyOrder({"id", "name", "birthDate", "play", "ranks"})
public class Player implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String birthDate;
	private String play;
	private ArrayList<Ranks> ranks = new ArrayList<Ranks>();

	public Player() {
		super();
	}
	
	public Player(int id, String name, String birthDate, String play) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.play = play;
	}
	
	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@XmlElement
	public String getPlay() {
		return play;
	}

	public void setPlay(String play) {
		this.play = play;
	}

	@XmlElement
	public ArrayList<Ranks> getRanks() {
		return ranks;
	}

	public void setRanks(ArrayList<Ranks> ranks) {
		this.ranks = ranks;
	}

	@JsonIgnore
	public String toString() {
		return this.name + " - " + this.birthDate == null? "Unknown" : this.birthDate.toString();
	}

}
