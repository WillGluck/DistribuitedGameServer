package furb.models;

import java.util.HashMap;
import java.util.Map;

import thrift.stubs.Player;

public class Region {
	
	public static final String REGIONCODE="regioncode";
	
	private Map<String, Player> players;
	private int regionNumber;
	
	public Region(int regionNumber) {
		this.regionNumber = regionNumber;
		this.players = new HashMap<String, Player>();
	}
	
	public Map<String, Player> getPlayers() {
		return players;
	}

	public void addPlayer(Player player) {
		players.put(player.name, player);
	}
	
	public int getRegionNumber() {
		return regionNumber;
	}
	public void setRegionNumber(int regionNumber) {
		this.regionNumber = regionNumber;
	}	

}
