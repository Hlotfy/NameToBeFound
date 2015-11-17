package cz.kuasta.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.kuasta.items.Equipment;
import cz.kuasta.items.Item;
import cz.kuasta.items.Stats;
import cz.kuasta.items.Equipment.Slot;
import cz.kuasta.items.Item.Quality;
import cz.kuasta.items.crafting.Machine;
import cz.kuasta.items.crafting.Recipe;
import cz.kuasta.items.crafting.Tool;


/**
 * An interface for communication between the library DB and the client.
 * @author Matrin Vostal
 * @version 1.0
 * @since 1.0
*/
public class DB{
	
	private Connection connection;
	/** The access point to your database, for example with MySQL, you use the IP, user login and password.*/
	private String conectionName;
	/**Name of the database connector class.*/
	private String connectorName;
	
	/** Basic constructor that includes the JDBC Database.*/
	public DB(){
		this.conectionName = "jdbc:sqlite:data/ServerDB.db";
		this.connectorName = "org.sqlite.JDBC";
		
		dbInit();
	}
	
	/** Advanced DB constructor.<br>
	 * If you want to change the DB type, you should also override connection methods with the respective commands and change respective methods.*/
	public DB(String connectionName, String connectorName){
		this.conectionName = connectionName;
		this.connectorName = connectorName;
		
		dbInit();
	}
	
	/***/
	private void dbInit() {
		
	}
	
	//DB methods
	public void executeSql(String sqlStmt){
		try{
			Class.forName(connectorName);
			connection = DriverManager.getConnection(conectionName);
			Statement statement = connection.createStatement();
			statement.executeUpdate(sqlStmt);
		}catch(SQLException | ClassNotFoundException e){
		    System.err.println(e.getMessage());
		}finally{
		    try{
		    	if(connection != null)
		    		connection.close();
		    }catch(SQLException e){
		        System.err.println(e);
		    }
		}
	}
	public void executeSql(ArrayList<String> sqlStmt){
		try{
			Class.forName(connectorName);
			connection = DriverManager.getConnection(conectionName);
			Statement statement = connection.createStatement();
			for(String s : sqlStmt){
				statement.executeUpdate(s);
			}
		}catch(SQLException | ClassNotFoundException e){
		    System.err.println(e.getMessage());
		}finally{
		    try{
		    	if(connection != null)
		    		connection.close();
		    }catch(SQLException e){
		        System.err.println(e);
		    }
		}
	}
	/**
	 * @param sqlStmt - string representing the statement for execution
	 * @return resultSet - Instance of ResultSet which contains statement results.
	 * @exception - 
	*/
	public ResultSet executeQuerry(String sqlStmt){
		ResultSet resultSet = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(conectionName);
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlStmt);
		}catch(SQLException | ClassNotFoundException e){
		    System.err.println(e.getMessage());
		}
		
		return resultSet;
	}		
	
	//added methods
	/**
	 * @param id databse table id
	 * @return result slot instance
	 * @throws SQLException 
	*/
	public Stats getStats(int id) throws SQLException{
		Stats result = null;
		
		String sqlStmt = "select * from Stats where id=" + id;
		ResultSet rsSet = executeQuerry(sqlStmt);
		
		result = new Stats();
		
		result.strength = rsSet.getInt("strength");
		result.intelect = rsSet.getInt("intelect");
		result.agility = rsSet.getInt("agility");
		result.stamina = rsSet.getInt("stamina");
		result.luck = rsSet.getInt("luck");
		result.atkPower = rsSet.getInt("atkPower");
		result.dodge = rsSet.getInt("dodge");
		result.hit = rsSet.getInt("hit");
		
		
		return result;
	}

	/**
	 * @throws SQLException 
	 * 
	*/
	@SuppressWarnings("unchecked")
	public Item getItem(int id, int amount) throws SQLException{
		Item result = null;
		String sqlStmt = null;
		ResultSet rsSet = null;
				
		
		if(1<id && id<200){
			
			sqlStmt = "select * from Items where id=" + id;
			rsSet = executeQuerry(sqlStmt);	
					 
			result = new Item();
					
			result.id = rsSet.getInt("id");
			result.setName(rsSet.getString("name"));
			result.setAmount(amount);
			result.setMaxAmount(rsSet.getInt("maxAmount"));
			result.setFlavorText(rsSet.getString("flavorText"));
			result.setBuyPrice(rsSet.getInt("buyPrice"));
			result.setSellPrice(rsSet.getInt("sellPrice"));
			result.setQuality(valueToQuality(rsSet.getInt("quality")));
				
		} else if(200<id && id<400){
				
			sqlStmt = "select * from Eqipment where id=" + id;
			rsSet = executeQuerry(sqlStmt);
			
			result = new Equipment();
					
			result.id = rsSet.getInt("id");
			result.setName(rsSet.getString("name"));
			result.setAmount(amount);
			result.setMaxAmount(rsSet.getInt("maxAmount"));
			result.setFlavorText(rsSet.getString("flavorText"));
			result.setBuyPrice(rsSet.getInt("buyPrice"));
			result.setSellPrice(rsSet.getInt("sellPrice"));
			result.setQuality(valueToQuality(rsSet.getInt("quality")));
			((Equipment) result).setDurability(rsSet.getInt("durability"));
			((Equipment) result).setMaxDurability(rsSet.getInt("maxDurability"));
			((Equipment) result).setSlot(valueToSlot(rsSet.getInt("slot")));
			((Equipment) result).setStats(getStats(rsSet.getInt("stats")));				
		} else if(200<id && id<400){	
			
			sqlStmt = "select * from Recipes where id=" + id;
			rsSet = executeQuerry(sqlStmt);
				
			result = new Recipe();
				
			result.id = rsSet.getInt("id");
			result.setName(rsSet.getString("name"));
			result.setAmount(amount);
			result.setMaxAmount(rsSet.getInt("maxAmount"));
			result.setFlavorText(rsSet.getString("flavorText"));
			result.setBuyPrice(rsSet.getInt("buyPrice"));
			result.setSellPrice(rsSet.getInt("sellPrice"));
			result.setQuality(valueToQuality(rsSet.getInt("quality")));
			((Recipe) result).setDescription(rsSet.getString("description"));
			((Recipe) result).setMachine((Class<? extends Machine>) rsSet.getObject("requiredMachine"));
				
			//adding requiredItems
			ArrayList<cz.kuasta.items.crafting.Slot> tmp = new ArrayList<cz.kuasta.items.crafting.Slot>();
			ResultSet rsSetTmp = executeQuerry("select * from SlotValues where id=" + rsSet.getInt("requiredItems"));
			int i = 2;
			
			while(rsSetTmp.getInt(i) != (Integer) null){
				cz.kuasta.items.crafting.Slot slot = new cz.kuasta.items.crafting.Slot(
						rsSetTmp.getInt(i),
						rsSetTmp.getInt(i+1)
					);
				tmp.add(slot);
				i += 2;
			}
				
			((Recipe) result).setRequiredItems(tmp);
				
			//setting tool IDs
			ArrayList<Class<? extends Tool>> tmp2 = new ArrayList<Class<? extends Tool>>();
			i = 2;
			rsSetTmp = executeQuerry("select * from ToolIDs where id=" + rsSet.getInt("requiredTools"));
			while(rsSetTmp.getObject(i) != null){
				tmp2.add((Class<? extends Tool>) rsSetTmp.getObject(i));
				i++;
			}
				
			//setting products
			tmp = new ArrayList<cz.kuasta.items.crafting.Slot>();
			i = 2;
			rsSetTmp = executeQuerry("select * from SlotValues where id=" + rsSet.getInt("products"));
			while(rsSetTmp.getInt(i) != (Integer) null){
				cz.kuasta.items.crafting.Slot slot = new cz.kuasta.items.crafting.Slot(
						rsSetTmp.getInt(i),
						rsSetTmp.getInt(i+1)
					);
				tmp.add(slot);
				i += 2;
			}	
		} else if(true){ // make other table slot if necessary
			
		}	
		
		return result;
	}
	
	private boolean nameTaken(String name){		
		ResultSet rsset = executeQuerry("select * from Users where name=" + name);
		
		if(rsset == null)
			return true;
		else
			return false;
	}
	public  void addUser(String login, String password, int accountLevel, String email){
		String stmt = "";
		boolean nameTaken = nameTaken(login);
		
		if(nameTaken = false){
			Pattern loginPatern = Pattern.compile("[a-zA-Z0-9]{6,20}");
			Matcher loginMatcher = loginPatern.matcher(login);
			
			Pattern passwordPatern = Pattern.compile("[a-zA-Z0-9]{6,30}");
			Matcher passwordMatcher = passwordPatern.matcher(password);
			
			Pattern emailPatern = Pattern.compile("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,3}");
			Matcher emailMatcher = emailPatern.matcher(email);
			
			if (emailMatcher.find() & loginMatcher.find() & passwordMatcher.find() & nameTaken == false) {
				stmt = "insert into Users values(" + login + "', '" + password + "', '" + email + "', " + accountLevel + ")";
				executeSql(stmt);
			}else{
				
			}
			
			
		}
	}
	public void addItem(){}

	public static Quality valueToQuality(int i){
		Quality result = null;
		
		switch(i){
			case 1:
				result = Quality.POOR;
				break;
			case 2:
				result = Quality.COMMON;
				break;
			case 3:
				result = Quality.UNCOMMON;
				break;
			case 4:
				result = Quality.RARE;
				break;
			case 5:
				result = Quality.EPIC;
				break;
			case 6:
				result = Quality.MYTHIC;
				break;
			case 7:
				result = Quality.LEGENDARY;
				break;
		}
		
		return result;
	}
	public static Slot valueToSlot(int i){
		Slot result = null;
		
		switch(i){
			case 1:
				result = Slot.HEAD;
				break;
			case 2:
				result = Slot.SHOULDERS;
				break;
			case 3:
				result = Slot.CHEST;
				break;
			case 4:
				result = Slot.WRIST;
				break;
			case 5:
				result = Slot.HANDS;
				break;
			case 6:
				result = Slot.BELT;
				break;
			case 7:
				result = Slot.LEGS;
				break;
			case 8:
				result = Slot.BOOTS;
				break;
			case 9:
				result = Slot.MAINHAND;
				break;
			case 10:
				result = Slot.OFFHAND;
				break;
			case 11:
				result = Slot.ONEHAND;
				break;
			/*case 12:
				result = Slot.TWOHAND;
				break;*/
			case 13:
				result = Slot.RING;
				break;
		}
		
		return result;
	}
}