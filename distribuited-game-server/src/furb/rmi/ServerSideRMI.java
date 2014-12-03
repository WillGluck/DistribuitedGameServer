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
		System.out.println("[RMI] getPlayerInfo executado");
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
		
		regionsByServer.put(ServerSharedInfo.getInstance().getSelfIp(), 
				ServerSharedInfo.getInstance().getRegions());
		
		totalRegions += ServerSharedInfo.getInstance().getRegions().size();		
		serverMedia = totalRegions / (ServerSharedInfo.getInstance().getOnlineServers().size() + 2);
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
		System.out.println("[RMI] newServer executado");
	}

	@Override
	public Map<Integer, Region> broadcastNewServer(String ip) throws RemoteException {
		ServerSharedInfo.getInstance().getOnlineServers().add(ip);
		System.out.println("[RMI] broadcastNewServer executado");
		return ServerSharedInfo.getInstance().getRegions();
	}

	@Override
	public void removeRegion(int regionCode) throws RemoteException {	
		ServerSharedInfo.getInstance().lockResource();
		ServerSharedInfo.getInstance().getRegions().remove(regionCode);
		ServerSharedInfo.getInstance().unlockResource();
		System.out.println("[RMI] removeRegion executado");
	}

	@Override
	public void addRegion(int regionCode) throws RemoteException {	
		ServerSharedInfo.getInstance().lockResource();
		ServerSharedInfo.getInstance().getRegions().put(regionCode, new Region(regionCode));
		ServerSharedInfo.getInstance().unlockResource();
		System.out.println("[RMI] addRegion executado");
	}	
	
}
