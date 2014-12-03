package furb.game;

import java.rmi.Naming;
import java.util.Map;

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
import furb.corba.InterfaceCorba;
import furb.corba.InterfaceCorbaHelper;
import furb.corba.InterfaceCorbaImpl;
import furb.db.DataBaseManager;
import furb.models.Region;
import furb.rmi.ServerSideRMI;

public class ServerMain {
	
	public static void main(String[] args) throws TTransportException {
		
		ServerMain.initRMI();
		ServerMain.initCorba();		
		ServerMain.initThrift();
		
		ServerSharedInfo.instantiate("localhost");
		DataBaseManager.instantiate();
		
		if (args.length == 1) {
			ServerMain.initRegions();
		}		
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
					
					ServerSharedInfo.instantiate("127.0.0.1");
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
	
	private static void initCorba() {
		
		new Thread(new Runnable() {			
			@Override
			public void run() {
				try {
					String[] aargs = {"localhost"};		      
			       
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
	
	private static void initRMI() {
		new Thread(new Runnable() {			
			@Override
			public void run() {
				try {			
					ServerSideRMI obj = new ServerSideRMI();
				    Naming.rebind("//localhost/InterfaceRmi", obj);		       
				    System.out.println("Servidor aguardando requisicoes RMI....");		    
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}).start();
	}

}
