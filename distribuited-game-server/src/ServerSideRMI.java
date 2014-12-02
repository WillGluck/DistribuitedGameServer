
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import furb.models.Region;

public class ServerSideRMI extends UnicastRemoteObject implements InterfaceRmi {

	private static final long serialVersionUID = 1L;

	public ServerSideRMI() throws RemoteException {
		super();
	}

	@Override
	public Player getPlayerInfo() throws RemoteException {
		
		return null;
	}

	@Override
	public void newServer(String ip) throws RemoteException {

		
	}

	@Override
	public List<Integer> broadcastNewServer(String ip) throws RemoteException {
		
		return null;
	}

	@Override
	public void removeRegion(int regionCode) throws RemoteException {
		
		
	}

	@Override
	public void addRegion(int regionCode) throws RemoteException {

			
	}	




}
