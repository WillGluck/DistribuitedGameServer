import furb.models.Region;





public class CorbaComunicationImpl { //extends CorbaComunicationPOA {

	  public boolean connectPlayer(short playerIP, org.omg.CORBA.BooleanHolder success) {
		  
		  
		  return true;
	  };

	  public boolean getIP(short regionNumber, String serverIP) {  	  
		  for (Region region : ServerManager.getInstance().getRegions()) {
			  if (region.getRegionNumber() == regionNumber) {
				  
			  }
		  }
	  	  return true;
	  };


	  public boolean mul_double (double p1, double p2, org.omg.CORBA.ShortHolder ret) {
	  	  ret.value = new Double(p1 * p2).shortValue();
		  System.out.println("Executada Multiplica��o");  	  
		  return true;
		  
	  };

}


