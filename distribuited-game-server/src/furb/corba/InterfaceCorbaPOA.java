package furb.corba;
/**
* InterfaceCorbaPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from InterfaceCorba.idl
* Quarta-feira, 3 de Dezembro de 2014 00h59min40s BRST
*/

public abstract class InterfaceCorbaPOA extends org.omg.PortableServer.Servant
 implements InterfaceCorbaOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("checkForRegion", new java.lang.Integer (0));
    _methods.put ("updatePlayer", new java.lang.Integer (1));
    _methods.put ("getPlayerTimestamp", new java.lang.Integer (2));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // InterfaceCorba/checkForRegion
       {
         int regionCode = in.read_long ();
         boolean $result = false;
         $result = this.checkForRegion (regionCode);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 1:  // InterfaceCorba/updatePlayer
       {
         String userName = in.read_string ();
         this.updatePlayer (userName);
         out = $rh.createReply();
         break;
       }

       case 2:  // InterfaceCorba/getPlayerTimestamp
       {
         String userName = in.read_string ();
         long $result = (long)0;
         $result = this.getPlayerTimestamp (userName);
         out = $rh.createReply();
         out.write_longlong ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:InterfaceCorba:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public InterfaceCorba _this() 
  {
    return InterfaceCorbaHelper.narrow(
    super._this_object());
  }

  public InterfaceCorba _this(org.omg.CORBA.ORB orb) 
  {
    return InterfaceCorbaHelper.narrow(
    super._this_object(orb));
  }


} // class InterfaceCorbaPOA
