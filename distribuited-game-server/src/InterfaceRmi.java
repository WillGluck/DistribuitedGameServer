

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import furb.models.Region;

public interface InterfaceRmi extends Remote{

	//public void replicatePlayerChanges(String sql) throws RemoteException;
	
	//public boolean checkStatus() throws RemoteException;
	
	//public Region getOneRegion() throws RemoteException;
	
	public Player getPlayerInfo() throws RemoteException;
	
	public void newServer(String ip) throws RemoteException;
	
	public List<Integer> broadcastNewServer(String ip) throws RemoteException;
	
	public void removeRegion(int regionCode) throws RemoteException;
	
	public void addRegion(int regionCode) throws RemoteException;

}
