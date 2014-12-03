package furb.corba;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.SharedInputStream;

import thrift.stubs.Player;
import furb.db.DataBaseManager;
import furb.game.ServerSharedInfo;
import furb.models.Region;
import furb.rmi.ClientSideRMI;


public class InterfaceCorbaImpl extends InterfaceCorbaPOA {

	@Override
	public boolean checkForRegion(short regionCode) {	
		ServerSharedInfo.instantiate("localhost");
		
		for (Region region :ServerSharedInfo.getInstance().getRegions().values()) {
			if (region.getRegionNumber() == regionCode) {
				System.out.println("eita");
				return true;
			}
		}
		return false;
		
	}

	@Override
	public void updatePlayer(String userName) {
		ClientSideCorba corba = new ClientSideCorba();
		String serverID = null;
		int biggerTimestamp = 0;
		for (String server : ServerSharedInfo.getInstance().getOnlineServers()) {
			int tempTimestamp = (int) corba.getPlayerTimestamp(server, userName);
			if (biggerTimestamp <= tempTimestamp) {
				serverID = server;
				biggerTimestamp = tempTimestamp;
			}
		}
		ClientSideRMI rmi = new ClientSideRMI();
		Player player = rmi.getPlayerInfo(serverID, userName);
		ServerSharedInfo.getInstance().lockResource();
		DataBaseManager.getInstance().updatePlayer(player);
		ServerSharedInfo.getInstance().unlockResource();
	}

	@Override
	public int getPlayerTimestamp(String userName) {
		return (int) DataBaseManager.getInstance().getPlayer(userName).last_saved;		
		
	}		

}
