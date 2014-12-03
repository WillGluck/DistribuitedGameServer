package furb.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Services {

	@WebMethod
	public boolean registerPlayer(String playerName) {
		return false;
	}
	
	@WebMethod
	public boolean updatePlayerName(String oldName, String newName) {
		return false;
	}
	
	@WebMethod
	public String getPlayer(String playerName) {
		thrift.stubs.Player player = new thrift.stubs.Player();
		return null;
	}
	
}
