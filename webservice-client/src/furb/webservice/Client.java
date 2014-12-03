package furb.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Client {
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Services services = new ServicesService().getServicesPort();
		
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("register")) {
				String playerName = line.split(" ")[1];
				if (services.registerPlayer(playerName)) {
					System.out.println("Player " + playerName + " registered");
				} else {
					System.out.println("A player with that name already exists");
				}
			} else if (line.startsWith("changename")) {
				String oldName = line.split(" ")[1];
				String newName = line.split(" ")[2];
				if (services.updatePlayerName(oldName, newName)) {
					System.out.println("Name changed to " + newName);
				} else {
					System.out.println("Could not switch name");
				}
			} else if (line.startsWith("getinfo")) {
				String playerName = line.split(" ")[1];
				String playerInfo = services.getPlayer(playerName);
				System.out.println(playerInfo);
			} else {
				System.out.println("Wrong command");
			}
			System.out.println();
		}
	}

}
