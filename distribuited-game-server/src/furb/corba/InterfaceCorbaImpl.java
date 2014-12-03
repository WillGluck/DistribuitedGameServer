package furb.corba;

import thrift.stubs.Player;
import furb.db.DataBaseManager;
import furb.game.ServerSharedInfo;
import furb.models.Region;
import furb.rmi.ClientSideRMI;


public class InterfaceCorbaImpl extends InterfaceCorbaPOA {

	@Override
	public boolean checkForRegion(int regionCode) {		
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
		DataBaseManager.getInstance().updatePlayer(player);
	}

	@Override
	public long getPlayerTimestamp(String userName) {		
		Player player = DataBaseManager.getInstance().getPlayer(userName);
		if (player == null) {
			return 0;
		} else {
			return player.last_saved;
		}
		
	}		

}
