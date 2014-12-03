package furb.rmi;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

import thrift.stubs.Player;
import furb.models.Region;

public interface InterfaceRmi extends Remote{
	
	public Player getPlayerInfo(String userName) throws RemoteException;
	
	public void newServer(String ip) throws RemoteException;
	
	public Map<Integer, Region> broadcastNewServer(String ip) throws RemoteException;
	
	public void removeRegion(int regionCode) throws RemoteException;
	
	public void addRegion(int regionCode) throws RemoteException;

}
