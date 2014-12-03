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
	
	public GameHandler() {
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
		Player player = new Player(); //TODO load from database
		try {
		player.name = "player";
		player.area = 1;
		player.life = 100;
		player.position = new ArrayList<Integer>();
		player.position.add(1);
		player.position.add(1);
		player.last_saved = System.currentTimeMillis();
		serverInfo.lockResource();
		serverInfo.getResions().get(player.area).addPlayer(player);
		serverInfo.unlockResource();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return player;
	}

	@Override
	public boolean move_self(Player player) throws TException {
		Region region = serverInfo.getResions().get(player.area);
		
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
		Region region = serverInfo.getResions().get(regionCode);
		List<Player> players = new ArrayList<Player>();
		players.addAll(region.getPlayers().values());
		return players;
	}

	@Override
	public boolean attack(Attack attack) throws TException {
		Region region = null;
		for (Region region2 : serverInfo.getResions().values()) {
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
		//TODO update player in database
		if (attacks == null)
			attacks = new ArrayList<Attack>(0);
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
