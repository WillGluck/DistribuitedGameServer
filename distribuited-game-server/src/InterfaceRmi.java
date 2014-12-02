

import java.rmi.Remote;
import java.rmi.RemoteException;

import furb.models.Region;

public interface InterfaceRmi extends Remote{

	public void replicatePlayerChanges(String sql) throws RemoteException;
	
	public boolean checkStatus() throws RemoteException;
	
	public Region getOneRegion() throws RemoteException;

}
