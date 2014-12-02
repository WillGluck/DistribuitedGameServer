

import java.rmi.Naming;

public class Main {
	
	public static void main(String[] args) {
		
		   try {
			      ServerSideRMI obj = new ServerSideRMI();
			      Naming.rebind("//localhost/RmiInterface", obj);
			      
			      
			      
			      
			      
		   } catch (Exception ex) {
			      System.out.println("Exception: " + ex.getMessage());
		   } 
	}

}
