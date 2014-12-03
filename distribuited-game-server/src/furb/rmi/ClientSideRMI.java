package furb.rmi;


import java.rmi.Naming;
import java.util.Map;

import thrift.stubs.Player;
import furb.models.Region;

public class ClientSideRMI  {
	
	InterfaceRmi rmiInterface;
	
	public ClientSideRMI() {
		
	}
	
	public Player getPlayerInfo(String targetIP, String userName) {
		try {
			InterfaceRmi rmiInterface = (InterfaceRmi)Naming.lookup("//" + targetIP + "/InterfaceRmi");
			return rmiInterface.getPlayerInfo(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	public Map<Integer, Region> broadcastNewServer(String targetIP, String newIP) {
		Map<Integer, Region> regions = null;
		try {
			InterfaceRmi rmiInterface = (InterfaceRmi)Naming.lookup("//" + targetIP + "/InterfaceRmi");
			regions = rmiInterface.broadcastNewServer(targetIP);
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
