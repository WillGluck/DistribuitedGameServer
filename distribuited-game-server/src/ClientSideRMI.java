

import java.rmi.Naming;

import furb.models.Region;

public class ClientSideRMI  {
	
	InterfaceRmi rmiInterface;
	
	public ClientSideRMI() {
		
	}
//	
//	public static void main(String[] args) {
//		ClientSideRMI rmi = new ClientSideRMI();
//		boolean status = rmi.checkStatus("localhost");
//		System.out.println(status);
//	}
	
	public void replicatePlayerChanges(String sql, String ip) {
		try {
			InterfaceRmi rmiInterface = (InterfaceRmi)Naming.lookup("//" + ip + "/InterfaceRmi");
			rmiInterface.replicatePlayerChanges(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkStatus(String ip) {
		boolean status = false;
		try {
			InterfaceRmi rmiInterface = (InterfaceRmi)Naming.lookup("//" + ip + "/InterfaceRmi");
			status = rmiInterface.checkStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public Region getOneRegion(String ip)  {
		Region region = null;
		try {
			InterfaceRmi rmiInterface = (InterfaceRmi)Naming.lookup("//" + ip + "/InterfaceRmi");
			region = rmiInterface.getOneRegion();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return region;
	}	   

}
