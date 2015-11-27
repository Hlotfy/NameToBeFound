package cz.kuasta.NameToBeFoundServer;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import cz.kuasta.db.CharacterData;
import cz.kuasta.db.DB;
import cz.kuasta.db.UserData;
import cz.kuasta.entityComponentSystem.Component;
import cz.kuasta.entityComponentSystem.EntityManager;
import cz.kuasta.entityComponentSystem.components.PlayerComponent;
import cz.kuasta.networkClasses.ClientShutdown;
import cz.kuasta.networkClasses.KryoUtil;
import cz.kuasta.networkClasses.LoginRequest;
import cz.kuasta.networkClasses.LoginResponse;
import cz.kuasta.networkClasses.RegistrationRequest;
import cz.kuasta.networkClasses.RegistrationResponse;

public class NameToBeFoundServer {

	public class ServerRates{
		public static final double STRENGTH_RATIO = 1.5;
		public static final double INTELECT_RATIO = 1.5;
		public static final double AGILITY_RATIO = 1.5;
		public static final double STAMINA_RATIO = 1.5;
		public static final double LUCK_RATIO = 1.5;
		
		public static final double ATK_POWER_DIVIDE_RATIO = 0.1;
		public static final double DODGE_DIVIDE_RATIO = 0.1;
		public static final double HIT_CHANCE_DIVIDE_RATIO_STRENGTH = 0.05;
		public static final double HIT_CHANCE_DIVIDE_RATIO_AGILITY = 0.05;
	}
	
	public static Server server;
	public static String serverLog = "";
	public static DB db;
	public static EntityManager entityManager;
	public static ServerRates serverRates;
	private static ArrayList<Integer> connectionsAccesLevel;
		
	//DO ONLY ONCE IF DB NOT DELETED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@SuppressWarnings("unused")
	private static void dbInit(){		
		ArrayList<String> statements = new ArrayList<String>();
		
		statements.add("create table Users ("
				+ "login string,"
				+ "password string,"
				+ "email string,"
				+ "accesLevel int,"
				+ "characterId int)"
			);
		statements.add("create table Characters ("
				+ "characterId int,"
				+ "name string,"
				+ "hp int,"
				+ "level int,"
				+ "baseStats JAVA_OBJECT,"
				+ "inventory JAVA_OBJECT,"
				+ "equip JAVA_OBJECT)"
			);
		statements.add("create table Items ("
				+ "id integer,"
				+ "name string,"
				+ "itemLevel integer,"
				+ "itemClass integer"
				+ "flavorText string,"
				+ "quality integer,"
				+ "buyPrice integer,"
				+ "sellPrice integer,"
				+ "maxAmount integer,"
				+ "maxDurability integer,"
				+ "strength integer,"
				+ "intelect integer,"
				+ "agility integer,"
				+ "stamina integer,"
				+ "luck integer,"
				+ "atkPower integer,"
				+ "dodge float,"
				+ "hit float,"
				+ "critRating integer,"
				+ "armor integer)"
			);
		
		db.executeSql(statements);
	}
	
	public static void main(String[] args) throws SQLException{
		Log.set(Log.LEVEL_DEBUG);
		 
		connectionsAccesLevel =  new ArrayList<Integer>();
		
        server = new Server();
        KryoUtil.registerServerClasses(server);
        db = new DB();
        
        entityManager = new EntityManager();
        
        server.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                serverLog += new Timestamp(new Date().getTime()) + " Client ID: " + connection.getID() + " connected\n";
            }
            @Override
            public void disconnected(Connection connection) {
                serverLog += new Timestamp(new Date().getTime()) + " Client ID: " + connection.getID() + " disconnected\n";
                
                if(EntityManager.getComponents().get(EntityManager.getEntity(connection.getID())) != null){
	                PlayerComponent comp = (PlayerComponent) EntityManager.getComponents().get(EntityManager.getEntity(connection.getID())).get(PlayerComponent.class);
	                                
	                String stmt = "update Characters set "
	                		+ "level=" + comp.getLevel()
	                		+ " stats=" + comp.getBaseStats()
	                		+ " inventory=" + comp.getInventory()
	                		+ " equip=" + comp.getEquip()
	                		+ " where id=" + comp.getCharId();
	                db.executeSql(stmt);
	                
	                entityManager.killEntity(connection.getID());
	                
	                db.dbEntriesToTxt();
                }
            }
            @SuppressWarnings({ "null" })
			@Override
            public void received(Connection connection, Object object) {
            	if(object instanceof ClientShutdown){
            		connection.close();
            	}else if(object instanceof LoginRequest){
            		serverLog += new Timestamp( new Date().getTime()) + " LoginRequest login: " + ((LoginRequest) object).login + "from IP: " + ((LoginRequest) object).ip.toString() + " recieved\n";
	    			                     		
            		LoginRequest request = (LoginRequest) object;
	    			UserData userData = db.getUserData(request.login);
	    			
	    			LoginResponse response = new LoginResponse();
	    			
	    			if(userData != null && userData.password == request.password){
						connectionsAccesLevel.set(connection.getID(), userData.accesLevel);
						CharacterData characterData = db.getCharacterData(userData.characterId);
						
						if(characterData != null){
							int id = entityManager.newEntity(connection.getID());	
							
							ArrayList<Component> comps = new ArrayList<Component>();
							comps.add(new PlayerComponent(characterData.charId, characterData.title, characterData.name, characterData.level, characterData.stats, characterData.inventory, characterData.equip));
							
							entityManager.addComponents(id, comps);
							
							serverLog += new Timestamp( new Date().getTime()) + " Entity id: " + id + "added to EntityManager\n";
							
							response.id = id;
							response.login = true;
							response.password = true;
						}else{
							
						}
					} else if (userData != null && userData.password != request.password){
						response.id = (Integer) null;
						response.login = true;
						response.password = false;
					} else {
						response.id = (Integer) null;
						response.login = false;
						response.password = false;
					}
	    			
	    			connection.sendTCP(response);
	    			serverLog += new Timestamp(new Date().getTime()) + " LoginResponse login: " + request.login + " sent\n";
	    		} else if(object instanceof RegistrationRequest){
	    			serverLog += new Timestamp( new Date().getTime()) + " RegistrationRequest recieved\n";
	    			RegistrationRequest request = (RegistrationRequest) object;
	    			RegistrationResponse response = new RegistrationResponse();
	    			
	    			response.state = db.addUser(request.login, request.password, request.email, 1);
	  	    					
	    			connection.sendTCP(response);
	    			db.dbEntriesToTxt();
	    		}
            }
        });
 
        try {
            server.bind(55223, 55224);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        
       // dbInit();
        
		server.start();
		
       
		

		//int result = db.addUser("Kuasta", "md18nmjchdnss", "vostal.martin@gmail.com", 4);
		
        
		
	}

	public static ServerRates getServerRates() {
		return serverRates;
	}
}
