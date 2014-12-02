

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerManager {
	
	public static final String serverIP = "127.168.0.9";
	
	private static ServerManager instance;	
	
	private List<Region> regions = new ArrayList<Region>();
	private Map<String, String> updateSqls = new HashMap<String, String>();
	private List<String> servers = new ArrayList<String>();
	private boolean status;
	
	private ServerManager() {
		//TODO
	}
	
	public static ServerManager getInstance() {
		if (ServerManager.instance == null) {
			ServerManager.instance = new ServerManager();
		}
		return ServerManager.instance;
	}
	
	public void addSql(String userName, String sql) {
		String allSql = "";
		if (this.updateSqls.containsKey(userName)) {
			allSql = this.updateSqls.get(userName);
		}
		allSql += sql;
		this.updateSqls.put(userName, allSql);
	}
	
	public List<Region> getRegions() {
		return regions;
	}
	
	public List<String> getServers() {
		return servers;
	}

	public boolean getStatus() {
		return this.status;
	}

}
