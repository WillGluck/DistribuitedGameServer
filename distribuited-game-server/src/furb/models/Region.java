package furb.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import thrift.stubs.Player;

public class Region implements Serializable {

	private static final long serialVersionUID = 8263742371187347371L;

	public static final String REGIONCODE="regioncode";
	
	private Map<String, Player> players;
	private int regionNumber;
	private int bound_x = 25;
	private int bound_y = 25;
	
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
	
	public int getBound_x() {
		return bound_x;
	}
	
	public int getBound_y() {
		return bound_y;
	}

}
