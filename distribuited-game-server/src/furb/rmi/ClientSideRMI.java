package furb.rmi;


import java.rmi.Naming;
import java.util.List;

import thrift.stubs.Player;

public class ClientSideRMI  {
	
	InterfaceRmi rmiInterface;
	
	public ClientSideRMI() {
		
	}
	
	public Player getPlayerInfo(String targetIP) {
		return null;
	}
	
	public void newServer(String targetIP, String newIP) {
		try {
			InterfaceRmi rmiInterface = (InterfaceRmi)Naming.lookup("//" + targetIP + "/InterfaceRmi");
			rmiInterface.newServer(newIP);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public List<Integer> broadcastNewServer(String targetIP, String newIP) {
		List<Integer> regions = null;
		try {
			InterfaceRmi rmiInterface = (InterfaceRmi)Naming.lookup("//" + targetIP + "/InterfaceRmi");
			regions = rmiInterface.broadcastNewServer(newIP);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return regions;
	}
	
	public void removeRegion(String targetIP, int regionCode) {
		try {
			InterfaceRmi rmiInterface = (InterfaceRmi)Naming.lookup("//" + targetIP + "/InterfaceRmi");
			rmiInterface.removeRegion(regionCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addRegion(String targetIP, int regionCode) {
		try {
			InterfaceRmi rmiInterface = (InterfaceRmi)Naming.lookup("//" + targetIP + "/InterfaceRmi");
			rmiInterface.addRegion(regionCode);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}   

}
