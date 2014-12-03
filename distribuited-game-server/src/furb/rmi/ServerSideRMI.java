package furb.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import furb.db.DataBaseManager;
import thrift.stubs.Player;

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
		//TODO		
	}

	@Override
	public List<Integer> broadcastNewServer(String ip) throws RemoteException {
		//TODO
		return null;
	}

	@Override
	public void removeRegion(int regionCode) throws RemoteException {
		//TODO		
	}

	@Override
	public void addRegion(int regionCode) throws RemoteException {
		//TODO
	}	
	
}
