package furb.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;

import furb.corba.ClientSideCorba;
import furb.db.DataBaseManager;
import furb.models.Region;
import thrift.stubs.*;

public class GameHandler implements Game.Iface {
	
	private ServerSharedInfo serverInfo;
	private Map<String, List<Attack>> attack_buffer;
	
	public GameHandler() {
		this.serverInfo = ServerSharedInfo.getInstance();
		this.attack_buffer = new HashMap<String, List<Attack>>();
	}

	@Override
	public String login(String user) throws TException {
		Player player = DataBaseManager.getInstance().getPlayer(user);
		if (player == null) {
			return "";
		}
		
		return this.getServerWithRegion(player.area);
	}

	@Override
	public Player get_player(String user) throws TException {
		Player player = DataBaseManager.getInstance().getPlayer(user);

		serverInfo.lockResource();
		serverInfo.getRegions().get(player.area).addPlayer(player);
		serverInfo.unlockResource();
		
		return player;
	}

	@Override
	public boolean move_self(Player player) throws TException {
		Region region = serverInfo.getRegions().get(player.area);
		
		if (region.getBound_x() <= player.position.get(0) ||
				region.getBound_y() <= player.position.get(1)) {
			return false;
		}
		
		if (player.position.get(0) < 0 || player.getPosition().get(1) < 0) {
			return false;
		}
		
		for (Player player2 : region.getPlayers().values()) {
			if (player2.getPosition().get(0).equals(player.getPosition().get(0))
					&& player2.getPosition().get(1).equals(player.getPosition().get(1))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<Player> update_players(int regionCode) throws TException {
		Region region = serverInfo.getRegions().get(regionCode);
		List<Player> players = new ArrayList<Player>();
		players.addAll(region.getPlayers().values());
		return players;
	}

	@Override
	public boolean attack(Attack attack) throws TException {
		Region region = null;
		for (Region region2 : serverInfo.getRegions().values()) {
			if (region2.getPlayers().containsKey(attack.attcker)) {
				region = region2;
				break;
			}
		}
		
		Player from = region.getPlayers().get(attack.attcker);
		if (from.attackCooldown-System.currentTimeMillis() < 1000) {
			serverInfo.lockResource();
			Player to = region.getPlayers().get(attack.attacked);
			to.life = to.life - 10;
			if (!attack_buffer.containsKey(to.name)) {
				attack_buffer.put(to.name, new ArrayList<Attack>());
			}
			attack_buffer.get(to.name).add(attack);
			from.attackCooldown = System.currentTimeMillis();
			serverInfo.unlockResource();
			return true;
		}
		
		return false;
	}
	
	@Override
	public List<Attack> update_self(String name) throws TException {
		List<Attack> attacks = attack_buffer.get(name);
		attack_buffer.remove(name);
		
		Region region = null;
		for (Region region2 : serverInfo.getRegions().values()) {
			if (region2.getPlayers().containsKey(name)) {
				region = region2;
				break;
			}
		}
		Player player = region.getPlayers().get(name);
		DataBaseManager.getInstance().updatePlayer(player);
		
		if (attacks == null)
			attacks = new ArrayList<Attack>(0);
		return attacks;
	}

	@Override
	public String go_to_area(int area, Player player) throws TException {
		Region region = serverInfo.getRegions().get(player.area);
		serverInfo.lockResource();
		region.getPlayers().remove(player.name);
		serverInfo.unlockResource();

		String server = this.getServerWithRegion(area);
		ClientSideCorba corba = new ClientSideCorba();
		corba.updatePlayer(server, player.name);
		
		return server;
	}
	
	private String getServerWithRegion(int region) {
		if (ServerSharedInfo.getInstance().getRegions().containsKey(region))
			return ServerSharedInfo.getInstance().getSelfIp();
		
		String server = null;
		ClientSideCorba corba = new ClientSideCorba();
		for (String online_server : ServerSharedInfo.getInstance().getOnlineServers()) {
			if (corba.checkForRegion(online_server, region)) {
				server = online_server;
			}
		}
		
		return server;
	}

}
