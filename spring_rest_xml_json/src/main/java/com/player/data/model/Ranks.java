package com.player.data.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

@XmlRootElement(name = "rank")
@XmlType(propOrder = {"gameType", "rank"})
@JsonPropertyOrder({"gameType", "rank"})
public class Ranks implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String gameType;
	private Integer rank;
	
	public Ranks() {
		super();
	}
	
	public Ranks(String gameType, Integer rank) {
		this.gameType = gameType;
		this.rank = rank;
	}
	
	@XmlElement
	public String getGameType() {
		return gameType;
	}
	
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	
	@XmlElement
	public Integer getRank() {
		return rank;
	}
	
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
}
