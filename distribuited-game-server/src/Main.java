

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import furb.corba.InterfaceCorba;
import furb.corba.InterfaceCorbaHelper;
import furb.corba.InterfaceCorbaImpl;
import furb.rmi.ServerSideRMI;

public class Main {
	
	public static void main(String[] args) {
		
		   try {
			   
		       ServerSideRMI obj = new ServerSideRMI();
		       Naming.rebind("//localhost/InterfaceRmi", obj);
		       
		       System.out.println("Servidor aguardando requisicoes RMI....");

		       String[] aargs = {"localhost"};
		      
	           // Cria e inicializa o ORB
	           ORB orb = ORB.init(aargs, null);

	           // Cria a implementação e registra no ORB
	           InterfaceCorbaImpl interfaceCorba = new InterfaceCorbaImpl();

	           // Ativa o POA
	           POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
	           rootpoa.the_POAManager().activate();

	           // Pega a referência do servidor
	           org.omg.CORBA.Object ref = rootpoa.servant_to_reference(interfaceCorba);
	           InterfaceCorba href = InterfaceCorbaHelper.narrow(ref);
	    	  
	           // Obtém uma referência para o servidor de nomes
	           org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
	           NamingContextExt namecontextRef = NamingContextExtHelper.narrow(objRef);

	           // Registra o servidor no servico de nomes
	           String name = "InterfaceCorba";
	           NameComponent path[] = namecontextRef.to_name(name);
	           namecontextRef.rebind(path, href);

	           System.out.println("Servidor aguardando requisicoes CORBA....");

	           // Aguarda chamadas dos clientes
	           orb.run();

			      
			      
			      
		   } catch (Exception ex) {
			      System.out.println("Exception: " + ex.getMessage());
		   } 
	}

}
