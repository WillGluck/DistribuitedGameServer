package furb.game;

import java.util.Map;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import thrift.stubs.Game;
import furb.models.Region;

public class ServerMain {
	
	public static void main(String[] args) throws TTransportException {
		ServerSharedInfo.instantiate("127.0.0.1");
		Map<Integer, Region> regions = ServerSharedInfo.getInstance().getResions();
		regions.put(1, new Region(1));
		
		GameHandler handler = new GameHandler();
		Game.Processor<GameHandler> processor = new Game.Processor<GameHandler>(handler);
		TServerTransport serverTransport = new TServerSocket(9090);
	    TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

	    System.out.println("Starting the simple server...");
	    server.serve();
	}

}
