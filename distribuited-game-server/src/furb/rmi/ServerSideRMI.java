package furb.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import thrift.stubs.Player;
import furb.db.DataBaseManager;
import furb.game.ServerSharedInfo;
import furb.models.Region;

public class ServerSideRMI extends UnicastRemoteObject implements InterfaceRmi {

	private static final long serialVersionUID = 1L;

	public ServerSideRMI() throws RemoteException {
		super();
	}

	@Override
	public Player getPlayerInfo(String userName) throws RemoteException {
		return DataBaseManager.getInstance().getPlayer(userName);
	}

	@Override
	public void newServer(String ip) throws RemoteException {
		
		ClientSideRMI rmi = new ClientSideRMI();
		Map<String, Map<Integer, Region>> regionsByServer = new HashMap<String, Map<Integer,Region>>();
		
		int serverMedia = 0;
		int totalRegions = 0;
		
		for (String server : ServerSharedInfo.getInstance().getOnlineServers()) {
			Map<Integer, Region> tempRegions = rmi.broadcastNewServer(server, ip); 
			regionsByServer.put(server, tempRegions);
			int tempTotalRegions = tempRegions.size(); 
			totalRegions += tempTotalRegions;					
		}
		
		totalRegions += ServerSharedInfo.getInstance().getRegions().size();		
		serverMedia = totalRegions / ServerSharedInfo.getInstance().getOnlineServers().size() + 2;
		int newServerRegions = 0;
		
		for (Entry<String, Map<Integer, Region>> entrySet : regionsByServer.entrySet()) {
			if (entrySet.getValue().size() > serverMedia) {
				int difference = entrySet.getValue().size() - serverMedia;
				for (Region region : entrySet.getValue().values()) {
					rmi.removeRegion(entrySet.getKey(), region.getRegionNumber());
					rmi.addRegion(ip, region.getRegionNumber());
					newServerRegions++;
					difference--;
					if (difference == 0) break;
					if (newServerRegions >= serverMedia) break;
				}
			}
		}
		
		ServerSharedInfo.getInstance().getOnlineServers().add(ip);
	}

	@Override
	public Map<Integer, Region> broadcastNewServer(String ip) throws RemoteException {
		ServerSharedInfo.getInstance().getOnlineServers().add(ip);
		return ServerSharedInfo.getInstance().getRegions();
	}

	@Override
	public void removeRegion(int regionCode) throws RemoteException {
		ServerSharedInfo.getInstance().getOnlineServers().remove(regionCode);		
	}

	@Override
	public void addRegion(int regionCode) throws RemoteException {		
		ServerSharedInfo.getInstance().getRegions().put(regionCode, new Region(regionCode));
	}	
	
}
