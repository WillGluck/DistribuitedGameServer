package furb.corba;

import thrift.stubs.Player;
import furb.db.DataBaseManager;
import furb.game.ServerSharedInfo;
import furb.models.Region;
import furb.rmi.ClientSideRMI;


public class InterfaceCorbaImpl extends InterfaceCorbaPOA {

	@Override
	public boolean checkForRegion(int regionCode) {	
		
		boolean regionChecked = false;
		for (Region region :ServerSharedInfo.getInstance().getRegions().values()) {
			if (region.getRegionNumber() == regionCode) {
				regionChecked = true;
			} 
		}
		System.out.println("[CORBA] checkForRegion executado");
		return regionChecked;
		
	}

	@Override
	public void updatePlayer(String userName) {
		
		ClientSideCorba corba = new ClientSideCorba();
		String serverID = null;
		long biggerTimestamp = 0;
		
		for (String server : ServerSharedInfo.getInstance().getOnlineServers()) {		
			long tempTimestamp = corba.getPlayerTimestamp(server, userName);
			if (biggerTimestamp < tempTimestamp) {
				serverID = server;
				biggerTimestamp = tempTimestamp;
			}
		}
		
		ClientSideRMI rmi = new ClientSideRMI();
		Player player = rmi.getPlayerInfo(serverID, userName);
		if (DataBaseManager.getInstance().getPlayer(userName) != null)
			DataBaseManager.getInstance().updatePlayer(player);
		else
			DataBaseManager.getInstance().insertPlayer(player);

		System.out.println("[CORBA] updatePlayer executado");
	}

	@Override
	public long getPlayerTimestamp(String userName) {		
		Player player = DataBaseManager.getInstance().getPlayer(userName);
		long playerTimestamp;
		if (player == null) {
			playerTimestamp = -1;
		} else {
			playerTimestamp = player.last_saved;
		}
		System.out.println("[COBRBA] getPlayerTimestamp executado");
		return playerTimestamp;
	}		

}
