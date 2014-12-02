package furb.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;

import furb.models.Region;
import thrift.stubs.*;

public class GameHandler implements Game.Iface {
	
	private String self_ip;
	private Map<Integer, Region> regions;
	
	public GameHandler(String self_ip) {
		this.self_ip = self_ip;
		this.regions = new HashMap<Integer, Region>();
	}

	@Override
	public String login(String user) throws TException {
		return self_ip;
	}

	@Override
	public Player get_player(String user) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean move_self(Player player) throws TException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Player> update_players() throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean attack(Attack attack) throws TException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Attack> update_self(int id) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String go_to_area(int area) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
