package cz.kuasta.networkClasses;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;

public class KryoUtil {
	 
    public static final int TCP_PORT = 55223;
    public static final int UDP_PORT = 55224;
    public static final String SERVER_IP = "127.0.0.1";
 
    public static void registerServerClasses(Server server) {
        register(server.getKryo());
    }
 
    public static void registerClientClass(Client client) {
        register(client.getKryo());        
    }
 
    private static void register(Kryo kryo) {
		//kryo.register(something.class);		
		
    	//network requests
    	kryo.register(LoginRequest.class);
    	kryo.register(DBRequest.class);
 
        //network responses
    	kryo.register(LoginResponse.class);
    	kryo.register(DBResponse.class);
		
    }
}