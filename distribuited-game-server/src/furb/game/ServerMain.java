package furb.game;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.ws.Endpoint;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import thrift.stubs.Game;
import thrift.stubs.Player;
import furb.corba.InterfaceCorba;
import furb.corba.InterfaceCorbaHelper;
import furb.corba.InterfaceCorbaImpl;
import furb.db.DataBaseManager;
import furb.models.Region;
import furb.rmi.ClientSideRMI;
import furb.rmi.ServerSideRMI;
import furb.webservice.Services;

public class ServerMain {
	
	public static void main(String[] args) throws TTransportException {
		ServerSharedInfo.instantiate(args[0]);
		DataBaseManager.instantiate();
		
		ServerMain.initWebService(args[0]);
		ServerMain.initRMI(args[0]);
		ServerMain.initCorba(args[0]);
		ServerMain.initThrift();
		
		if (args.length == 1) {
			ServerMain.initRegions();
		} else {
			ServerSharedInfo.getInstance().lockResource();
			for (int i = 1; i < args.length; i++) {
				ServerSharedInfo.getInstance().getOnlineServers().add(args[i]);
			}
			ServerSharedInfo.getInstance().unlockResource();
			
			ClientSideRMI rmi = new ClientSideRMI();
			rmi.newServer(ServerSharedInfo.getInstance().getOnlineServers().get(0), 
					ServerSharedInfo.getInstance().getSelfIp());
		}
		
	}
	
	private static void temp() {
		
		Player player = new Player();
		player.name = "caique";
		player.last_saved = new Date().getTime();
		player.life = 100;
		player.area = 1;
		List<Integer> positions = new ArrayList<Integer>();
		positions.add(20);
		positions.add(20);
		player.position = positions;
		
		DataBaseManager.getInstance().insertPlayer(player);
	}
	
	private static void initRegions() {
		ServerSharedInfo.getInstance().getRegions().put(1, new Region(1));
		ServerSharedInfo.getInstance().getRegions().put(2, new Region(2));
		ServerSharedInfo.getInstance().getRegions().put(3, new Region(3));
		ServerSharedInfo.getInstance().getRegions().put(4, new Region(4));
		ServerSharedInfo.getInstance().getRegions().put(5, new Region(5));
	}
	
	private static void initThrift() {
		new Thread(new Runnable() {			
			@Override
			public void run() {
				try {
					Map<Integer, Region> regions = ServerSharedInfo.getInstance().getRegions();
					regions.put(1, new Region(1));
					
					GameHandler handler = new GameHandler();
					Game.Processor<GameHandler> processor = new Game.Processor<GameHandler>(handler);
					TServerTransport serverTransport = new TServerSocket(9090);
				    TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
			
				    System.out.println("Servidor aguardando requisicoes Thrift....");
				    server.serve();
				    
				} catch (TTransportException e) {
					e.printStackTrace();
				}			
			}
		}).start();
	}
	
	private static void initCorba(final String ip) {
		
		new Thread(new Runnable() {			
			@Override
			public void run() {
				try {
					String[] aargs = {ip};
			       
		            ORB orb = ORB.init(aargs, null);
		            InterfaceCorbaImpl interfaceCorba = new InterfaceCorbaImpl();

		            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		            rootpoa.the_POAManager().activate();

		            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(interfaceCorba);
		            InterfaceCorba href = InterfaceCorbaHelper.narrow(ref);
		    	  
		            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		            NamingContextExt namecontextRef = NamingContextExtHelper.narrow(objRef);

		            String name = "InterfaceCorba";
		            NameComponent path[] = namecontextRef.to_name(name);
		            namecontextRef.rebind(path, href);

		            System.out.println("Servidor aguardando requisicoes CORBA....");
		            orb.run();
		           
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}).start();
          
	}
	
	private static void initRMI(final String ip) {
		new Thread(new Runnable() {			
			@Override
			public void run() {
				try {			
					ServerSideRMI obj = new ServerSideRMI();
				    Naming.rebind("//" + ip + "/InterfaceRmi", obj);		       
				    System.out.println("Servidor aguardando requisicoes RMI....");		    
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}).start();
	}
	
	private static void initWebService(final String ip) {
		Endpoint.publish("http://" + ip + ":8080/services", new Services());
		System.out.println("Servidor aguardando requisicoes web service....");
	}

}
