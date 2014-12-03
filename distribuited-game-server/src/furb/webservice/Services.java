package furb.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import furb.db.DataBaseManager;
import thrift.stubs.Player;

@WebService
public class Services {

	@WebMethod
	public boolean registerPlayer(String playerName) {
		Player player = DataBaseManager.getInstance().getPlayer(playerName);
		if (player != null)
			return false;
		
		List<Integer> position = new ArrayList<Integer>();
		position.add(10);
		position.add(10);
		player = new Player(playerName, position, 100, 1, System.currentTimeMillis());
		
		DataBaseManager.getInstance().insertPlayer(player);
		
		return true;
	}
	
	@WebMethod
	public boolean updatePlayerName(String oldName, String newName) {
		Player player = DataBaseManager.getInstance().getPlayer(oldName);
		if (player == null)
			return false;
		
		player.name = newName;
		DataBaseManager.getInstance().updatePlayer(player);
		
		return true;
	}
	
	@WebMethod
	public String getPlayer(String playerName) {
		Player player = DataBaseManager.getInstance().getPlayer(playerName);
		
		if (player == null)
			return "Player does not exists";
		
		String playerInfo = String.format("{name:%s, area:%i, position:%i, life:%i}",
				player.name, player.area, player.position, player.life);
		
		return playerInfo;
	}
	
}
