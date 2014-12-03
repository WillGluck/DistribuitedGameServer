package furb.corba;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;


public class ClientSideCorba {
	
	public ClientSideCorba() {
		
	}		
	    
	public boolean checkForRegion(String targetIP, int regionCode) {
		InterfaceCorba server = this.connectToCorba(targetIP);
		return server.checkForRegion((short)regionCode);
	}
	  
	public void updatePlayer(String targetIP, String userName) {
		InterfaceCorba server = this.connectToCorba(targetIP);
		server.updatePlayer(userName);
	}
	  
	public long getPlayerTimestamp(String targetIP, String userName) {
		InterfaceCorba server = this.connectToCorba(targetIP);
		return server.getPlayerTimestamp(userName);
	}
	  
	private InterfaceCorba connectToCorba(String targetIP) {
		  
		InterfaceCorba server = null;
		  
			try {
				String args2 = "-ORBInitialHost " + targetIP;
				ORB orb = ORB.init(args2.split(" "), null);
				org.omg.CORBA.Object objRef;
				objRef = orb.resolve_initial_references("NameService");
				NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
				String name = "InterfaceCorba";
				server = InterfaceCorbaHelper.narrow(ncRef.resolve_str(name));
	
			} catch (InvalidName e) {
				e.printStackTrace();
			} catch (NotFound e) {
				e.printStackTrace();
			} catch (CannotProceed e) {
				e.printStackTrace();
			} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
				e.printStackTrace();
			}		 
			return server;
	 }
	  
}
