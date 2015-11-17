package cz.kuasta.networkClasses;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;

/**
 * Utility class for the Kryo library.<br>
 * Contains port and IP data.
*/
public class KryoUtil {
	 
    public static final int TCP_PORT = 55223;
    public static final int UDP_PORT = 55224;
    public static final String SERVER_IP = "127.0.0.1";
 
    /**Registers the communication classes to the server.*/
    public static void registerServerClasses(Server server) {
        register(server.getKryo());
    }
    /**Registers the communication classes to the client.*/
    public static void registerClientClass(Client client) {
        register(client.getKryo());        
    }
    /**Multipurpose class registering tool.<p>
     * Should contain Network requests and responses.
     * 
     * @param kryo - instance of the Kryo server/client
    */
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