package furb.rmi;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import thrift.stubs.Player;

public interface InterfaceRmi extends Remote{
	
	public Player getPlayerInfo(String userName) throws RemoteException;
	
	public void newServer(String ip) throws RemoteException;
	
	public List<Integer> broadcastNewServer(String ip) throws RemoteException;
	
	public void removeRegion(int regionCode) throws RemoteException;
	
	public void addRegion(int regionCode) throws RemoteException;

}
