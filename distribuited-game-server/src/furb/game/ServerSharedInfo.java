package furb.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import furb.models.Region;

public class ServerSharedInfo {
	
	private String self_ip;
	private Map<Integer, Region> regions;
	private List<String> online_servers;
	
	private static ServerSharedInfo self;
	
	private ReentrantLock lock = new ReentrantLock();
	
	private ServerSharedInfo(String self_ip) {
		this.self_ip = self_ip;
		this.regions = new HashMap<Integer, Region>();
		this.online_servers = new ArrayList<String>();
	}
	
	public synchronized void lockResource() {
		lock.lock();
	}
	
	public synchronized void unlockResource() {
		lock.unlock();
	}
	
	public String getSelfIp() {
		return self_ip;
	}
	
	public Map<Integer, Region> getRegions() {
		return regions;
	}
	
	
	public List<String> getOnlineServers() {
		return online_servers;
	}
	
	
	public static void instantiate(String self_ip) {
		self = new ServerSharedInfo(self_ip);
	}
	
	public static ServerSharedInfo getInstance() {
		if (self != null)
			return self;
		throw new RuntimeException();
	}

}
