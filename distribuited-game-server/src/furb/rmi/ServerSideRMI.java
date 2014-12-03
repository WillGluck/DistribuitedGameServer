package furb.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

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
		for (String server : ServerSharedInfo.getInstance().getOnlineServers()) {
			rmi.broadcastNewServer(server, ip);	
		}		
		//TODO
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
