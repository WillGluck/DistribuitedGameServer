
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerSideRMI extends UnicastRemoteObject implements InterfaceRmi {

	private static final long serialVersionUID = 1L;

	public ServerSideRMI() throws RemoteException {
		super();
	}	
	
	public void replicatePlayerChanges(String sql) throws RemoteException {
		DataBaseManager.getInstance().executeSql(sql);
	}	
	
	public boolean checkStatus() throws RemoteException {		
		return ServerManager.getInstance().getStatus();
	}

	public Region getOneRegion() throws RemoteException {
		return ServerManager.getInstance().getRegions().get(0);
	}



}
