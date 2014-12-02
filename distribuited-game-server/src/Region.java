

import java.util.List;

public class Region {
	
	public static final String REGIONCODE="regioncode";
	
	private List<Player> players;
	private int regionNumber;
	
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public int getRegionNumber() {
		return regionNumber;
	}
	public void setRegionNumber(int regionNumber) {
		this.regionNumber = regionNumber;
	}	

}
