package furb.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;

import furb.models.Region;
import thrift.stubs.*;

public class GameHandler implements Game.Iface {
	
	private ServerSharedInfo serverInfo;
	private Map<String, List<Attack>> attack_buffer;
	
	public GameHandler(String self_ip) {
		this.serverInfo = ServerSharedInfo.getInstance();
		this.attack_buffer = new HashMap<String, List<Attack>>();
	}

	@Override
	public String login(String user) throws TException {
		//TODO load player from database and request server for region the player is in
		return serverInfo.getSelfIp();
	}

	@Override
	public Player get_player(String user) throws TException {
		Player player = null; //TODO load from database
		serverInfo.lockResource();
		serverInfo.getResions().get(player.area).addPlayer(player);
		serverInfo.unlockResource();
		return player;
	}

	@Override
	public boolean move_self(Player player) throws TException {
		Region region = serverInfo.getResions().get(player.area);
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
		Region region = serverInfo.getResions().get(regionCode);
		List<Player> players = new ArrayList<Player>();
		players.addAll(region.getPlayers().values());
		return players;
	}

	@Override
	public boolean attack(Attack attack) throws TException {
		Region region = null;
		for (Region region2 : serverInfo.getResions().values()) {
			if (region2.getPlayers().containsKey(attack.from))
				region = region2;
		}
		
		Player from = region.getPlayers().get(attack.from);
		if (from.attackCooldown-System.currentTimeMillis() < 1000) {
			serverInfo.lockResource();
			Player to = region.getPlayers().get(attack.to);
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
		return attacks;
	}

	@Override
	public String go_to_area(int area, Player player) throws TException {
		Region region = serverInfo.getResions().get(player.area);
		serverInfo.lockResource();
		region.getPlayers().remove(player.name);
		serverInfo.unlockResource();
		// TODO request areas from server
		return serverInfo.getSelfIp();
	}

	

}
