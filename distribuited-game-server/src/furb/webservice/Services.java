package furb.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import furb.corba.ClientSideCorba;
import furb.db.DataBaseManager;
import furb.game.ServerSharedInfo;
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
		ClientSideCorba corba = new ClientSideCorba();
		for (String server : ServerSharedInfo.getInstance().getOnlineServers()) {
			corba.updatePlayer(server, playerName);
		}
		
		System.out.println("[WebService] Registered player " + playerName);
		
		return true;
	}
	
	@WebMethod
	public boolean updatePlayerName(String oldName, String newName) {
		Player player = DataBaseManager.getInstance().getPlayer(oldName);
		if (player == null)
			return false;
		
		player.name = newName;
		DataBaseManager.getInstance().updatePlayer(player);
		ClientSideCorba corba = new ClientSideCorba();
		for (String server : ServerSharedInfo.getInstance().getOnlineServers()) {
			corba.updatePlayer(server, newName);
		}
		
		System.out.println("[WebService] Updated player name from " + oldName + " to " + newName);
		
		return true;
	}
	
	@WebMethod
	public String getPlayer(String playerName) {
		Player player = DataBaseManager.getInstance().getPlayer(playerName);
		
		if (player == null)
			return "Player does not exists";
		
		String playerInfo = String.format("{name:%s, area:%d, position:%s, life:%d}",
				player.name, player.area, player.position.toString(), player.life);
		
		System.out.println("[WebService] returned player info");
		
		return playerInfo;
	}
	
}
